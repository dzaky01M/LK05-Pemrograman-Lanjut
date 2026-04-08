public class IntegrationGateway<T extends MedicalRecord & Versioned & Confidential> {

    private T data; // ini kita anggap data dari database

    // constructor
    public IntegrationGateway(T data) {
        this.data = data;
    }

    public SecureResponse<T> fetchData(String patientId, int requesterClearanceLevel) {

        // cek id dulu
        if (!data.getPatientId().equals(patientId)) {
            return new SecureResponse<>(false, null, "Data tidak ditemukan");
        }

        // cek level keamanan
        if (requesterClearanceLevel < data.getSecurityLevel()) {

            // masking
            data.maskSensitiveData();

            return new SecureResponse<>(
                    true,
                    data,
                    "Beberapa data disembunyikan"
            );
        }

        // kalo aman
        return new SecureResponse<>(
                true,
                data,
                null
        );
    }
}