/*
 * MetnalHealthLiberiaView.java
 */

package mentalhealthliberia;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * The application's main frame.
 */
public class MentalHealthLiberiaView extends FrameView {

    private DataStore dataStore = DataStore.getInstance();
    
    // Input Verifiers
    public class IntegerInputVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField tf = (JTextField) input;
            return tf.getText() != null && tf.getText().matches("[0-9]*");
        }
    }
    
    public MentalHealthLiberiaView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        final MentalHealthLiberiaView parent = this;
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (parent.saveForm()) {
                    System.exit(1);
                }
            }
        };
        getFrame().addWindowListener(exitListener);
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = MentalHealthLiberiaApp.getApplication().getMainFrame();
            aboutBox = new MentalHealthLiberiaAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        MentalHealthLiberiaApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        uploadMenuItem = new javax.swing.JMenuItem();
        saveAsPdfMenuItem = new javax.swing.JMenuItem();
        browseFormsMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        reasonForVisit = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JTabbedPane();
        basicInformationOuterPanel = new javax.swing.JPanel();
        basicInformationPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        locationOfService = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        clinicianID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clinicianTrainingLevel = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        patientID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        referralSource = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dateOfService = new javax.swing.JFormattedTextField();
        reasonForVisit1 = new javax.swing.JCheckBox();
        reasonForVisit2 = new javax.swing.JCheckBox();
        patientDemographicsOuterPanel = new javax.swing.JPanel();
        patientDemographicsPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        countyOfResidence = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        distanceTraveled = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        maritalStatus = new javax.swing.JList();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        education = new javax.swing.JList();
        jLabel23 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        age = new javax.swing.JFormattedTextField();
        numberOfDaysInLifeRole = new javax.swing.JTextField();
        symptonsOuterPanel = new javax.swing.JPanel();
        symptonsPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        phq = new javax.swing.JFormattedTextField();
        gaf = new javax.swing.JFormattedTextField();
        cage = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        who_das = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        euroqol = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        otherSymptomsScore = new javax.swing.JTextField();
        diagnosisScrollPane = new javax.swing.JScrollPane();
        diagnosisPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        moodDisorder = new javax.swing.JList();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        anxietyDisorder = new javax.swing.JList();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        psychoticDisorder = new javax.swing.JList();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        somatoformDisorder = new javax.swing.JList();
        jLabel35 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        substanceAbuseDisorder = new javax.swing.JList();
        jScrollPane11 = new javax.swing.JScrollPane();
        substanceAbuseDisorder2 = new javax.swing.JList();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        epilepsy = new javax.swing.JList();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        otherMedicalCondition = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        secondaryDiagnosis = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        diagnosisPrimary = new javax.swing.JList();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        numberOfSeizuresPerMonth = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        epilepticOtherSymptoms = new javax.swing.JTextArea();
        jScrollPane20 = new javax.swing.JScrollPane();
        developmentalDisability = new javax.swing.JList();
        jScrollPane19 = new javax.swing.JScrollPane();
        developmentalDisabilityOther = new javax.swing.JTextArea();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        childhoodMentalHealthDisorder = new javax.swing.JList();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        childhoodMentalHealthDisorderOther = new javax.swing.JTextArea();
        jLabel57 = new javax.swing.JLabel();
        treatmentOuterPanel = new javax.swing.JPanel();
        treatmentPanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        fluoxetine = new javax.swing.JCheckBox();
        escitalopram = new javax.swing.JCheckBox();
        sertraline = new javax.swing.JCheckBox();
        amitriptyline = new javax.swing.JCheckBox();
        imipramine = new javax.swing.JCheckBox();
        otherAntidepressant = new javax.swing.JCheckBox();
        haloperidal = new javax.swing.JCheckBox();
        haloperidalDecanoatInjection = new javax.swing.JCheckBox();
        chlorpromazine = new javax.swing.JCheckBox();
        fluphenazine = new javax.swing.JCheckBox();
        fluphenazineDecanoateInjection = new javax.swing.JCheckBox();
        risperidone = new javax.swing.JCheckBox();
        risperidoneConstaInjection = new javax.swing.JCheckBox();
        otherAntipsychotic = new javax.swing.JCheckBox();
        clomipramine = new javax.swing.JCheckBox();
        clonazepam = new javax.swing.JCheckBox();
        diazepam = new javax.swing.JCheckBox();
        lorazepam = new javax.swing.JCheckBox();
        otherSedative = new javax.swing.JCheckBox();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        depakote = new javax.swing.JCheckBox();
        lithium = new javax.swing.JCheckBox();
        carbamazepine = new javax.swing.JCheckBox();
        otherMoodStabilizer = new javax.swing.JCheckBox();
        depakoteAntiepiletic = new javax.swing.JCheckBox();
        carbamazepineAntiepileptic = new javax.swing.JCheckBox();
        phenobarbital = new javax.swing.JCheckBox();
        phenytoin = new javax.swing.JCheckBox();
        otherAntiepileptic = new javax.swing.JCheckBox();
        trihexyphenidyl = new javax.swing.JCheckBox();
        otherAnticholinergic = new javax.swing.JCheckBox();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        individualCounseling = new javax.swing.JList();
        jScrollPane17 = new javax.swing.JScrollPane();
        familyPsychoEducation = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        medicationsNotAvailable = new javax.swing.JTextField();
        counseling1 = new javax.swing.JCheckBox();
        counseling2 = new javax.swing.JCheckBox();
        counseling3 = new javax.swing.JCheckBox();
        counseling4 = new javax.swing.JCheckBox();
        counseling5 = new javax.swing.JCheckBox();
        dischargeOuterPanel = new javax.swing.JPanel();
        dischargePanel = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        followUpCareMedication1 = new javax.swing.JRadioButton();
        followUpCareMedication2 = new javax.swing.JRadioButton();
        followUpCareCounseling1 = new javax.swing.JRadioButton();
        followUpCareCounseling2 = new javax.swing.JRadioButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        dischargeDisposition = new javax.swing.JList();
        reportData1 = new javax.swing.JRadioButton();
        reportData2 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        followupCareMedicationsGroup = new javax.swing.ButtonGroup();
        followupCareCounselingGroup = new javax.swing.ButtonGroup();
        permissionToReportGroup = new javax.swing.ButtonGroup();

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(mentalhealthliberia.MentalHealthLiberiaApp.class).getContext().getResourceMap(MentalHealthLiberiaView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newMenuItem.setText(resourceMap.getString("newMenuItem.text")); // NOI18N
        newMenuItem.setName("newMenuItem"); // NOI18N
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        uploadMenuItem.setText(resourceMap.getString("uploadMenuItem.text")); // NOI18N
        uploadMenuItem.setName("uploadMenuItem"); // NOI18N
        uploadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(uploadMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(mentalhealthliberia.MentalHealthLiberiaApp.class).getContext().getActionMap(MentalHealthLiberiaView.class, this);
        saveAsPdfMenuItem.setAction(actionMap.get("saveAsPDF")); // NOI18N
        saveAsPdfMenuItem.setText(resourceMap.getString("saveAsPdfMenuItem.text")); // NOI18N
        saveAsPdfMenuItem.setName("saveAsPdfMenuItem"); // NOI18N
        saveAsPdfMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsPdfMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsPdfMenuItem);

        browseFormsMenuItem.setAction(actionMap.get("browseForms")); // NOI18N
        browseFormsMenuItem.setText(resourceMap.getString("browseFormsMenuItem.text")); // NOI18N
        browseFormsMenuItem.setName("browseFormsMenuItem"); // NOI18N
        browseFormsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseFormsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(browseFormsMenuItem);

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 614, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        mainPanel.setToolTipText(resourceMap.getString("basicInformationTab.toolTipText")); // NOI18N
        mainPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        mainPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        mainPanel.setName("basicInformationTab"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        basicInformationOuterPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        basicInformationOuterPanel.setName("basicInformationOuterPanel"); // NOI18N
        basicInformationOuterPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        basicInformationPanel.setName("basicInformationPanel"); // NOI18N
        basicInformationPanel.setPreferredSize(new java.awt.Dimension(600, 407));
        basicInformationPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(resourceMap.getFont("basicInformationLabel.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("basicInformationLabel.text")); // NOI18N
        jLabel1.setName("basicInformationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel1, gridBagConstraints);

        jSeparator1.setName("jSeparator1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        basicInformationPanel.add(jSeparator1, gridBagConstraints);

        jLabel2.setFont(resourceMap.getFont("dateOfServiceLabel.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("dateOfServiceLabel.text")); // NOI18N
        jLabel2.setName("dateOfServiceLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(resourceMap.getFont("locationOfServiceLabel.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("locationOfServiceLabel.text")); // NOI18N
        jLabel3.setName("locationOfServiceLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel3, gridBagConstraints);

        locationOfService.setText(resourceMap.getString("locationOfService.text")); // NOI18N
        locationOfService.setName("locationOfService"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(locationOfService, gridBagConstraints);

        jLabel4.setName("clinicianIDLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        basicInformationPanel.add(jLabel4, gridBagConstraints);

        clinicianID.setText(resourceMap.getString("clinicianID.text")); // NOI18N
        clinicianID.setName("clinicianID"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(clinicianID, gridBagConstraints);

        jLabel5.setFont(resourceMap.getFont("reasonForVisitLabel.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("reasonForVisitLabel.text")); // NOI18N
        jLabel5.setName("reasonForVisitLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(resourceMap.getFont("clinicianTrainingLevelLabel.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("clinicianTrainingLevelLabel.text")); // NOI18N
        jLabel6.setName("clinicianTrainingLevelLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel6, gridBagConstraints);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        clinicianTrainingLevel.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "No formal training", "Community health worker training", "Physician's assistant training", "General clinician training", "Psychiatric clinician training", "Other", "No Response" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        clinicianTrainingLevel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        clinicianTrainingLevel.setName("clinicianTrainingLevel"); // NOI18N
        jScrollPane1.setViewportView(clinicianTrainingLevel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jScrollPane1, gridBagConstraints);

        jLabel7.setFont(resourceMap.getFont("patientIDLabel.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("patientIDLabel.text")); // NOI18N
        jLabel7.setName("patientIDLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel7, gridBagConstraints);

        patientID.setEditable(false);
        patientID.setText(resourceMap.getString("patientID.text")); // NOI18N
        patientID.setName("patientID"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(patientID, gridBagConstraints);

        jButton1.setText(resourceMap.getString("generatePatientIDButton.text")); // NOI18N
        jButton1.setName("generatePatientIDButton"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generatePatientIDClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jButton1, gridBagConstraints);

        jLabel8.setFont(resourceMap.getFont("referralSourceLabel.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("referralSourceLabel.text")); // NOI18N
        jLabel8.setName("referralSourceLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel8, gridBagConstraints);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        referralSource.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Clinician", "Messages (radio, etc)", "Other", "No Response" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        referralSource.setName("referralSource"); // NOI18N
        jScrollPane2.setViewportView(referralSource);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jScrollPane2, gridBagConstraints);

        jLabel9.setName("clinicianIDLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        basicInformationPanel.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(resourceMap.getFont("clinicianIDLabel.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("clinicianIDLabel.text")); // NOI18N
        jLabel10.setName("clinicianIDLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(jLabel10, gridBagConstraints);

        dateOfService.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss"))));
        dateOfService.setText(new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
        dateOfService.setName("dateOfService"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(dateOfService, gridBagConstraints);

        reasonForVisit1.setText(resourceMap.getString("reasonForVisit1.text")); // NOI18N
        reasonForVisit1.setName("reasonForVisit1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(reasonForVisit1, gridBagConstraints);

        reasonForVisit2.setText(resourceMap.getString("reasonForVisit2.text")); // NOI18N
        reasonForVisit2.setName("reasonForVisit2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        basicInformationPanel.add(reasonForVisit2, gridBagConstraints);

        basicInformationOuterPanel.add(basicInformationPanel);

        mainPanel.addTab(resourceMap.getString("basicInformationOuterPanel.TabConstraints.tabTitle"), basicInformationOuterPanel); // NOI18N

        patientDemographicsOuterPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        patientDemographicsOuterPanel.setName("patientDemographicsOuterPanel"); // NOI18N
        patientDemographicsOuterPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        patientDemographicsPanel.setName("patientDemographicsPanel"); // NOI18N
        patientDemographicsPanel.setLayout(new java.awt.GridBagLayout());

        jLabel16.setFont(resourceMap.getFont("ageLabel.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("ageLabel.text")); // NOI18N
        jLabel16.setName("ageLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jLabel16, gridBagConstraints);

        jLabel17.setFont(resourceMap.getFont("countyLabel.font")); // NOI18N
        jLabel17.setText(resourceMap.getString("countyLabel.text")); // NOI18N
        jLabel17.setName("countyLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jLabel17, gridBagConstraints);

        countyOfResidence.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bomi", "Bong", "Gbarpolo", "Grand Bassa", "Grand Cape Mount", "Grand Gedah", "Grand Kru", "Lofa", "Margibi", "Maryland", "Montserrado", "Nimba", "River Cess", "River Gee", "Sinoe", "Other", "" }));
        countyOfResidence.setSelectedIndex(9);
        countyOfResidence.setName("county"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(countyOfResidence, gridBagConstraints);

        jLabel18.setFont(resourceMap.getFont("distanceLabel.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("distanceLabel.text")); // NOI18N
        jLabel18.setName("distanceLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jLabel18, gridBagConstraints);

        distanceTraveled.setText(resourceMap.getString("distance.text")); // NOI18N
        distanceTraveled.setName("distance"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(distanceTraveled, gridBagConstraints);

        jLabel19.setFont(resourceMap.getFont("employmentLabel.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("employmentLabel.text")); // NOI18N
        jLabel19.setName("employmentLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jLabel19, gridBagConstraints);

        jLabel20.setFont(resourceMap.getFont("maritalStatusLabel.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("maritalStatusLabel.text")); // NOI18N
        jLabel20.setName("maritalStatusLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jLabel20, gridBagConstraints);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        maritalStatus.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Single, never married", "Married", "Divorced", "Widowed", "No Response" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        maritalStatus.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        maritalStatus.setName("maritalStatus"); // NOI18N
        jScrollPane4.setViewportView(maritalStatus);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jScrollPane4, gridBagConstraints);

        jLabel21.setFont(resourceMap.getFont("educationLabel.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("educationLabel.text")); // NOI18N
        jLabel21.setName("educationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jLabel21, gridBagConstraints);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        education.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "No formal education", "Grade (primary) school", "High school", "Some university", "University", "No Response" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        education.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        education.setName("education"); // NOI18N
        jScrollPane5.setViewportView(education);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jScrollPane5, gridBagConstraints);

        jLabel23.setFont(resourceMap.getFont("demographicsLabel.font")); // NOI18N
        jLabel23.setText(resourceMap.getString("demographicsLabel.text")); // NOI18N
        jLabel23.setName("demographicsLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jLabel23, gridBagConstraints);

        jSeparator3.setName("jSeparator3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        patientDemographicsPanel.add(jSeparator3, gridBagConstraints);

        jSeparator4.setName("jSeparator4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(jSeparator4, gridBagConstraints);

        age.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        age.setText(resourceMap.getString("age.text")); // NOI18N
        age.setName("age"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        patientDemographicsPanel.add(age, gridBagConstraints);

        numberOfDaysInLifeRole.setText(resourceMap.getString("numberOfDaysInLifeRole.text")); // NOI18N
        numberOfDaysInLifeRole.setInputVerifier(new IntegerInputVerifier());
        numberOfDaysInLifeRole.setName("numberOfDaysInLifeRole"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        patientDemographicsPanel.add(numberOfDaysInLifeRole, gridBagConstraints);

        patientDemographicsOuterPanel.add(patientDemographicsPanel);

        mainPanel.addTab(resourceMap.getString("patientDemographicsOuterPanel.TabConstraints.tabTitle"), patientDemographicsOuterPanel); // NOI18N

        symptonsOuterPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        symptonsOuterPanel.setName("symptonsOuterPanel"); // NOI18N
        symptonsOuterPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        symptonsPanel.setName("symptonsPanel"); // NOI18N
        symptonsPanel.setLayout(new java.awt.GridBagLayout());

        jLabel24.setFont(resourceMap.getFont("phqScoreLabel.font")); // NOI18N
        jLabel24.setText(resourceMap.getString("phqScoreLabel.text")); // NOI18N
        jLabel24.setName("phqScoreLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(jLabel24, gridBagConstraints);

        jLabel25.setFont(resourceMap.getFont("gafScoreLabel.font")); // NOI18N
        jLabel25.setText(resourceMap.getString("gafScoreLabel.text")); // NOI18N
        jLabel25.setName("gafScoreLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(jLabel25, gridBagConstraints);

        jLabel26.setFont(resourceMap.getFont("cageScoreLabel.font")); // NOI18N
        jLabel26.setText(resourceMap.getString("cageScoreLabel.text")); // NOI18N
        jLabel26.setName("cageScoreLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(jLabel26, gridBagConstraints);

        phq.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        phq.setText(resourceMap.getString("phq.text")); // NOI18N
        phq.setInputVerifier(new IntegerInputVerifier());
        phq.setName("phq"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(phq, gridBagConstraints);

        gaf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        gaf.setText(resourceMap.getString("gaf.text")); // NOI18N
        gaf.setInputVerifier(new IntegerInputVerifier());
        gaf.setName("gaf"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(gaf, gridBagConstraints);

        cage.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        cage.setText(resourceMap.getString("cage.text")); // NOI18N
        cage.setInputVerifier(new IntegerInputVerifier());
        cage.setName("cage"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(cage, gridBagConstraints);

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        symptonsPanel.add(jLabel11, gridBagConstraints);

        who_das.setText(resourceMap.getString("who_das.text")); // NOI18N
        who_das.setInputVerifier(new IntegerInputVerifier());
        who_das.setName("who_das"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(who_das, gridBagConstraints);

        jLabel50.setText(resourceMap.getString("jLabel50.text")); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        symptonsPanel.add(jLabel50, gridBagConstraints);

        jLabel51.setText(resourceMap.getString("jLabel51.text")); // NOI18N
        jLabel51.setName("jLabel51"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        symptonsPanel.add(jLabel51, gridBagConstraints);

        euroqol.setText(resourceMap.getString("euroqol.text")); // NOI18N
        euroqol.setInputVerifier(new IntegerInputVerifier());
        euroqol.setName("euroqol"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(euroqol, gridBagConstraints);

        jLabel52.setText(resourceMap.getString("jLabel52.text")); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        symptonsPanel.add(jLabel52, gridBagConstraints);

        otherSymptomsScore.setText(resourceMap.getString("otherSymptomsScore.text")); // NOI18N
        otherSymptomsScore.setName("otherSymptomsScore"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        symptonsPanel.add(otherSymptomsScore, gridBagConstraints);

        symptonsOuterPanel.add(symptonsPanel);

        mainPanel.addTab(resourceMap.getString("symptonsOuterPanel.TabConstraints.tabTitle"), symptonsOuterPanel); // NOI18N

        diagnosisScrollPane.setMinimumSize(new java.awt.Dimension(800, 600));
        diagnosisScrollPane.setName("diagnosisScrollPane"); // NOI18N
        diagnosisScrollPane.setPreferredSize(new java.awt.Dimension(790, 600));

        diagnosisPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        diagnosisPanel.setName("diagnosisPanel"); // NOI18N
        diagnosisPanel.setPreferredSize(new java.awt.Dimension(780, 1800));
        diagnosisPanel.setLayout(new java.awt.GridBagLayout());

        jLabel27.setFont(resourceMap.getFont("moodDisorderFirstLabel.font")); // NOI18N
        jLabel27.setText(resourceMap.getString("moodDisorderFirstLabel.text")); // NOI18N
        jLabel27.setName("moodDisorderFirstLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel27, gridBagConstraints);

        jLabel28.setFont(resourceMap.getFont("moodDisorderSecondLabel.font")); // NOI18N
        jLabel28.setText(resourceMap.getString("moodDisorderSecondLabel.text")); // NOI18N
        jLabel28.setName("moodDisorderSecondLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel28, gridBagConstraints);

        jScrollPane7.setMinimumSize(new java.awt.Dimension(313, 300));
        jScrollPane7.setName("moodDisorderSecond"); // NOI18N
        jScrollPane7.setPreferredSize(new java.awt.Dimension(241, 300));

        moodDisorder.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Full remission", "Partial remission", "Mild", "Moderate", "Severe without psychotic features", "Severe with psychotic features", "Unspecified", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        moodDisorder.setMaximumSize(new java.awt.Dimension(218, 300));
        moodDisorder.setMinimumSize(new java.awt.Dimension(218, 300));
        moodDisorder.setName("moodDisorderSecond"); // NOI18N
        moodDisorder.setPreferredSize(new java.awt.Dimension(218, 300));
        jScrollPane7.setViewportView(moodDisorder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane7, gridBagConstraints);

        jLabel29.setFont(resourceMap.getFont("anxietyDisorderLabel.font")); // NOI18N
        jLabel29.setText(resourceMap.getString("anxietyDisorderLabel.text")); // NOI18N
        jLabel29.setName("anxietyDisorderLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel29, gridBagConstraints);

        jScrollPane8.setMinimumSize(new java.awt.Dimension(200, 150));
        jScrollPane8.setName("jScrollPane8"); // NOI18N

        anxietyDisorder.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Generalized anxiety disorder", "Panic disorder with agoraphobia", "Panic disorder without agoraphobia", "Agoraphobia without history of panic disorder", "Specific phobia", "Social phobia", "Obsessive compulsive disorder", "Post-traumatic stress disorder", "Acute stress", "Anxiety disorder due to general medical condition", "Anxiety disorder due to specific medical condition", "Anxiety disorder NOS", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        anxietyDisorder.setName("anxietyDisorder"); // NOI18N
        jScrollPane8.setViewportView(anxietyDisorder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane8, gridBagConstraints);

        jLabel31.setFont(resourceMap.getFont("psychoticDisorderLabel.font")); // NOI18N
        jLabel31.setText(resourceMap.getString("psychoticDisorderLabel.text")); // NOI18N
        jLabel31.setName("psychoticDisorderLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel31, gridBagConstraints);

        jScrollPane10.setMinimumSize(new java.awt.Dimension(200, 150));
        jScrollPane10.setName("jScrollPane10"); // NOI18N

        psychoticDisorder.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Schizophrenia catatonic type", "Schizophrenia disorganized type", "Schizophrenia paranoid type", "Schizophrenia residual type", "Schizophrenia undifferentiated type", "Schizoaffective disorder", "Brief psychotic disorder", "Psychotic disorder due to medical condition with delusions", "Psychotic disorder due to medical condition with hallucinations", "Psychotic disorder NOS", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        psychoticDisorder.setName("psychoticDisorder"); // NOI18N
        jScrollPane10.setViewportView(psychoticDisorder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane10, gridBagConstraints);

        jLabel33.setFont(resourceMap.getFont("somatoformDisorderLabel.font")); // NOI18N
        jLabel33.setText(resourceMap.getString("somatoformDisorderLabel.text")); // NOI18N
        jLabel33.setName("somatoformDisorderLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel33, gridBagConstraints);

        jScrollPane12.setMinimumSize(new java.awt.Dimension(200, 150));
        jScrollPane12.setName("jScrollPane12"); // NOI18N

        somatoformDisorder.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Somatization disorder", "Undifferentiated somatoform disorder", "Conversion disorder", "Pain disorder with both psychological factors and general medical condition", "Pain disorder with psychological factors", "Hypochondriasis", "Body dysmorphic disorder", "Somatoform disorder NOS", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        somatoformDisorder.setName("somatoformDisorderLabel"); // NOI18N
        jScrollPane12.setViewportView(somatoformDisorder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane12, gridBagConstraints);

        jLabel35.setFont(resourceMap.getFont("substanceAbuseFirstLabel.font")); // NOI18N
        jLabel35.setText(resourceMap.getString("substanceAbuseFirstLabel.text")); // NOI18N
        jLabel35.setName("substanceAbuseFirstLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel35, gridBagConstraints);

        jLabel30.setFont(resourceMap.getFont("substanceAbuseSecondLabel.font")); // NOI18N
        jLabel30.setText(resourceMap.getString("substanceAbuseSecondLabel.text")); // NOI18N
        jLabel30.setName("substanceAbuseSecondLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel30, gridBagConstraints);

        jScrollPane9.setMinimumSize(new java.awt.Dimension(200, 200));
        jScrollPane9.setName("jScrollPane9"); // NOI18N

        substanceAbuseDisorder.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Alcohol-related disorder", "Cannabis", "Cocaine", "Nicotine dependence", "Opioid", "Sedative-, hypnotic-, or anxiolytic", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        substanceAbuseDisorder.setMinimumSize(new java.awt.Dimension(200, 200));
        substanceAbuseDisorder.setName("substanceAbuseFirst"); // NOI18N
        jScrollPane9.setViewportView(substanceAbuseDisorder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane9, gridBagConstraints);

        jScrollPane11.setMinimumSize(new java.awt.Dimension(200, 200));
        jScrollPane11.setName("jScrollPane11"); // NOI18N

        substanceAbuseDisorder2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "-induced mood disorder", "-induced psychotic disorder with delusions", "-induced psychotic disorder with hallucinations", "Intoxication", "Withdrawal", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        substanceAbuseDisorder2.setName("substanceAbuseSecond"); // NOI18N
        jScrollPane11.setViewportView(substanceAbuseDisorder2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane11, gridBagConstraints);

        jLabel32.setFont(resourceMap.getFont("EpilepsyLabel.font")); // NOI18N
        jLabel32.setText(resourceMap.getString("EpilepsyLabel.text")); // NOI18N
        jLabel32.setName("EpilepsyLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel32, gridBagConstraints);

        jScrollPane13.setMinimumSize(new java.awt.Dimension(200, 150));
        jScrollPane13.setName("jScrollPane13"); // NOI18N

        epilepsy.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "With grand mal seizure", "With petit mal seizure", "Without seizure", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        epilepsy.setName("epilepsy"); // NOI18N
        epilepsy.setPreferredSize(new java.awt.Dimension(200, 200));
        jScrollPane13.setViewportView(epilepsy);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane13, gridBagConstraints);

        jLabel34.setFont(resourceMap.getFont("medicalConditionsLabel.font")); // NOI18N
        jLabel34.setText(resourceMap.getString("medicalConditionsLabel.text")); // NOI18N
        jLabel34.setName("medicalConditionsLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel34, gridBagConstraints);

        jScrollPane14.setName("jScrollPane14"); // NOI18N

        otherMedicalCondition.setColumns(20);
        otherMedicalCondition.setRows(5);
        otherMedicalCondition.setName("medicalConditions"); // NOI18N
        jScrollPane14.setViewportView(otherMedicalCondition);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane14, gridBagConstraints);

        jLabel36.setFont(resourceMap.getFont("secondaryConditionsLabel.font")); // NOI18N
        jLabel36.setText(resourceMap.getString("secondaryConditionsLabel.text")); // NOI18N
        jLabel36.setName("secondaryConditionsLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel36, gridBagConstraints);

        jScrollPane15.setName("jScrollPane15"); // NOI18N

        secondaryDiagnosis.setColumns(20);
        secondaryDiagnosis.setRows(5);
        secondaryDiagnosis.setName("secondaryConditions"); // NOI18N
        jScrollPane15.setViewportView(secondaryDiagnosis);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane15, gridBagConstraints);

        jScrollPane6.setMinimumSize(new java.awt.Dimension(313, 300));
        jScrollPane6.setName("jScrollPane6"); // NOI18N
        jScrollPane6.setPreferredSize(new java.awt.Dimension(380, 300));

        diagnosisPrimary.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Major depressive disorder, recurrent", "Bipolar disorder, most recent episode depressed", "Bipolar disorder, most recent episode manic", "Bipolar disorder, most recent episode mixed", "Mood disorder NOS", "Mood disorder due to medical condition", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        diagnosisPrimary.setMaximumSize(new java.awt.Dimension(313, 300));
        diagnosisPrimary.setMinimumSize(new java.awt.Dimension(313, 300));
        diagnosisPrimary.setName("moodDisorderFirst"); // NOI18N
        diagnosisPrimary.setPreferredSize(new java.awt.Dimension(313, 300));
        jScrollPane6.setViewportView(diagnosisPrimary);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        diagnosisPanel.add(jScrollPane6, gridBagConstraints);

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        diagnosisPanel.add(jLabel12, gridBagConstraints);

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        diagnosisPanel.add(jLabel15, gridBagConstraints);

        numberOfSeizuresPerMonth.setText(resourceMap.getString("numberOfSeizuresPerMonth.text")); // NOI18N
        numberOfSeizuresPerMonth.setInputVerifier(new IntegerInputVerifier());
        numberOfSeizuresPerMonth.setName("numberOfSeizuresPerMonth"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(numberOfSeizuresPerMonth, gridBagConstraints);

        jLabel53.setText(resourceMap.getString("jLabel53.text")); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jLabel53, gridBagConstraints);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        epilepticOtherSymptoms.setColumns(20);
        epilepticOtherSymptoms.setRows(5);
        epilepticOtherSymptoms.setName("epilepticOtherSymptoms"); // NOI18N
        jScrollPane3.setViewportView(epilepticOtherSymptoms);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        diagnosisPanel.add(jScrollPane3, gridBagConstraints);

        jScrollPane20.setMinimumSize(new java.awt.Dimension(200, 150));
        jScrollPane20.setName("jScrollPane20"); // NOI18N

        developmentalDisability.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Intellectual Disability", "Autism", "Other" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        developmentalDisability.setName("developmentalDisability"); // NOI18N
        developmentalDisability.setPreferredSize(new java.awt.Dimension(200, 200));
        jScrollPane20.setViewportView(developmentalDisability);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane20, gridBagConstraints);

        jScrollPane19.setName("jScrollPane19"); // NOI18N

        developmentalDisabilityOther.setColumns(20);
        developmentalDisabilityOther.setRows(5);
        developmentalDisabilityOther.setName("developmentalDisabilityOther"); // NOI18N
        jScrollPane19.setViewportView(developmentalDisabilityOther);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        diagnosisPanel.add(jScrollPane19, gridBagConstraints);

        jLabel54.setText(resourceMap.getString("jLabel54.text")); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        diagnosisPanel.add(jLabel54, gridBagConstraints);

        jScrollPane21.setMinimumSize(new java.awt.Dimension(200, 150));
        jScrollPane21.setName("jScrollPane21"); // NOI18N

        childhoodMentalHealthDisorder.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "ADHD", "Other" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        childhoodMentalHealthDisorder.setName("childhoodMentalHealthDisorder"); // NOI18N
        childhoodMentalHealthDisorder.setPreferredSize(new java.awt.Dimension(200, 200));
        jScrollPane21.setViewportView(childhoodMentalHealthDisorder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 25;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        diagnosisPanel.add(jScrollPane21, gridBagConstraints);

        jLabel55.setText(resourceMap.getString("jLabel55.text")); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        diagnosisPanel.add(jLabel55, gridBagConstraints);

        jLabel56.setText(resourceMap.getString("jLabel56.text")); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        diagnosisPanel.add(jLabel56, gridBagConstraints);

        jScrollPane22.setName("jScrollPane22"); // NOI18N

        childhoodMentalHealthDisorderOther.setColumns(20);
        childhoodMentalHealthDisorderOther.setRows(5);
        childhoodMentalHealthDisorderOther.setName("childhoodMentalHealthDisorderOther"); // NOI18N
        jScrollPane22.setViewportView(childhoodMentalHealthDisorderOther);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 25;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        diagnosisPanel.add(jScrollPane22, gridBagConstraints);

        jLabel57.setText(resourceMap.getString("jLabel57.text")); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        diagnosisPanel.add(jLabel57, gridBagConstraints);

        diagnosisScrollPane.setViewportView(diagnosisPanel);

        mainPanel.addTab(resourceMap.getString("diagnosisScrollPane.TabConstraints.tabTitle"), diagnosisScrollPane); // NOI18N

        treatmentOuterPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        treatmentOuterPanel.setName("treatmentOuterPanel"); // NOI18N
        treatmentOuterPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        treatmentPanel.setName("treatmentPanel"); // NOI18N
        treatmentPanel.setLayout(new java.awt.GridBagLayout());

        jLabel37.setFont(resourceMap.getFont("antidepressentMedicationLabel.font")); // NOI18N
        jLabel37.setText(resourceMap.getString("antidepressentMedicationLabel.text")); // NOI18N
        jLabel37.setName("antidepressentMedicationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel37, gridBagConstraints);

        jLabel38.setFont(resourceMap.getFont("antipsychoticMedicationLabel.font")); // NOI18N
        jLabel38.setText(resourceMap.getString("antipsychoticMedicationLabel.text")); // NOI18N
        jLabel38.setName("antipsychoticMedicationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel38, gridBagConstraints);

        jLabel39.setFont(resourceMap.getFont("sedativeMedicationLabel.font")); // NOI18N
        jLabel39.setText(resourceMap.getString("sedativeMedicationLabel.text")); // NOI18N
        jLabel39.setName("sedativeMedicationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel39, gridBagConstraints);

        fluoxetine.setText(resourceMap.getString("fluoxetine.text")); // NOI18N
        fluoxetine.setName("fluoxetine"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(fluoxetine, gridBagConstraints);

        escitalopram.setText(resourceMap.getString("escitalopram.text")); // NOI18N
        escitalopram.setName("escitalopram"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(escitalopram, gridBagConstraints);

        sertraline.setText(resourceMap.getString("sertraline.text")); // NOI18N
        sertraline.setName("sertraline"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(sertraline, gridBagConstraints);

        amitriptyline.setText(resourceMap.getString("amitriptyline.text")); // NOI18N
        amitriptyline.setName("amitriptyline"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(amitriptyline, gridBagConstraints);

        imipramine.setText(resourceMap.getString("imipramine.text")); // NOI18N
        imipramine.setName("imipramine"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(imipramine, gridBagConstraints);

        otherAntidepressant.setText(resourceMap.getString("antidepressantOther.text")); // NOI18N
        otherAntidepressant.setName("antidepressantOther"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(otherAntidepressant, gridBagConstraints);

        haloperidal.setText(resourceMap.getString("halperidal.text")); // NOI18N
        haloperidal.setName("halperidal"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(haloperidal, gridBagConstraints);

        haloperidalDecanoatInjection.setText(resourceMap.getString("haloperidalInjection.text")); // NOI18N
        haloperidalDecanoatInjection.setName("haloperidalInjection"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(haloperidalDecanoatInjection, gridBagConstraints);

        chlorpromazine.setText(resourceMap.getString("chlorpromazine.text")); // NOI18N
        chlorpromazine.setName("chlorpromazine"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(chlorpromazine, gridBagConstraints);

        fluphenazine.setText(resourceMap.getString("fluphenazine.text")); // NOI18N
        fluphenazine.setName("fluphenazine"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(fluphenazine, gridBagConstraints);

        fluphenazineDecanoateInjection.setText(resourceMap.getString("fluphenazineInjection.text")); // NOI18N
        fluphenazineDecanoateInjection.setName("fluphenazineInjection"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(fluphenazineDecanoateInjection, gridBagConstraints);

        risperidone.setText(resourceMap.getString("risperidone.text")); // NOI18N
        risperidone.setName("risperidone"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(risperidone, gridBagConstraints);

        risperidoneConstaInjection.setText(resourceMap.getString("risperidoneInjection.text")); // NOI18N
        risperidoneConstaInjection.setName("risperidoneInjection"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(risperidoneConstaInjection, gridBagConstraints);

        otherAntipsychotic.setText(resourceMap.getString("antipsychoticInjection.text")); // NOI18N
        otherAntipsychotic.setName("antipsychoticInjection"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(otherAntipsychotic, gridBagConstraints);

        clomipramine.setText(resourceMap.getString("clomipramine.text")); // NOI18N
        clomipramine.setName("clomipramine"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(clomipramine, gridBagConstraints);

        clonazepam.setText(resourceMap.getString("clonazepam.text")); // NOI18N
        clonazepam.setName("clonazepam"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(clonazepam, gridBagConstraints);

        diazepam.setText(resourceMap.getString("diazepam.text")); // NOI18N
        diazepam.setName("diazepam"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(diazepam, gridBagConstraints);

        lorazepam.setText(resourceMap.getString("lorazepam.text")); // NOI18N
        lorazepam.setName("lorazepam"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(lorazepam, gridBagConstraints);

        otherSedative.setText(resourceMap.getString("sedativeOther.text")); // NOI18N
        otherSedative.setName("sedativeOther"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(otherSedative, gridBagConstraints);

        jLabel40.setFont(resourceMap.getFont("moodStabilizerMedicationLabel.font")); // NOI18N
        jLabel40.setText(resourceMap.getString("moodStabilizerMedicationLabel.text")); // NOI18N
        jLabel40.setName("moodStabilizerMedicationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel40, gridBagConstraints);

        jLabel41.setFont(resourceMap.getFont("antiepilecticMedicationLabel.font")); // NOI18N
        jLabel41.setText(resourceMap.getString("antiepilecticMedicationLabel.text")); // NOI18N
        jLabel41.setName("antiepilecticMedicationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel41, gridBagConstraints);

        jLabel42.setFont(resourceMap.getFont("anticholinergicMedicationLabel.font")); // NOI18N
        jLabel42.setText(resourceMap.getString("anticholinergicMedicationLabel.text")); // NOI18N
        jLabel42.setName("anticholinergicMedicationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel42, gridBagConstraints);

        depakote.setText(resourceMap.getString("depakoteMoodStabilizer.text")); // NOI18N
        depakote.setName("depakoteMoodStabilizer"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(depakote, gridBagConstraints);

        lithium.setText(resourceMap.getString("lithium.text")); // NOI18N
        lithium.setName("lithium"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(lithium, gridBagConstraints);

        carbamazepine.setText(resourceMap.getString("carbamazepineMoodStabilizer.text")); // NOI18N
        carbamazepine.setName("carbamazepineMoodStabilizer"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(carbamazepine, gridBagConstraints);

        otherMoodStabilizer.setText(resourceMap.getString("moodStabilizerOther.text")); // NOI18N
        otherMoodStabilizer.setName("moodStabilizerOther"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(otherMoodStabilizer, gridBagConstraints);

        depakoteAntiepiletic.setText(resourceMap.getString("depakoteAntiepileptic.text")); // NOI18N
        depakoteAntiepiletic.setName("depakoteAntiepileptic"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(depakoteAntiepiletic, gridBagConstraints);

        carbamazepineAntiepileptic.setText(resourceMap.getString("carbamazepineAntiepileptic.text")); // NOI18N
        carbamazepineAntiepileptic.setName("carbamazepineAntiepileptic"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(carbamazepineAntiepileptic, gridBagConstraints);

        phenobarbital.setText(resourceMap.getString("phenobarbital.text")); // NOI18N
        phenobarbital.setName("phenobarbital"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(phenobarbital, gridBagConstraints);

        phenytoin.setText(resourceMap.getString("phenytoin.text")); // NOI18N
        phenytoin.setName("phenytoin"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(phenytoin, gridBagConstraints);

        otherAntiepileptic.setText(resourceMap.getString("antiepilepticOther.text")); // NOI18N
        otherAntiepileptic.setName("antiepilepticOther"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(otherAntiepileptic, gridBagConstraints);

        trihexyphenidyl.setText(resourceMap.getString("trihexyphenidyl.text")); // NOI18N
        trihexyphenidyl.setName("trihexyphenidyl"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(trihexyphenidyl, gridBagConstraints);

        otherAnticholinergic.setText(resourceMap.getString("anticholinergicOther.text")); // NOI18N
        otherAnticholinergic.setName("anticholinergicOther"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(otherAnticholinergic, gridBagConstraints);

        jLabel43.setFont(resourceMap.getFont("counselingLabel.font")); // NOI18N
        jLabel43.setText(resourceMap.getString("counselingLabel.text")); // NOI18N
        jLabel43.setName("counselingLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel43, gridBagConstraints);

        jLabel44.setFont(resourceMap.getFont("individualCounselingLabel.font")); // NOI18N
        jLabel44.setText(resourceMap.getString("individualCounselingLabel.text")); // NOI18N
        jLabel44.setName("individualCounselingLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel44, gridBagConstraints);

        jLabel45.setFont(resourceMap.getFont("familyEducationLabel.font")); // NOI18N
        jLabel45.setText(resourceMap.getString("familyEducationLabel.text")); // NOI18N
        jLabel45.setName("familyEducationLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jLabel45, gridBagConstraints);

        jScrollPane16.setName("jScrollPane16"); // NOI18N

        individualCounseling.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "CBT", "DBT", "Motivational interviewing", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        individualCounseling.setName("individualCounseling"); // NOI18N
        jScrollPane16.setViewportView(individualCounseling);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jScrollPane16, gridBagConstraints);

        jScrollPane17.setName("jScrollPane17"); // NOI18N

        familyPsychoEducation.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Support group", "Other", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        familyPsychoEducation.setName("familyEducation"); // NOI18N
        jScrollPane17.setViewportView(familyPsychoEducation);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(jScrollPane17, gridBagConstraints);

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        treatmentPanel.add(jLabel13, gridBagConstraints);

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        treatmentPanel.add(jLabel22, gridBagConstraints);

        medicationsNotAvailable.setText(resourceMap.getString("medicationsNotAvailable.text")); // NOI18N
        medicationsNotAvailable.setName("medicationsNotAvailable"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(medicationsNotAvailable, gridBagConstraints);

        counseling1.setText(resourceMap.getString("counseling1.text")); // NOI18N
        counseling1.setName("counseling1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(counseling1, gridBagConstraints);

        counseling2.setText(resourceMap.getString("counseling2.text")); // NOI18N
        counseling2.setName("counseling2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(counseling2, gridBagConstraints);

        counseling3.setText(resourceMap.getString("counseling3.text")); // NOI18N
        counseling3.setName("counseling3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(counseling3, gridBagConstraints);

        counseling4.setText(resourceMap.getString("counseling4.text")); // NOI18N
        counseling4.setName("counseling4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(counseling4, gridBagConstraints);

        counseling5.setText(resourceMap.getString("counseling5.text")); // NOI18N
        counseling5.setName("counseling5"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        treatmentPanel.add(counseling5, gridBagConstraints);

        treatmentOuterPanel.add(treatmentPanel);

        mainPanel.addTab(resourceMap.getString("treatmentOuterPanel.TabConstraints.tabTitle"), treatmentOuterPanel); // NOI18N

        dischargeOuterPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        dischargeOuterPanel.setName("dischargeOuterPanel"); // NOI18N
        dischargeOuterPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        dischargePanel.setName("dischargePanel"); // NOI18N
        dischargePanel.setLayout(new java.awt.GridBagLayout());

        jLabel46.setFont(resourceMap.getFont("followupCareMedicationsLabel.font")); // NOI18N
        jLabel46.setText(resourceMap.getString("followupCareMedicationsLabel.text")); // NOI18N
        jLabel46.setName("followupCareMedicationsLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(jLabel46, gridBagConstraints);

        jLabel47.setFont(resourceMap.getFont("followupCareCounselingLabel.font")); // NOI18N
        jLabel47.setText(resourceMap.getString("followupCareCounselingLabel.text")); // NOI18N
        jLabel47.setName("followupCareCounselingLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(jLabel47, gridBagConstraints);

        followupCareMedicationsGroup.add(followUpCareMedication1);
        followUpCareMedication1.setText(resourceMap.getString("followupCareMedications1.text")); // NOI18N
        followUpCareMedication1.setName("followupCareMedications1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(followUpCareMedication1, gridBagConstraints);

        followupCareMedicationsGroup.add(followUpCareMedication2);
        followUpCareMedication2.setText(resourceMap.getString("followupCareMedications2.text")); // NOI18N
        followUpCareMedication2.setName("followupCareMedications2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(followUpCareMedication2, gridBagConstraints);

        followupCareCounselingGroup.add(followUpCareCounseling1);
        followUpCareCounseling1.setText(resourceMap.getString("followupCareCounseling1.text")); // NOI18N
        followUpCareCounseling1.setName("followupCareCounseling1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(followUpCareCounseling1, gridBagConstraints);

        followupCareCounselingGroup.add(followUpCareCounseling2);
        followUpCareCounseling2.setText(resourceMap.getString("followupCareCounseling2.text")); // NOI18N
        followUpCareCounseling2.setName("followupCareCounseling2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(followUpCareCounseling2, gridBagConstraints);

        jLabel48.setFont(resourceMap.getFont("dispositionLabel.font")); // NOI18N
        jLabel48.setText(resourceMap.getString("dispositionLabel.text")); // NOI18N
        jLabel48.setName("dispositionLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(jLabel48, gridBagConstraints);

        jLabel49.setFont(resourceMap.getFont("permissionToReportLabel.font")); // NOI18N
        jLabel49.setText(resourceMap.getString("permissionToReportLabel.text")); // NOI18N
        jLabel49.setName("permissionToReportLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(jLabel49, gridBagConstraints);

        jScrollPane18.setName("jScrollPane18"); // NOI18N

        dischargeDisposition.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Home", "Hospital", "Psychiatric facility", "Prison", "Other", "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        dischargeDisposition.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dischargeDisposition.setName("disposition"); // NOI18N
        jScrollPane18.setViewportView(dischargeDisposition);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(jScrollPane18, gridBagConstraints);

        permissionToReportGroup.add(reportData1);
        reportData1.setText(resourceMap.getString("permissionToReport1.text")); // NOI18N
        reportData1.setName("permissionToReport1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(reportData1, gridBagConstraints);

        permissionToReportGroup.add(reportData2);
        reportData2.setText(resourceMap.getString("permissionToReport2.text")); // NOI18N
        reportData2.setName("permissionToReport2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        dischargePanel.add(reportData2, gridBagConstraints);

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        dischargePanel.add(jLabel14, gridBagConstraints);

        dischargeOuterPanel.add(dischargePanel);

        mainPanel.addTab(resourceMap.getString("dischargeOuterPanel.TabConstraints.tabTitle"), dischargeOuterPanel); // NOI18N

        mainPanel.getAccessibleContext().setAccessibleName(resourceMap.getString("basicInformationTab.AccessibleContext.accessibleName")); // NOI18N

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

private void generatePatientIDClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generatePatientIDClicked
    // TODO: THIS FUCNTION NOT USED, SHOULD BE REMOVED
}//GEN-LAST:event_generatePatientIDClicked

@Action
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (generatePatientIDBox == null) {
        JFrame mainFrame = MentalHealthLiberiaApp.getApplication().getMainFrame();
        generatePatientIDBox = new MentalHealthLiberiaPatientID(mainFrame, this);
        generatePatientIDBox.setLocationRelativeTo(mainFrame);
    }
    MentalHealthLiberiaApp.getApplication().show(generatePatientIDBox);
}//GEN-LAST:event_jButton1ActionPerformed

private String extractValue(Object component) {
    if (component instanceof JList) {
        if (((JList)component).getSelectedIndex() >= 0) {
            return ((JList)component).getSelectedValue().toString();
        }
    } else if (component instanceof JComboBox) {
        if (((JComboBox)component).getSelectedIndex() >= 0) {
            return ((JComboBox)component).getSelectedItem().toString();
        }
    } else if (component instanceof JTextField) {
        return ((JTextField)component).getText();
    } else if (component instanceof ButtonGroup) {
        ButtonGroup group = (ButtonGroup)component;
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
    }
    return "";
}

private boolean extractBoolean(Object box) {
    if (box instanceof JCheckBox) {
        return ((JCheckBox)box).isSelected();
    } else if (box instanceof ButtonGroup) {
        String result = "false";
        ButtonGroup group = (ButtonGroup)box;
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                result = button.getText();
            }
        }
        return result.equalsIgnoreCase("Yes");
    }
    return false;
}

private String dateOfBirth = ""; // hash of date of birth
private String fathersName = ""; // hash of father's name
private String placeOfBirth = ""; // hash of place of birth
private String gender = ""; // one of Male or Female
private String patientName = ""; // hash of patient name

private String password; // used for authentication, not stored

public void setPassword(String password) {
    this.password = password;
}

public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
}

public void setFathersName(String fathersName) {
    this.fathersName = fathersName;
}

public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
}

public void setGender(String gender) {
    this.gender = gender;
}

public void setPatientsName(String patientName) {
    this.patientName = patientName;
}

public void patientIDNotGenerated() {
    this.patientID.setText("Not generated");
}

public void patientIDGenerated() {
    this.patientID.setText("Generated");
}

private boolean fieldIsCompleted(String val) {
    if (val == null || val.equals("")) {
        return false;
    }
    return true;
}

private int[] convertToIntArray(List<Integer> list) {
    if (list == null) {
        return new int[0];
    } else {
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }
}

private int[] convertStringToIntArray(String csv, String[] values) {
    if (fieldIsCompleted(csv)) {
        List<Integer> indicies = new ArrayList<Integer>();
        String[] strings = csv.split("[,]");
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (strings[i].equals(values[j])) {
                    indicies.add(j);
                }
            }
        }
        return convertToIntArray(indicies);
    } else {
        return new int[0];
    }
}

private void loadFormValues(PatientEncounterForm formData) {
    
    // Basic Information
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    this.dateOfService.setText(format.format(formData.getDateOfService()));
    this.clinicianID.setText(formData.getClinicianID());
    this.locationOfService.setText(formData.getLocationOfService());
    this.reasonForVisit1.setSelected(formData.isReasonSeekingMentalHealthCare());
    this.reasonForVisit2.setSelected(formData.isReasonOther());
    this.clinicianTrainingLevel.setSelectedValue(formData.getClinicianTrainingLevel(), true);
    this.referralSource.setSelectedValue(formData.getReferralSource(), true);
        
    // Patient Demographics (used for generating unique ID)
    if (!fieldIsCompleted(formData.getPatientName()) ||
        !fieldIsCompleted(formData.getDateOfBirth()) ||
        !fieldIsCompleted(formData.getGender()) ||
        !fieldIsCompleted(formData.getFathersName()) ||
        !fieldIsCompleted(formData.getPlaceOfBirth())) {
        patientIDNotGenerated();
    } else {
        this.patientName = formData.getPatientName();
        this.dateOfBirth = formData.getDateOfBirth();
        this.gender = formData.getGender();
        this.fathersName = formData.getFathersName();
        this.placeOfBirth = formData.getPlaceOfBirth();
        patientIDGenerated();
    }
    
    // Patient Demographics
    this.age.setText(formData.getAge());
    this.countyOfResidence.setSelectedItem(formData.getCountyOfResidence());
    this.distanceTraveled.setText(formData.getDistanceTraveled());
    this.numberOfDaysInLifeRole.setText("" + formData.getNumberOfDaysInLifeRole());
    this.maritalStatus.setSelectedValue(formData.getMaritalStatus(), true);
    this.education.setSelectedValue(formData.getEducation(), true);
    
    // Symptoms and Functioning
    this.phq.setText(formData.getPhq());
    this.gaf.setText(formData.getGaf());
    this.cage.setText(formData.getCage());
    this.who_das.setText(formData.getWhoDas());
    this.euroqol.setText(formData.getEuroqol());
    this.otherSymptomsScore.setText(formData.getOtherSymptomsScore());
    
    // Diagnosis
    this.diagnosisPrimary.setSelectedIndices(
            convertStringToIntArray(
                    formData.getDiagnosisPrimary(),
                    new String[] {
                        "Major depressive disorder, recurrent",
                        "Bipolar disorder, most recent episode depressed",
                        "Bipolar disorder, most recent episode manic",
                        "Bipolar disorder, most recent episode mixed",
                        "Mood disorder NOS",
                        "Mood disorder due to medical condition",
                        "None"}));
    this.moodDisorder.setSelectedIndices(
            convertStringToIntArray(
                    formData.getMoodDisorder(),
                    new String[] {
                        "Full remission",
                        "Partial remission",
                        "Mild",
                        "Moderate",
                        "Severe without psychotic features",
                        "Severe with psychotic features",
                        "None"}));
    this.anxietyDisorder.setSelectedIndices(
            convertStringToIntArray(
                    formData.getAnxietyDisorder(),
                    new String[] {
                        "Generalized anxiety disorder",
                        "Panic disorder with agoraphobia",
                        "Panic disorder without agoraphobia",
                        "Agoraphobia without history of panic disorder",
                        "Specific phobia",
                        "Social phobia",
                        "Obsessive compulsive disorder",
                        "Post-traumatic stress disorder",
                        "Acute stress",
                        "Anxiety disorder due to general medical condition",
                        "Anxiety disorder due to specific medical condition",
                        "Anxiety disoder due to NOS",
                        "None"}));
    this.psychoticDisorder.setSelectedIndices(
            convertStringToIntArray(
                    formData.getPsychoticDisorder(),
                    new String[] {
                        "Schizophrenia catatonic type",
                        "Schizophrenia disorganized type",
                        "Schizophrenia paranoid type",
                        "Schizophrenia residual type",
                        "Schizophrenia undifferentiated type",
                        "Schizoaffective disorder",
                        "Psychotic disorder due to medical condition with delusions",
                        "Psychotic disorder due to medical condition with hallucinations",
                        "Psychotic disorder NOS",
                        "None"}));
    this.somatoformDisorder.setSelectedIndices(
            convertStringToIntArray(
                    formData.getSomatoformDisorder(),
                    new String[] {
                        "Somatization disorder",
                        "Undifferentiated somatoform disorder",
                        "Conversion disorder",
                        "Pain disorder with both psychological factors and general medical condition",
                        "Pain disorder with psychological factors",
                        "Hypochondriasis",
                        "Body dysmorphic disorder",
                        "Somatoform disorder NOS",
                        "None"}));
    this.substanceAbuseDisorder.setSelectedIndices(
            convertStringToIntArray(
                    formData.getSubstanceAbuseDisorder(),
                    new String[] {
                        "Alcohol-related disorder",
                        "Cannabis",
                        "Cocaine",
                        "Nicotine dependence",
                        "Opioid",
                        "Sedative-, hypnotic-, or anxiolytic",
                        "None"}));
    this.substanceAbuseDisorder2.setSelectedIndices(
            convertStringToIntArray(
                    formData.getSubstanceAbuseDisorder2(),
                    new String[] {
                        "-induced mood disorder",
                        "-induced psychotic disorder with delusions",
                        "-induced psychotic disorder with hallucinations",
                        "Intoxication",
                        "Withdrawal",
                        "None"}));
    this.epilepsy.setSelectedIndices(
            convertStringToIntArray(
                    formData.getEpilepsy(),
                    new String[] {
                        "With grand mal seizure",
                        "With petit mal seizure",
                        "Without seizure",
                        "None"}));
    this.developmentalDisability.setSelectedIndices(
            convertStringToIntArray(
                    formData.getDevelopmentalDisability(),
                    new String[] {
                        "Intellectual Disability",
                        "Autism",
                        "Other"}));
    this.childhoodMentalHealthDisorder.setSelectedIndices(
            convertStringToIntArray(
                    formData.getChildhoodMentalHealthDisorder(),
                    new String[] {
                        "ADHD",
                        "Other"}));
    this.otherMedicalCondition.setText(formData.getOtherMedicalCondition());
    this.secondaryDiagnosis.setText(formData.getSecondaryDiagnosis());
    this.numberOfSeizuresPerMonth.setText(formData.getNumberOfSeizuresPerMonth());
    this.developmentalDisabilityOther.setText(formData.getDevelopmentalDisabilityOther());
    this.childhoodMentalHealthDisorderOther.setText(formData.getChildhoodMentalHealthDisorderOther());
    
    // Treatment
    this.fluoxetine.setSelected(formData.isFluoxetine());
    this.escitalopram.setSelected(formData.isEscitalopram());
    this.sertraline.setSelected(formData.isSertraline());
    this.amitriptyline.setSelected(formData.isAmitriptyline());
    this.imipramine.setSelected(formData.isImipramine());
    this.otherAntidepressant.setSelected(formData.isOtherAntidepressant());
    this.haloperidal.setSelected(formData.isHaloperidal());
    this.haloperidalDecanoatInjection.setSelected(formData.isHaloperidalDecanoateInjection());
    this.chlorpromazine.setSelected(formData.isChlorpromazine());
    this.fluphenazine.setSelected(formData.isFluphenazine());
    this.fluphenazineDecanoateInjection.setSelected(formData.isFluphenazineDecanoateInjection());
    this.risperidone.setSelected(formData.isRisperidone());
    this.risperidoneConstaInjection.setSelected(formData.isRisperidoneConstaInjection());
    this.otherAntipsychotic.setSelected(formData.isOtherAntipsychotic());
    
    this.clomipramine.setSelected(formData.isClomipramine());
    this.clonazepam.setSelected(formData.isClonazepam());
    this.diazepam.setSelected(formData.isDiazepam());
    this.lorazepam.setSelected(formData.isLorazepam());
    this.otherSedative.setSelected(formData.isOtherSedative());
    
    this.depakote.setSelected(formData.isDepakote());
    this.lithium.setSelected(formData.isLithium());
    this.carbamazepine.setSelected(formData.isCarbamazepine());
    this.otherMoodStabilizer.setSelected(formData.isOtherMoodStabilizer());
    
    this.depakoteAntiepiletic.setSelected(formData.isDepakoteAntiepileptic());
    this.carbamazepineAntiepileptic.setSelected(formData.isCarbmazepineAntiepileptic());
    this.phenobarbital.setSelected(formData.isPhenobarbital());
    this.phenytoin.setSelected(formData.isPhenytoin());
    this.otherAntiepileptic.setSelected(formData.isOtherAntiepileptic());
    
    this.trihexyphenidyl.setSelected(formData.isTrihexyphenidyl());
    this.otherAnticholinergic.setSelected(formData.isOtherAnticholinergic());
    
    this.counseling1.setSelected(formData.isCounselingIndividual());
    this.counseling2.setSelected(formData.isCounselingGroup());
    this.counseling3.setSelected(formData.isCounselingFamilyPsychoEducational());
    this.counseling4.setSelected(formData.isCounselingFamilyTherapy());
    this.counseling5.setSelected(formData.isCounselingNone());
    
    this.individualCounseling.setSelectedIndices(
            convertStringToIntArray(
                    formData.getDiagnosisPrimary(),
                    new String[] {
                        "CBT",
                        "DBT",
                        "Motivational interviewing",
                        "None"}));
    this.familyPsychoEducation.setSelectedIndices(
            convertStringToIntArray(
                    formData.getDiagnosisPrimary(),
                    new String[] {
                        "Support group",
                        "Other",
                        "None"}));
    
    this.medicationsNotAvailable.setText(formData.getMedicationsNotAvailable());
    
    // Discharge
    this.followUpCareMedication1.setSelected(false);
    this.followUpCareMedication2.setSelected(false);
    if (formData.isFollowUpCareMedication()) {
        this.followUpCareMedication1.setSelected(true);
    } else {
        this.followUpCareMedication2.setSelected(true);
    }
    this.followUpCareCounseling1.setSelected(false);
    this.followUpCareCounseling2.setSelected(false);
    if (formData.isFollowUpCareCounseling()) {
        this.followUpCareCounseling1.setSelected(true);
    } else {
        this.followUpCareCounseling2.setSelected(true);
    }
    this.dischargeDisposition.setSelectedValue(formData.getDischargeDisposition(), true);
    
    this.reportData1.setSelected(false);
    this.reportData2.setSelected(false);
    if (formData.isReportData()) {
        this.reportData1.setSelected(true);
    } else {
        this.reportData2.setSelected(true);
    }
}

private PatientEncounterForm buildForm() {
    
    
    PatientEncounterForm formData = new PatientEncounterForm();
    
    // Basic Information
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    try {
        if (this.dateOfService.getText() != null && !this.dateOfService.getText().equals("")) {
            formData.setDateOfService(format.parse(this.dateOfService.getText()));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    formData.setClinicianID(extractValue(this.clinicianID));
    formData.setLocationOfService(extractValue(this.locationOfService));
    formData.setReasonSeekingMentalHealthCare(extractBoolean(this.reasonForVisit1));
    formData.setReasonOther(extractBoolean(this.reasonForVisit2));
    formData.setClinicianTrainingLevel(extractValue(this.clinicianTrainingLevel));
    formData.setReferralSource(extractValue(this.referralSource));
    
    // Patient Demographics (used for generating unique ID)
    formData.setPatientName(this.patientName);
    formData.setDateOfBirth(this.dateOfBirth);
    formData.setGender(this.gender);
    formData.setFathersName(this.fathersName);
    formData.setPlaceOfBirth(this.placeOfBirth);
    
    // Patient Demographics
    formData.setAge(extractValue(this.age));
    formData.setCountyOfResidence(extractValue(this.countyOfResidence));
    formData.setDistanceTraveled(extractValue(this.distanceTraveled));
    formData.setNumberOfDaysInLifeRole(extractValue(this.numberOfDaysInLifeRole));
    formData.setMaritalStatus(extractValue(this.maritalStatus));
    formData.setEducation(extractValue(this.education));
    
    // Symptoms and Functioning
    formData.setPhq(extractValue(this.phq));
    formData.setGaf(extractValue(this.gaf));
    formData.setCage(extractValue(this.cage));
    formData.setWhoDas(extractValue(this.who_das));
    formData.setEuroqol(extractValue(this.euroqol));
    formData.setOtherSymptomsScore(extractValue(this.otherSymptomsScore));
    
    // Diagnosis
    formData.setDiagnosisPrimary(extractValue(this.diagnosisPrimary));
    formData.setMoodDisorder(extractValue(this.moodDisorder));
    formData.setAnxietyDisorder(extractValue(this.anxietyDisorder));
    formData.setPsychoticDisorder(extractValue(this.psychoticDisorder));
    formData.setSomatoformDisorder(extractValue(this.somatoformDisorder));
    formData.setSubstanceAbuseDisorder(extractValue(this.substanceAbuseDisorder));
    formData.setSubstanceAbuseDisorder2(extractValue(this.substanceAbuseDisorder2));
    formData.setEpilepsy(extractValue(this.epilepsy));
    formData.setNumberOfSeizuresPerMonth(extractValue(this.numberOfSeizuresPerMonth));
    formData.setEpilepticOtherSymptoms(extractValue(this.epilepticOtherSymptoms));
    formData.setDevelopmentalDisability(extractValue(this.developmentalDisability));
    formData.setDevelopmentalDisabilityOther(extractValue(this.developmentalDisabilityOther));
    formData.setChildhoodMentalHealthDisorder(extractValue(this.childhoodMentalHealthDisorder));
    formData.setChildhoodMentalHealthDisorderOther(extractValue(this.childhoodMentalHealthDisorderOther));
    formData.setOtherMedicalCondition(extractValue(this.otherMedicalCondition));
    formData.setSecondaryDiagnosis(extractValue(this.secondaryDiagnosis));
    
    // Treatment
    formData.setFluoxetine(extractBoolean(this.fluoxetine));
    formData.setEscitalopram(extractBoolean(this.escitalopram));
    formData.setSertraline(extractBoolean(this.sertraline));
    formData.setAmitriptyline(extractBoolean(this.amitriptyline));
    formData.setImipramine(extractBoolean(this.imipramine));
    formData.setOtherAntidepressant(extractBoolean(this.otherAntidepressant));
    formData.setHaloperidal(extractBoolean(this.haloperidal));
    formData.setHaloperidalDecanoateInjection(extractBoolean(this.haloperidalDecanoatInjection));
    formData.setChlorpromazine(extractBoolean(this.chlorpromazine));
    formData.setFluphenazine(extractBoolean(this.fluphenazine));
    formData.setFluphenazineDecanoateInjection(extractBoolean(this.fluphenazineDecanoateInjection));
    formData.setRisperidone(extractBoolean(this.risperidone));
    formData.setRisperidoneConstaInjection(extractBoolean(this.risperidoneConstaInjection));
    formData.setOtherAntipsychotic(extractBoolean(this.otherAntipsychotic));
    formData.setClomipramine(extractBoolean(this.clomipramine));
    formData.setDiazepam(extractBoolean(this.diazepam));
    formData.setLorazepam(extractBoolean(this.lorazepam));
    formData.setOtherSedative(extractBoolean(this.otherSedative));
    formData.setDepakote(extractBoolean(this.depakote));
    formData.setLithium(extractBoolean(this.lithium));
    formData.setCarbamazepine(extractBoolean(this.carbamazepine));
    formData.setOtherMoodStabilizer(extractBoolean(this.otherMoodStabilizer));
    formData.setDepakoteAntiepileptic(extractBoolean(this.depakoteAntiepiletic));
    formData.setCarbmazepineAntiepileptic(extractBoolean(this.carbamazepineAntiepileptic));
    formData.setPhenobarbital(extractBoolean(this.phenobarbital));
    formData.setPhenytoin(extractBoolean(this.phenytoin));
    formData.setOtherAntiepileptic(extractBoolean(this.otherAntiepileptic));
    formData.setTrihexyphenidyl(extractBoolean(this.trihexyphenidyl));
    formData.setOtherAnticholinergic(extractBoolean(this.otherAnticholinergic));
    formData.setCounselingIndividual(extractBoolean(this.counseling1));
    formData.setCounselingGroup(extractBoolean(this.counseling2));
    formData.setCounselingFamilyPsychoEducational(extractBoolean(this.counseling3));
    formData.setCounselingFamilyTherapy(extractBoolean(this.counseling4));
    formData.setCounselingNone(extractBoolean(this.counseling5));
    formData.setIndividualCounseling(extractValue(this.individualCounseling));
    formData.setFamilyPsychoEducation(extractValue(this.familyPsychoEducation));
    formData.setMedicationsNotAvailable(this.medicationsNotAvailable.getText());
    
    // Discharge
    formData.setFollowUpCareMedication(extractBoolean(this.followupCareMedicationsGroup));
    formData.setFollowUpCareCounseling(extractBoolean(this.followupCareCounselingGroup));
    formData.setDischargeDisposition(extractValue(this.dischargeDisposition));
    formData.setReportData(extractBoolean(this.permissionToReportGroup));
    
    return formData;
}

public boolean saveForm() {
    Object[] options = {
        "Cancel Action, go back and fix form",
        "Continue without saving the form"
    };
    int choice = -1;
    
    if (this.patientID.getText().equalsIgnoreCase("not generated")) {
        choice = JOptionPane.showOptionDialog(
                this.getFrame(),
                "You must generate a patient ID before saving",
                "Form not completed",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]);
    
        if (choice == JOptionPane.CLOSED_OPTION || choice == 0) {
            System.out.println("Going back to fix");
            return false;
        }
        return true;
    }
    if (this.clinicianID.getText().equalsIgnoreCase("")) {
        choice = JOptionPane.showOptionDialog(
                this.getFrame(),
                "You must enter your Clinician ID before saving",
                "Form not completed",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]);
    
        if (choice == JOptionPane.CLOSED_OPTION || choice == 0) {
            System.out.println("Going back to fix");
            return false;
        }
        return true;
    }
    
    PatientEncounterForm formData = buildForm();
    if (dataStore.saveForm(formData)) {
        clearForm();
    } else {
        choice = JOptionPane.showOptionDialog(
                this.getFrame(),
                "An error occurred while saving the form, please try again or save to PDF. "
                + "There may be a problem saving to the 'data' directory. Please find the "
                + "app.properties file that is being loaded, and check the 'data_dir' property "
                + "to make sure that it is pointing to a folder that is created.",
                "Form not completed",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]);
    
        if (choice == JOptionPane.CANCEL_OPTION || choice == 1) {
            System.out.println("Going back to fix");
            return false;
        }
        return true;
    }
    
    System.out.println("Form saved");
    return true;
}

private void uploadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadMenuItemActionPerformed
    if (saveForm()) {
        if (uploadFormsBox == null) {
            JFrame mainFrame = MentalHealthLiberiaApp.getApplication().getMainFrame();
            uploadFormsBox = new MentalHealthLiberiaUploadView(mainFrame, this);
            uploadFormsBox.setLocationRelativeTo(mainFrame);
        }
        MentalHealthLiberiaApp.getApplication().show(uploadFormsBox);
    }
}//GEN-LAST:event_uploadMenuItemActionPerformed

// package scoped
void uploadFiles(String username, String password) {
    DataStore.FileStatus status = dataStore.uploadFiles(username, password);
    
    // notify user of result
    if (status == DataStore.FileStatus.READY) {
        JOptionPane.showMessageDialog(
                this.getFrame(),
                "Forms uploaded successfully.");
        
    } else {
        JOptionPane.showMessageDialog(
                this.getFrame(),
                "Failed to upload forms, please try again later.",
                "Upload Error",
                JOptionPane.ERROR_MESSAGE);
    }
}

private void clearField(Object field) {
    if (field instanceof JTextField) {
        ((JTextField)field).setText("");
    } else if (field instanceof ButtonGroup) {
        ((ButtonGroup)field).clearSelection();
    } else if (field instanceof JCheckBox) {
        ((JCheckBox)field).setSelected(false);
    } else if (field instanceof JList) {
        ((JList)field).clearSelection();
    }
}

private void clearForm() {
    // reset patient ID
    this.patientID.setText("Not generated");

    // Basic Information
    clearField(this.dateOfService);
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    this.dateOfService.setText(format.format(new Date()));
    clearField(this.clinicianID);
    clearField(this.locationOfService);
    clearField(this.reasonForVisit);
    clearField(this.clinicianTrainingLevel);
    clearField(this.referralSource);

    // Patient Demographics (used to generate unique ID)
    this.gender = "";
    this.fathersName = "";
    this.placeOfBirth = "";
    this.dateOfBirth = "";
    this.patientName = "";

    // Patient Demographics
    clearField(this.age);
    clearField(this.countyOfResidence);
    clearField(this.distanceTraveled);
    clearField(this.numberOfDaysInLifeRole);

    // Symptoms and Functioning
    clearField(this.phq);
    clearField(this.gaf);
    clearField(this.cage);
    clearField(this.who_das);
    clearField(this.euroqol);
    clearField(this.otherSymptomsScore);

    // Diagnosis
    clearField(this.diagnosisPrimary);
    clearField(this.moodDisorder);
    clearField(this.anxietyDisorder);
    clearField(this.psychoticDisorder);
    clearField(this.somatoformDisorder);
    clearField(this.substanceAbuseDisorder);
    clearField(this.substanceAbuseDisorder2);
    clearField(this.epilepsy);
    clearField(this.numberOfSeizuresPerMonth);
    clearField(this.epilepticOtherSymptoms);
    clearField(this.developmentalDisability);
    clearField(this.developmentalDisabilityOther);
    clearField(this.childhoodMentalHealthDisorder);
    clearField(this.childhoodMentalHealthDisorderOther);
    clearField(this.otherMedicalCondition);
    clearField(this.secondaryDiagnosis);

    //  Treatment
    clearField(this.fluoxetine);
    clearField(this.escitalopram);
    clearField(this.sertraline);
    clearField(this.amitriptyline);
    clearField(this.imipramine);
    clearField(this.otherAntidepressant);
    clearField(this.haloperidal);
    clearField(this.haloperidalDecanoatInjection);
    clearField(this.chlorpromazine);
    clearField(this.fluphenazine);
    clearField(this.fluphenazineDecanoateInjection);
    clearField(this.risperidone);
    clearField(this.risperidoneConstaInjection);
    clearField(this.otherAntipsychotic);
    clearField(this.clomipramine);
    clearField(this.clonazepam);
    clearField(this.diazepam);
    clearField(this.lorazepam);
    clearField(this.otherSedative);
    clearField(this.depakote);
    clearField(this.lithium);
    clearField(this.carbamazepine);
    clearField(this.otherMoodStabilizer);
    clearField(this.depakoteAntiepiletic);
    clearField(this.carbamazepineAntiepileptic);
    clearField(this.phenobarbital);
    clearField(this.phenytoin);
    clearField(this.otherAntiepileptic);
    clearField(this.trihexyphenidyl);
    clearField(this.otherAnticholinergic);
    clearField(this.counseling1);
    clearField(this.counseling2);
    clearField(this.counseling3);
    clearField(this.counseling4);
    clearField(this.counseling5);
    clearField(this.individualCounseling);
    clearField(this.familyPsychoEducation);
    clearField(this.medicationsNotAvailable);

    // Discharge
    clearField(this.followupCareMedicationsGroup);
    clearField(this.followupCareCounselingGroup);
    clearField(this.dischargeDisposition);
    clearField(this.permissionToReportGroup);
}

private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
    if (saveForm()) {
        // show confirmation dialog
        int option = JOptionPane.showConfirmDialog(
                this.getFrame(),
                "Any un-saved information will be lost, continue?",
                "New Form Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            System.out.println("Clearing form");
            clearForm();
        }
    }
}//GEN-LAST:event_newMenuItemActionPerformed

private void saveAsPdfMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsPdfMenuItemActionPerformed
    PatientEncounterForm form = buildForm();
    if (saveForm()) {
        PatientEncounterFormController.getInstance().generatePdf(form, this.getFrame());
    }
}//GEN-LAST:event_saveAsPdfMenuItemActionPerformed

public void showForm(PatientEncounterForm form) {
    loadFormValues(form);
}

private void browseFormsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseFormsMenuItemActionPerformed
    if (saveForm()) {
        List<PatientEncounterForm> forms = dataStore.getPendingForms();
        BrowseFormsDialog dialog = new BrowseFormsDialog(this, true, forms);
        dialog.setVisible(true);
    }
}//GEN-LAST:event_browseFormsMenuItemActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
   if (saveForm()) {
       this.getFrame().dispose();
       System.exit(0);
   }
}//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField age;
    private javax.swing.JCheckBox amitriptyline;
    private javax.swing.JList anxietyDisorder;
    private javax.swing.JPanel basicInformationOuterPanel;
    private javax.swing.JPanel basicInformationPanel;
    private javax.swing.JMenuItem browseFormsMenuItem;
    private javax.swing.JFormattedTextField cage;
    private javax.swing.JCheckBox carbamazepine;
    private javax.swing.JCheckBox carbamazepineAntiepileptic;
    private javax.swing.JList childhoodMentalHealthDisorder;
    private javax.swing.JTextArea childhoodMentalHealthDisorderOther;
    private javax.swing.JCheckBox chlorpromazine;
    private javax.swing.JTextField clinicianID;
    private javax.swing.JList clinicianTrainingLevel;
    private javax.swing.JCheckBox clomipramine;
    private javax.swing.JCheckBox clonazepam;
    private javax.swing.JCheckBox counseling1;
    private javax.swing.JCheckBox counseling2;
    private javax.swing.JCheckBox counseling3;
    private javax.swing.JCheckBox counseling4;
    private javax.swing.JCheckBox counseling5;
    private javax.swing.JComboBox countyOfResidence;
    private javax.swing.JFormattedTextField dateOfService;
    private javax.swing.JCheckBox depakote;
    private javax.swing.JCheckBox depakoteAntiepiletic;
    private javax.swing.JList developmentalDisability;
    private javax.swing.JTextArea developmentalDisabilityOther;
    private javax.swing.JPanel diagnosisPanel;
    private javax.swing.JList diagnosisPrimary;
    private javax.swing.JScrollPane diagnosisScrollPane;
    private javax.swing.JCheckBox diazepam;
    private javax.swing.JList dischargeDisposition;
    private javax.swing.JPanel dischargeOuterPanel;
    private javax.swing.JPanel dischargePanel;
    private javax.swing.JTextField distanceTraveled;
    private javax.swing.JList education;
    private javax.swing.JList epilepsy;
    private javax.swing.JTextArea epilepticOtherSymptoms;
    private javax.swing.JCheckBox escitalopram;
    private javax.swing.JTextField euroqol;
    private javax.swing.JList familyPsychoEducation;
    private javax.swing.JCheckBox fluoxetine;
    private javax.swing.JCheckBox fluphenazine;
    private javax.swing.JCheckBox fluphenazineDecanoateInjection;
    private javax.swing.JRadioButton followUpCareCounseling1;
    private javax.swing.JRadioButton followUpCareCounseling2;
    private javax.swing.JRadioButton followUpCareMedication1;
    private javax.swing.JRadioButton followUpCareMedication2;
    private javax.swing.ButtonGroup followupCareCounselingGroup;
    private javax.swing.ButtonGroup followupCareMedicationsGroup;
    private javax.swing.JFormattedTextField gaf;
    private javax.swing.JCheckBox haloperidal;
    private javax.swing.JCheckBox haloperidalDecanoatInjection;
    private javax.swing.JCheckBox imipramine;
    private javax.swing.JList individualCounseling;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JCheckBox lithium;
    private javax.swing.JTextField locationOfService;
    private javax.swing.JCheckBox lorazepam;
    private javax.swing.JTabbedPane mainPanel;
    private javax.swing.JList maritalStatus;
    private javax.swing.JTextField medicationsNotAvailable;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JList moodDisorder;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JTextField numberOfDaysInLifeRole;
    private javax.swing.JTextField numberOfSeizuresPerMonth;
    private javax.swing.JCheckBox otherAnticholinergic;
    private javax.swing.JCheckBox otherAntidepressant;
    private javax.swing.JCheckBox otherAntiepileptic;
    private javax.swing.JCheckBox otherAntipsychotic;
    private javax.swing.JTextArea otherMedicalCondition;
    private javax.swing.JCheckBox otherMoodStabilizer;
    private javax.swing.JCheckBox otherSedative;
    private javax.swing.JTextField otherSymptomsScore;
    private javax.swing.JPanel patientDemographicsOuterPanel;
    private javax.swing.JPanel patientDemographicsPanel;
    private javax.swing.JTextField patientID;
    private javax.swing.ButtonGroup permissionToReportGroup;
    private javax.swing.JCheckBox phenobarbital;
    private javax.swing.JCheckBox phenytoin;
    private javax.swing.JFormattedTextField phq;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JList psychoticDisorder;
    private javax.swing.ButtonGroup reasonForVisit;
    private javax.swing.JCheckBox reasonForVisit1;
    private javax.swing.JCheckBox reasonForVisit2;
    private javax.swing.JList referralSource;
    private javax.swing.JRadioButton reportData1;
    private javax.swing.JRadioButton reportData2;
    private javax.swing.JCheckBox risperidone;
    private javax.swing.JCheckBox risperidoneConstaInjection;
    private javax.swing.JMenuItem saveAsPdfMenuItem;
    private javax.swing.JTextArea secondaryDiagnosis;
    private javax.swing.JCheckBox sertraline;
    private javax.swing.JList somatoformDisorder;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JList substanceAbuseDisorder;
    private javax.swing.JList substanceAbuseDisorder2;
    private javax.swing.JPanel symptonsOuterPanel;
    private javax.swing.JPanel symptonsPanel;
    private javax.swing.JPanel treatmentOuterPanel;
    private javax.swing.JPanel treatmentPanel;
    private javax.swing.JCheckBox trihexyphenidyl;
    private javax.swing.JMenuItem uploadMenuItem;
    private javax.swing.JTextField who_das;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private JDialog generatePatientIDBox;
    private JDialog uploadFormsBox;
}
