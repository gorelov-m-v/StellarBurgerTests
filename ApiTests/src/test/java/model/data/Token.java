package model.data;

public class Token {
    private String token;

    public String getToken() {
        return token;
    }

    public Token withToken(String token) {
        this.token = token;
        return this;
    }
}
