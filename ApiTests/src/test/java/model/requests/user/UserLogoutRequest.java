package model.requests.user;

import io.restassured.response.Response;
import model.data.Token;
import model.requests.RequestHelper;
import model.responses.user.logout.LogoutResponse;

import static io.restassured.RestAssured.given;

public class UserLogoutRequest extends RequestHelper {

    public int statusCode;

    public LogoutResponse userLogout(Token token) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(token)
                .when()
                .post(paths.getLOGOUT_USER_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(LogoutResponse.class);
    }
}
