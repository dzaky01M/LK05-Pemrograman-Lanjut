/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author DELL
 */
public class PatientProfileV2 implements MedicalRecord, Versioned, Confidential {

    private String patientId;
    private String name;
    private String ssn; // sensitif
    private String allergy; // tambahan di V2
    private int version;
    private int securityLevel;

    // Constructor
    public PatientProfileV2(String patientId, String name, String ssn, String allergy) {
        this.patientId = patientId;
        this.name = name;
        this.ssn = ssn;
        this.allergy = allergy;
        this.version = 2;
        this.securityLevel = 3; // lebih sensitif
    }

    // Getter
    @Override
    public String getPatientId() {
        return patientId;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int getSecurityLevel() {
        return securityLevel;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public String getAllergy() {
        return allergy;
    }

    // MASKING DATA SENSITIF (lebih banyak yang di-mask)
    @Override
    public void maskSensititveData() {
        if (this.ssn != null) {
            this.ssn = "******";
        }
        if (this.allergy != null) {
            this.allergy = "CONFIDENTIAL";
        }
    }
}
