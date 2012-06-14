/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mentalhealthliberia;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author greg
 */
public class DataStore {
    
    public enum FileStatus {
        READY, OUTOFDATE, ERROR
    }
    
    private static final String FORM_DIR = "forms";
    
    private static DataStore INSTANCE = new DataStore();
    
    private DataStore() {}
    
    public static DataStore getInstance() {
        return INSTANCE;
    }
    
    public FileStatus sendFile(File file, String username, String password) {
        try {
            // Construct data
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String data = "";
            String curr = reader.readLine();
            while (curr != null) {
                data += curr;
                curr = reader.readLine();
            }

            // append password field

            reader.close();

            String charset = "UTF-8";
            String query = String.format("username=%s&password=%s&data=%s", URLEncoder.encode(username, charset), 
                    URLEncoder.encode(password, charset), URLEncoder.encode(data, charset));
            System.out.println(query);

            try {
                boolean versionOutOfDate = false;

                // Send data
                URL url = new URL(MentalHealthLiberiaApp.getApplication().getUploadUrl());
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(query);
                wr.flush();

                // Get the response
                if (conn.getResponseCode() != 200) {
                    wr.close();
                    return FileStatus.READY;
                } else {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = rd.readLine()) != null) {
                        Integer version = Integer.parseInt(line);
                        if (version > MentalHealthLiberiaApp.getApplication().getVersionNum()) {
                            // version is out of date
                            versionOutOfDate = true;
                        }
                    }
                    rd.close();
                }

                wr.close();

                if (versionOutOfDate) {
                    return FileStatus.OUTOFDATE;
                }

                return FileStatus.READY;

            } catch (Exception e) {
                System.err.println("Error occured while transmitting data.\n" +
                        "Please contact a system administrator.");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Error occured while reading saved form data.");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return FileStatus.ERROR;
    }

    public boolean saveForm(PatientEncounterForm form) {
        String dataDir = MentalHealthLiberiaApp.getApplication().getDataDirectory();
        File dataDirFile = new File(dataDir);
        if (!dataDirFile.exists()) {
            dataDirFile.mkdir();
        }
        File directory = new File(dataDir + FORM_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File newFile = null;
        if (directory.isDirectory()) {
            try {
                String fileName = form.getClinicianID() + '-' + form.getDateOfService() + ".mhl";
                newFile = new File(directory.getAbsolutePath() + "/" + removeInvalidCharacters(fileName));
                newFile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                JSONSerializer serializer = new JSONSerializer();
                String data = serializer.serialize(form);
                writer.write(data);
                writer.close();
                return true;
            } catch (IOException e) {
                System.err.println(e.getMessage());
                if (newFile != null) {
                    newFile.delete();
                }
                e.printStackTrace();
            }
        }
        return false;
    }
    
    private String removeInvalidCharacters(String fileName) {
        try {
            return URLEncoder.encode(fileName.trim(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return fileName.trim();
        }
    }
    
    public List<PatientEncounterForm> getPendingForms() {
        List<PatientEncounterForm> forms = new ArrayList<PatientEncounterForm>();
        File directory = new File(MentalHealthLiberiaApp.getApplication().getDataDirectory() + FORM_DIR);
        try {
            for (File file : directory.listFiles()) {
                if (!file.isDirectory()) {
                    String jsonString = "";
                    StringBuilder fileData = new StringBuilder(1000);
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    char[] buf = new char[1024];
                    int numRead=0;
                    while((numRead=reader.read(buf)) != -1){
                        fileData.append(buf, 0, numRead);
                    }
                    reader.close();
                    jsonString = fileData.toString();

                    PatientEncounterForm form = new JSONDeserializer<PatientEncounterForm>()
                            .deserialize(jsonString);

                    forms.add(form);
                }
            }
        } catch (Exception e) {
            System.err.println("File does not appear to be valid MHL file: " + e.getMessage());
            e.printStackTrace();
        }
        return forms;
    }
    
    public FileStatus uploadFiles(String username, String password) {
        boolean errorEncountered = false;
        File directory = new File(MentalHealthLiberiaApp.getApplication().getDataDirectory() + FORM_DIR);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {

            // upload pending file
            if (sendFile(files[i], username, password) != DataStore.FileStatus.READY) {
                // delete pending file
                files[i].delete();
            } else {
                errorEncountered = true;
                break;
            }
        }

        // notify user of result
        if (errorEncountered) {
            return FileStatus.ERROR;

        } else {
            return FileStatus.READY;
        }
    }
}
