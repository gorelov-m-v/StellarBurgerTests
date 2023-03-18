package model.data;

public class User {

    private String email;
    private String password;
    private String name;

    public String email() {
        return email;
    }
    public String password() {
        return password;
    }
    public String name() {
        return name;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }
}
