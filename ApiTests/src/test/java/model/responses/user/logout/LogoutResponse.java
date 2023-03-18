package model.responses.user.logout;

public class LogoutResponse {

    private boolean success;
    private String message;

    public boolean success() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
