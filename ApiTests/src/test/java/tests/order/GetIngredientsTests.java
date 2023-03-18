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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetIngredientsTests extends TestHelper {
    GetIngredientsResponse getIngredientsResponse;
    GetIngredientsRequest getIngredientsRequest = new GetIngredientsRequest();
    Generator generator = new Generator();

    Urls urls = new Urls();


    @Before
    public void setUp(){
        RestAssured.baseURI = urls.getStellarBurgerProd();
    }

    @Test
    public void smokeGetIngredientsTest() {
        getIngredientsResponse = getIngredientsRequest.getIngredientsList();

        List<Data> ingredientsList = getIngredientsResponse.getData();
        List<String> idList = ingredientsList.stream().map(Data::get_id).collect(Collectors.toList());

        List<String> orderLists = generator.getOrderList(idList);

        System.out.println(Arrays.toString(idList.toArray()));
        System.out.println(Arrays.toString(orderLists.toArray()));
    }
}
