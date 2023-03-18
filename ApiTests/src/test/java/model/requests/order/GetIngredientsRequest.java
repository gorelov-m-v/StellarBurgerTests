package model.requests.order;

import io.restassured.response.Response;
import model.requests.RequestHelper;
import model.responses.order.getingredients.GetIngredientsResponse;

import static io.restassured.RestAssured.given;

public class GetIngredientsRequest extends RequestHelper {

    public int statusCode;

    public GetIngredientsResponse getIngredientsList() {
        Response response = (Response) given()
                .header("Content-type", "application/json")
                .when()
                .get(paths.getGET_INGREDIENTS_PATH())
                .then()
                .extract()
                .body();

        statusCode = response.getStatusCode();

        return response.as(GetIngredientsResponse.class);
    }
}
