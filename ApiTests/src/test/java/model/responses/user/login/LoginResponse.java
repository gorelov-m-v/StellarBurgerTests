package model.responses.user.login;

public class LoginResponse {

    private boolean success;
    private User user;
    private String message;
    private String accessToken;
    private String refreshToken;

    public boolean success() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User user() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String accessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String refreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
