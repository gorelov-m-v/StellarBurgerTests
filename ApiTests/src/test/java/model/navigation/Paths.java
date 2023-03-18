package model.navigation;

public class Paths {
    private final String userRegistration = "/api/auth/register";
    public String userRegistration() {
        return userRegistration;
    }
    private final String LOGIN_USER_PATH = "/api/auth/login";
    public String getLOGIN_USER_PATH() {
        return LOGIN_USER_PATH;
    }
    private final String LOGOUT_USER_PATH = "/api/auth/logout";
    public String getLOGOUT_USER_PATH() {
        return LOGOUT_USER_PATH;
    }
    private final String DELETE_USER_PATH = "/api/auth/user";
    public String getDELETE_USER_PATH() {
        return DELETE_USER_PATH;
    }
    private final String CHANGE_USER_DATA_PATH = "/api/auth/user";
    public String getCHANGE_USER_DATA_PATH() {
        return CHANGE_USER_DATA_PATH;
    }
    public String GET_USER_DATA_PATH = "/api/auth/user";
    public String getGET_USER_DATA_PATH() {
        return GET_USER_DATA_PATH;
    }
    private final String GET_INGREDIENTS_PATH = "/api/ingredients";
    public String getGET_INGREDIENTS_PATH() {
        return GET_INGREDIENTS_PATH;
    }
    private final String CREATE_ORDER_PATH = "/api/orders";
    public String getCREATE_ORDER_PATH() {
        return CREATE_ORDER_PATH;
    }
    private final String GET_ORDER_PATH = "/api/orders";
    public String getGET_ORDER_PATH() {
        return GET_ORDER_PATH;
    }
    public final String RESET_PASSWORD = "/api/password-reset";
    public String getRESET_PASSWORD() {
        return RESET_PASSWORD;
    }
}
