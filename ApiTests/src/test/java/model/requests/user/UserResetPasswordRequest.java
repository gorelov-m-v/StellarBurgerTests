package model.requests.user;

import io.restassured.response.Response;
import model.data.User;
import model.requests.RequestHelper;
import model.responses.user.resetpassword.ResetPasswordResponse;

import static io.restassured.RestAssured.given;

public class UserResetPasswordRequest extends RequestHelper {

    public int statusCode;

    public ResetPasswordResponse resetPassword(User user) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(paths.getRESET_PASSWORD())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(ResetPasswordResponse.class);
    }
}
