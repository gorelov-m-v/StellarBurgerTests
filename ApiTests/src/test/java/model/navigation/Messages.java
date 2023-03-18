package model.navigation;

public class Messages {
    private final String USER_ALREADY_EXISTS = "User already exists";
    public String getUSER_ALREADY_EXISTS() {
        return USER_ALREADY_EXISTS;
    }
    private final String CREATING_USER_WITHOUT_FIELD = "Email, password and name are required fields";
    public String getCREATING_USER_WITHOUT_FIELD() {
        return CREATING_USER_WITHOUT_FIELD;
    }
    private final String EMAIL_PASSWORD_INCORRECT= "email or password are incorrect";
    public String getEMAIL_PASSWORD_INCORRECT() {
        return EMAIL_PASSWORD_INCORRECT;
    }
    private final String YOU_SHOULD_BE_AUTHORISED = "You should be authorised";
    public String getYOU_SHOULD_BE_AUTHORISED() {
        return YOU_SHOULD_BE_AUTHORISED;
    }
    private final String USER_EMAIL_ALREADY_EXISTS = "User with such email already exists";
    public String getUSER_EMAIL_ALREADY_EXISTS() {
        return USER_EMAIL_ALREADY_EXISTS;
    }
    public final String INGREDIENTS_MUST_PROVIDED = "Ingredient ids must be provided";
    public String getINGREDIENTS_MUST_PROVIDED() {
        return INGREDIENTS_MUST_PROVIDED;
    }
    private final String JWT_MALFORMED = "jwt malformed";
    public String getJWT_MALFORMED() {
        return JWT_MALFORMED;
    }
    private final String USER_NOT_FOUND = "User not found";
    public String getUSER_NOT_FOUND() {
        return USER_NOT_FOUND;
    }
    private final String INVALID_SIGNATURE = "invalid signature";
    public String getINVALID_SIGNATURE() {
        return INVALID_SIGNATURE;
    }
    private final String TOKEN_REQUIRED = "Token required";
    public String getTOKEN_REQUIRED() {
        return TOKEN_REQUIRED;
    }
    private final String USER_SUCCESSFULLY_REMOVED = "User successfully removed";
    public String getUSER_SUCCESSFULLY_REMOVED() {
        return USER_SUCCESSFULLY_REMOVED;
    }
    private String SUCCESSFUL_LOGOUT  = "Successful logout";
    public String getSUCCESSFUL_LOGOUT() {
        return SUCCESSFUL_LOGOUT;
    }
    private String RESET_EMAIL_SENT = "Reset email sent";
    public String getRESET_EMAIL_SENT() {
        return RESET_EMAIL_SENT;
    }
}
