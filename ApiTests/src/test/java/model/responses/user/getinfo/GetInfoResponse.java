package model.responses.user.getinfo;

import model.responses.user.login.User;

public class GetInfoResponse {
    private boolean success;
    private User user;

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
}
