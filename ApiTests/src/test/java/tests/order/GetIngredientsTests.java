package tests.order;

import io.restassured.RestAssured;
import model.data.Ingredients;
import model.navigation.Urls;
import model.requests.order.GetIngredientsRequest;
import model.responses.order.getingredients.Data;
import model.responses.order.getingredients.GetIngredientsResponse;
import org.junit.Before;
import org.junit.Test;
import tests.user.TestHelper;
import util.Generator;
import static org.assertj.core.api.Assertions.assertThat;

public class GetIngredientsTests extends TestHelper {
    GetIngredientsResponse getIngredientsResponse;
    GetIngredientsRequest getIngredientsRequest = new GetIngredientsRequest();
    Urls urls = new Urls();

    @Before
    public void setUp(){
        RestAssured.baseURI = urls.getStellarBurgerProd();
    }

    @Test
    public void smokeGetIngredientsTest() {
        getIngredientsResponse = getIngredientsRequest.getIngredientsList();

        assertThat(getIngredientsRequest.statusCode).isEqualTo(200);
        assertThat(getIngredientsResponse.getSuccess()).isEqualTo(true);
    }
}
