package model.requests.order;

import io.restassured.response.Response;
import model.requests.RequestHelper;
import model.responses.order.get.GetOrdersResponse;
import static io.restassured.RestAssured.given;

public class GetAllOrdersRequest extends RequestHelper {

    public int statusCode;

    public GetOrdersResponse getAllOrders(String accessToken) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .get(paths.getGET_ORDER_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(GetOrdersResponse.class);
    }
}
