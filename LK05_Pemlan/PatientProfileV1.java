/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class PatientProfileV1 implements MedicalRecord, Versioned, Confidential {

    private String patientId;
    private String name;
    private String ssn; // data sensitif
    private int version;
    private int securityLevel; // 1=Public, 2=Restricted, 3=Secret

    // Constructor
    public PatientProfileV1(String patientId, String name, String ssn) {
        this.patientId = patientId;
        this.name = name;
        this.ssn = ssn;
        this.version = 1;
        this.securityLevel = 2; // default Restricted
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

    //  MASKING DATA SENSITIF
    @Override
    public void maskSensititveData() {
        if (this.ssn != null) {
            this.ssn = "******";
        }
    }
}
