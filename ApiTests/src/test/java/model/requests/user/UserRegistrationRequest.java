package model.requests.user;

import io.restassured.response.Response;
import model.data.User;
import model.requests.RequestHelper;
import model.responses.user.registration.RegistrationResponse;
import static io.restassured.RestAssured.given;

public class UserRegistrationRequest extends RequestHelper {

    public int statusCode;

    public RegistrationResponse userRegistration(User user) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(paths.userRegistration())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(RegistrationResponse.class);
    }
}
