package model.requests.user;

import io.restassured.response.Response;
import model.requests.RequestHelper;
import model.responses.user.getinfo.GetInfoResponse;
import static io.restassured.RestAssured.given;

public class UserGetInfoRequest extends RequestHelper {

    public int statusCode;

    public GetInfoResponse userGetInfo(String accessToken) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .get(paths.getCHANGE_USER_DATA_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(GetInfoResponse.class);
    }
}
