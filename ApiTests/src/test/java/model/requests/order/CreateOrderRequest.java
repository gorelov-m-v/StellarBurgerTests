package model.requests.order;

import io.restassured.response.Response;
import model.data.Ingredients;
import model.requests.RequestHelper;
import model.responses.order.create.CreateOrderResponse;

import static io.restassured.RestAssured.given;

public class CreateOrderRequest extends RequestHelper {

    public int statusCode;

    public CreateOrderResponse createOrder(Ingredients orderList, String accessToken) {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(orderList)
                .when()
                .post(paths.getCREATE_ORDER_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(CreateOrderResponse.class);
    }
}
