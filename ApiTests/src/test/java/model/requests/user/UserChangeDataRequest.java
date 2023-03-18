package model.requests.user;

import io.restassured.response.Response;
import model.data.User;
import model.requests.RequestHelper;
import model.responses.user.changedata.ChangeDataResponse;

import static io.restassured.RestAssured.given;

public class UserChangeDataRequest extends RequestHelper {

    public int statusCode;

    public ChangeDataResponse changeUserData(User userNewData, String accessToken) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(userNewData)
                .when()
                .patch(paths.getCHANGE_USER_DATA_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(ChangeDataResponse.class);
    }

}
