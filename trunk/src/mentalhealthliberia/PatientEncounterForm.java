/*
 *
 */
package mentalhealthliberia;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO which stores the patient encounter form data.
 * 
 * @author W. Greg Youree
 */
public class PatientEncounterForm implements Serializable {

    public String getAge() {
        return age;
    }

    public boolean isAmitriptyline() {
        return amitriptyline;
    }

    public String getAnxietyDisorder() {
        return anxietyDisorder;
    }

    public String getCage() {
        return cage;
    }

    public boolean isCarbamazepine() {
        return carbamazepine;
    }

    public boolean isCarbmazepineAntiepileptic() {
        return carbmazepineAntiepileptic;
    }

    public boolean isChlorpromazine() {
        return chlorpromazine;
    }

    public String getClinicianID() {
        return clinicianID;
    }

    public String getClinicianTrainingLevel() {
        return clinicianTrainingLevel;
    }

    public boolean isClomipramine() {
        return clomipramine;
    }

    public boolean isClonazepam() {
        return clonazepam;
    }

    public String getCounseling() {
        return counseling;
    }

    public String getCountyOfResidence() {
        return countyOfResidence;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfService() {
        return dateOfService;
    }

    public boolean isDepakote() {
        return depakote;
    }

    public boolean isDepakoteAntiepileptic() {
        return depakoteAntiepileptic;
    }

    public String getDiagnosisPrimary() {
        return diagnosisPrimary;
    }

    public boolean isDiazepam() {
        return diazepam;
    }

    public String getDischargeDisposition() {
        return dischargeDisposition;
    }

    public String getDistanceTraveled() {
        return distanceTraveled;
    }

    public String getEducation() {
        return education;
    }

    public int getNumberOfDaysInLifeRole() {
        return numberOfDaysInLifeRole;
    }

    public String getEpilepsy() {
        return epilepsy;
    }

    public boolean isEscitalopram() {
        return escitalopram;
    }

    public String getFamilyPsychoEducation() {
        return familyPsychoEducation;
    }

    public String getFathersName() {
        return fathersName;
    }

    public boolean isFluoxetine() {
        return fluoxetine;
    }

    public boolean isFluphenazine() {
        return fluphenazine;
    }

    public boolean isFluphenazineDecanoateInjection() {
        return fluphenazineDecanoateInjection;
    }

    public boolean isFollowUpCareCounseling() {
        return followUpCareCounseling;
    }

    public boolean isFollowUpCareMedication() {
        return followUpCareMedication;
    }

    public String getGaf() {
        return gaf;
    }

    public String getGender() {
        return gender;
    }

    public boolean isHaloperidal() {
        return haloperidal;
    }

    public boolean isHaloperidalDecanoateInjection() {
        return haloperidalDecanoateInjection;
    }

    public boolean isImipramine() {
        return imipramine;
    }

    public String getIndividualCounseling() {
        return individualCounseling;
    }

    public boolean isLithium() {
        return lithium;
    }

    public String getLocationOfService() {
        return locationOfService;
    }

    public boolean isLorazepam() {
        return lorazepam;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getMoodDisorder() {
        return moodDisorder;
    }

    public boolean isOtherAnticholinergic() {
        return otherAnticholinergic;
    }

    public boolean isOtherAntidepressant() {
        return otherAntidepressant;
    }

    public boolean isOtherAntiepileptic() {
        return otherAntiepileptic;
    }

    public boolean isOtherAntipsychotic() {
        return otherAntipsychotic;
    }

    public String getOtherMedicalCondition() {
        return otherMedicalCondition;
    }

    public boolean isOtherMoodStabilizer() {
        return otherMoodStabilizer;
    }

    public boolean isOtherSedative() {
        return otherSedative;
    }

    public String getPatientName() {
        return patientName;
    }

    public boolean isPhenobarbital() {
        return phenobarbital;
    }

    public boolean isPhenytoin() {
        return phenytoin;
    }

    public String getPhq() {
        return phq;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getPsychoticDisorder() {
        return psychoticDisorder;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public boolean isReportData() {
        return reportData;
    }

    public boolean isRisperidone() {
        return risperidone;
    }

    public boolean isRisperidoneConstaInjection() {
        return risperidoneConstaInjection;
    }

    public String getSecondaryDiagnosis() {
        return secondaryDiagnosis;
    }

    public boolean isSertraline() {
        return sertraline;
    }

    public String getSomatoformDisorder() {
        return somatoformDisorder;
    }

    public String getSubstanceAbuseDisorder() {
        return substanceAbuseDisorder;
    }

    public String getSubstanceAbuseDisorder2() {
        return substanceAbuseDisorder2;
    }

    public boolean isTrihexyphenidyl() {
        return trihexyphenidyl;
    }

    public int getNumberOfSeizuresPerWeek() {
        return numberOfSeizuresPerWeek;
    }
    
    public boolean getIsDepressionPresent() {
        return isDepressionPresent;
    }
    
    public String getMedicationsNotAvailable() {
        return medicationsNotAvailable;
    }
    
    public String getWhoDas() {
        return whoDas;
    }
    
    public String getEuroqol() {
        return this.euroqol;
    }
    
    public String getOtherSymptomsScore() {
        return this.otherSymptomsScore;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAmitriptyline(boolean amitriptyline) {
        this.amitriptyline = amitriptyline;
    }

    public void setAnxietyDisorder(String anxietyDisorder) {
        this.anxietyDisorder = anxietyDisorder;
    }

    public void setCage(String cage) {
        this.cage = cage;
    }

    public void setCarbamazepine(boolean carbamazepine) {
        this.carbamazepine = carbamazepine;
    }

    public void setCarbmazepineAntiepileptic(boolean carbmazepineAntiepileptic) {
        this.carbmazepineAntiepileptic = carbmazepineAntiepileptic;
    }

    public void setChlorpromazine(boolean chlorpromazine) {
        this.chlorpromazine = chlorpromazine;
    }

    public void setClinicianID(String clinicianID) {
        this.clinicianID = clinicianID;
    }

    public void setClinicianTrainingLevel(String clinicianTrainingLevel) {
        this.clinicianTrainingLevel = clinicianTrainingLevel;
    }

    public void setClomipramine(boolean clomipramine) {
        this.clomipramine = clomipramine;
    }

    public void setClonazepam(boolean clonazepam) {
        this.clonazepam = clonazepam;
    }

    public void setCounseling(String counseling) {
        this.counseling = counseling;
    }

    public void setCountyOfResidence(String countyOfResidence) {
        this.countyOfResidence = countyOfResidence;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfService(Date dateOfService) {
        this.dateOfService = dateOfService;
    }

    public void setDepakote(boolean depakote) {
        this.depakote = depakote;
    }

    public void setDepakoteAntiepileptic(boolean depakoteAntiepileptic) {
        this.depakoteAntiepileptic = depakoteAntiepileptic;
    }

    public void setDiagnosisPrimary(String diagnosisPrimary) {
        this.diagnosisPrimary = diagnosisPrimary;
    }

    public void setDiazepam(boolean diazepam) {
        this.diazepam = diazepam;
    }

    public void setDischargeDisposition(String dischargeDisposition) {
        this.dischargeDisposition = dischargeDisposition;
    }

    public void setDistanceTraveled(String distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setNumberOfDaysInLifeRole(int numberOfDaysInLifeRole) {
        this.numberOfDaysInLifeRole = numberOfDaysInLifeRole;
    }

    public void setEpilepsy(String epilepsy) {
        this.epilepsy = epilepsy;
    }

    public void setEscitalopram(boolean escitalopram) {
        this.escitalopram = escitalopram;
    }

    public void setFamilyPsychoEducation(String familyPsychoEducation) {
        this.familyPsychoEducation = familyPsychoEducation;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public void setFluoxetine(boolean fluoxetine) {
        this.fluoxetine = fluoxetine;
    }

    public void setFluphenazine(boolean fluphenazine) {
        this.fluphenazine = fluphenazine;
    }

    public void setFluphenazineDecanoateInjection(boolean fluphenazineDecanoateInjection) {
        this.fluphenazineDecanoateInjection = fluphenazineDecanoateInjection;
    }

    public void setFollowUpCareCounseling(boolean followUpCareCounseling) {
        this.followUpCareCounseling = followUpCareCounseling;
    }

    public void setFollowUpCareMedication(boolean followUpCareMedication) {
        this.followUpCareMedication = followUpCareMedication;
    }

    public void setGaf(String gaf) {
        this.gaf = gaf;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHaloperidal(boolean haloperidal) {
        this.haloperidal = haloperidal;
    }

    public void setHaloperidalDecanoateInjection(boolean haloperidalDecanoateInjection) {
        this.haloperidalDecanoateInjection = haloperidalDecanoateInjection;
    }

    public void setImipramine(boolean imipramine) {
        this.imipramine = imipramine;
    }

    public void setIndividualCounseling(String individualCounseling) {
        this.individualCounseling = individualCounseling;
    }

    public void setLithium(boolean lithium) {
        this.lithium = lithium;
    }

    public void setLocationOfService(String locationOfService) {
        this.locationOfService = locationOfService;
    }

    public void setLorazepam(boolean lorazepam) {
        this.lorazepam = lorazepam;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setMoodDisorder(String moodDisorder) {
        this.moodDisorder = moodDisorder;
    }

    public void setOtherAnticholinergic(boolean otherAnticholinergic) {
        this.otherAnticholinergic = otherAnticholinergic;
    }

    public void setOtherAntidepressant(boolean otherAntidepressant) {
        this.otherAntidepressant = otherAntidepressant;
    }

    public void setOtherAntiepileptic(boolean otherAntiepileptic) {
        this.otherAntiepileptic = otherAntiepileptic;
    }

    public void setOtherAntipsychotic(boolean otherAntipsychotic) {
        this.otherAntipsychotic = otherAntipsychotic;
    }

    public void setOtherMedicalCondition(String otherMedicalCondition) {
        this.otherMedicalCondition = otherMedicalCondition;
    }

    public void setOtherMoodStabilizer(boolean otherMoodStabilizer) {
        this.otherMoodStabilizer = otherMoodStabilizer;
    }

    public void setOtherSedative(boolean otherSedative) {
        this.otherSedative = otherSedative;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPhenobarbital(boolean phenobarbital) {
        this.phenobarbital = phenobarbital;
    }

    public void setPhenytoin(boolean phenytoin) {
        this.phenytoin = phenytoin;
    }

    public void setPhq(String phq) {
        this.phq = phq;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setPsychoticDisorder(String psychoticDisorder) {
        this.psychoticDisorder = psychoticDisorder;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }

    public void setReportData(boolean reportData) {
        this.reportData = reportData;
    }

    public void setRisperidone(boolean risperidone) {
        this.risperidone = risperidone;
    }

    public void setRisperidoneConstaInjection(boolean risperidoneConstaInjection) {
        this.risperidoneConstaInjection = risperidoneConstaInjection;
    }

    public void setSecondaryDiagnosis(String secondaryDiagnosis) {
        this.secondaryDiagnosis = secondaryDiagnosis;
    }

    public void setSertraline(boolean sertraline) {
        this.sertraline = sertraline;
    }

    public void setSomatoformDisorder(String somatoformDisorder) {
        this.somatoformDisorder = somatoformDisorder;
    }

    public void setSubstanceAbuseDisorder(String substanceAbuseDisorder) {
        this.substanceAbuseDisorder = substanceAbuseDisorder;
    }

    public void setSubstanceAbuseDisorder2(String substanceAbuseDisorder2) {
        this.substanceAbuseDisorder2 = substanceAbuseDisorder2;
    }

    public void setTrihexyphenidyl(boolean trihexyphenidyl) {
        this.trihexyphenidyl = trihexyphenidyl;
    }

    public void setNumberOfSeizuresPerWeek(int numberOfSeizuresPerWeek) {
        this.numberOfSeizuresPerWeek = numberOfSeizuresPerWeek;
    }

    public void setIsDepressionPresent(boolean isDepressionPresent) {
        this.isDepressionPresent = isDepressionPresent;
    }
    
    public void setMedicationsNotAvailable(String medicationsNotAvailable) {
        this.medicationsNotAvailable = medicationsNotAvailable;
    }
    
    public void setWhoDas(String whoDas) {
        this.whoDas = whoDas;
    }
    
    public void setEuroqol(String euroqol) {
        this.euroqol = euroqol;
    }
    
    public void setOtherSymptomsScore(String otherSymptomsScore) {
        this.otherSymptomsScore = otherSymptomsScore;
    }
    
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Clinician: " + getClinicianID() + " ");
        builder.append("Date of Service: " + getDateOfService() + " ");
        return builder.toString();
    }
    
    // Clinician Info
    private String clinicianID;
    
    // Basic Information
    private Date dateOfService;
    private String locationOfService;
    private String reasonForVisit;
    private String clinicianTrainingLevel;
    private String referralSource;
    
    // Patient Demographics (used for generating unique ID)
    private String patientName;
    private String dateOfBirth;
    private String gender;
    private String fathersName;
    private String placeOfBirth;
    
    // Patient Demographics
    private String age;
    private String countyOfResidence;
    private String distanceTraveled;
    private int numberOfDaysInLifeRole;
    private String maritalStatus;
    private String education;
    
    // Symptoms and Functioning
    private String phq;
    private String gaf;
    private String cage;
    private String whoDas;
    private String euroqol;
    private String otherSymptomsScore;
    
    // Diagnosis
    private String diagnosisPrimary;
    private String moodDisorder;
    private String anxietyDisorder;
    private String psychoticDisorder;
    private String somatoformDisorder;
    private String substanceAbuseDisorder;
    private String substanceAbuseDisorder2;
    private String epilepsy;
    private int numberOfSeizuresPerWeek;
    private boolean isDepressionPresent;
    private String otherMedicalCondition;
    private String secondaryDiagnosis;
    
    // Treatment
    private boolean fluoxetine;
    private boolean escitalopram;
    private boolean sertraline;
    private boolean amitriptyline;
    private boolean imipramine;
    private boolean otherAntidepressant;
    private boolean haloperidal;
    private boolean haloperidalDecanoateInjection;
    private boolean chlorpromazine;
    private boolean fluphenazine;
    private boolean fluphenazineDecanoateInjection;
    private boolean risperidone;
    private boolean risperidoneConstaInjection;
    private boolean otherAntipsychotic;
    private boolean clomipramine;
    private boolean clonazepam;
    private boolean diazepam;
    private boolean lorazepam;
    private boolean otherSedative;
    private boolean depakote;
    private boolean lithium;
    private boolean carbamazepine;
    private boolean otherMoodStabilizer;
    private boolean depakoteAntiepileptic;
    private boolean carbmazepineAntiepileptic;
    private boolean phenobarbital;
    private boolean phenytoin;
    private boolean otherAntiepileptic;
    private boolean trihexyphenidyl;
    private boolean otherAnticholinergic;
    private String counseling;
    private String individualCounseling;
    private String familyPsychoEducation;
    private String medicationsNotAvailable;
    
    // Discharge
    private boolean followUpCareMedication;
    private boolean followUpCareCounseling;
    private String dischargeDisposition;
    private boolean reportData;
}
