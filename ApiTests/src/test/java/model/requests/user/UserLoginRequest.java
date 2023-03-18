package model.requests.user;

import io.restassured.response.Response;
import model.data.User;
import model.requests.RequestHelper;
import model.responses.user.login.LoginResponse;

import static io.restassured.RestAssured.given;

public class UserLoginRequest extends RequestHelper {

    public int statusCode;

    public LoginResponse userLogin(User user) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(paths.getLOGIN_USER_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(LoginResponse.class);
    }

}
