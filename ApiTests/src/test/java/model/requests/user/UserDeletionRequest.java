package model.requests.user;

import io.restassured.response.Response;
import model.requests.RequestHelper;
import model.responses.user.deletion.DeletionResponse;
import static io.restassured.RestAssured.given;

public class UserDeletionRequest extends RequestHelper {

    public int statusCode;

    public DeletionResponse userDeletion(String actualAccessToken) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .header("Authorization", actualAccessToken)
                .when()
                .delete(paths.getDELETE_USER_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(DeletionResponse.class);
    }
}
