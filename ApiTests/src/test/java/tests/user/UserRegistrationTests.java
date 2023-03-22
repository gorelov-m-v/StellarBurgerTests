package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.navigation.Messages;
import model.navigation.Urls;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.registration.RegistrationResponse;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import util.Generator;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class UserRegistrationTests extends TestListenerAdapter {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();
    Generator generate = new Generator();
    Urls urls = new Urls();
    Messages messages = new Messages();


    @DataProvider(name = "test1")
    public static Object[][] passwordLength() {

        return new Object[][] {{2, true, 200},
                               {6, true, 200},
                               {19, true, 200},
                               {22, true, 200},
                               {0, false, 403}};
    }


    @BeforeMethod
    public void BeforeMethod(ITestContext ctx, Method method){
        RestAssured.baseURI = urls.getStellarBurgerProd();
    }

    @Test(dataProvider = "test1")
    public void smokeUserRegistrationTest(int passwordLength, boolean success, int statusCode) {

        User requestedUser = new User().withEmail(generate.randomEmail())
                                       .withPassword(generate.randomPassword(passwordLength))
                                       .withName(generate.randomName());

        registrationResponse = registrationRequest.userRegistration(requestedUser);

        assertThat(registrationRequest.statusCode).isEqualTo(statusCode);
        assertThat(registrationResponse.success()).isEqualTo(success);
    }

    @Test
    public void smokeUserRegistrationTes2t() {

        User requestedUser = new User().withEmail(generate.randomEmail())
                                       .withPassword(generate.randomPassword(8))
                                       .withName(generate.randomName());

        registrationResponse = registrationRequest.userRegistration(requestedUser);

        assertThat(registrationRequest.statusCode).isEqualTo(200);
        assertThat(registrationResponse.success()).isEqualTo(true);
        assertThat(registrationResponse.user().email()).isEqualTo(requestedUser.email());
        assertThat(registrationResponse.user().name()).isEqualTo(requestedUser.name());
        assertThat(registrationResponse.accessToken()).isNotNull();
        assertThat(registrationResponse.refreshToken()).isNotNull();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
