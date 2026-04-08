public class SecureResponse<T extends MedicalRecord & Confidential> {

    private boolean success;
    private T data;
    private String warningMessage;

    // constructor
    public SecureResponse(boolean success, T data, String warningMessage) {
        this.success = success;
        this.data = data;
        this.warningMessage = warningMessage;
    }

    // getter
    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getWarningMessage() {
        return warningMessage;
    }
}