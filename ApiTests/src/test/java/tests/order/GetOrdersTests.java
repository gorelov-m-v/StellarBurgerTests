package tests.order;

import io.restassured.RestAssured;
import model.data.Ingredients;
import model.data.User;
import model.navigation.Urls;
import model.requests.order.CreateOrderRequest;
import model.requests.order.GetAllOrdersRequest;
import model.requests.order.GetIngredientsRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.order.create.CreateOrderResponse;
import model.responses.order.get.GetOrdersResponse;
import model.responses.order.get.Orders;
import model.responses.order.getingredients.Data;
import model.responses.order.getingredients.GetIngredientsResponse;
import model.responses.user.registration.RegistrationResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetOrdersTests {

    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    GetIngredientsRequest getIngredientsRequest = new GetIngredientsRequest();
    GetIngredientsResponse getIngredientsResponse;
    CreateOrderRequest createOrderRequest = new CreateOrderRequest();
    CreateOrderResponse createOrderResponse;
    GetAllOrdersRequest getAllOrdersRequest = new GetAllOrdersRequest();
    GetOrdersResponse getOrdersResponse;
    User user;
    Urls urls = new Urls();
    Generator generate = new Generator();
    Ingredients orderList;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = urls.getStellarBurgerProd();
        user = new User().withEmail(generate.randomEmail())
                .withPassword(generate.randomPassword(8))
                .withName(generate.randomName(11));
        registrationResponse = registrationRequest.userRegistration(user);

        getIngredientsResponse = getIngredientsRequest.getIngredientsList();
        List<Data> ingredientsList = getIngredientsResponse.getData();
        List<String> idList = ingredientsList.stream().map(Data::get_id).collect(Collectors.toList());

        orderList = new Ingredients(generate.getOrderList(idList));
        createOrderResponse = createOrderRequest.createOrder(orderList, registrationResponse.accessToken());
    }

    @Test
    public void smokeGetAllOrdersTest() {
        getOrdersResponse = getAllOrdersRequest.getAllOrders(registrationResponse.accessToken());

        List<Orders> orders = getOrdersResponse.getOrders();
        List<List<String>> ingredientLists = orders.stream().map(Orders::getIngredients).collect(Collectors.toList());
        List <String> firstOrder = ingredientLists.get(0);
        System.out.println(Arrays.toString(firstOrder.toArray()));
    }
}
