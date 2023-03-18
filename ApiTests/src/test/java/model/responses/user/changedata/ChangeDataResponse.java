package model.responses.user.changedata;

public class ChangeDataResponse {
    private User user;
    private boolean success;

    public User user() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean success() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
