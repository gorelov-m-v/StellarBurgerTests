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

public class UserRegistrationTests extends TestHelper {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();

    @BeforeMethod
    public void BeforeMethod(){
        RestAssured.baseURI = urls.getStellarBurgerProd();
    }

    @DataProvider(name = "passwordLength")
    public static Object[][] passwordLength() {

        return new Object[][] {{4,   false},
                               {7,   false},
                               {8,    true},
                               {9,    true},
                               {35,   true},
                               {49,   true},
                               {50,   true},
                               {51,  false},
                               {70,  false}};
    }

    @Test(dataProvider = "passwordLength")
    public void passwordLengthValidationTest(int passwordLength, boolean success) {

        User requestedUser = new User().withEmail(generate.randomEmail())
                                       .withPassword(generate.randomPassword(passwordLength))
                                       .withName(generate.randomName(11));

        registrationResponse = registrationRequest.userRegistration(requestedUser);

        assertThat(registrationResponse.success()).isEqualTo(success);
        if(registrationResponse.success() == true) {
            assertThat(registrationRequest.statusCode).isEqualTo(200);
            assertThat(registrationResponse.user().email()).isEqualTo(requestedUser.email());
            assertThat(registrationResponse.user().name()).isEqualTo(requestedUser.name());
            assertThat(registrationResponse.accessToken()).isNotNull();
            assertThat(registrationResponse.refreshToken()).isNotNull();
        } else {
            assertThat(registrationRequest.statusCode).isEqualTo(404);
            assertThat(registrationResponse.getMessage()).isEqualTo(messages.getINVALID_PASSWORD_LENGTH());
        }
    }

    @DataProvider(name = "nameLength")
    public static Object[][] nameLength() {

        return new Object[][] {{6,   false},
                               {9,   false},
                               {10,   true},
                               {11,   true},
                               {24,   true},
                               {39,   true},
                               {40,   true},
                               {41,  false},
                               {80,  false}};
    }

    @Test(dataProvider = "nameLength")
    public void nameLengthValidationTest(int nameLength, boolean success) {

        User requestedUser = new User().withEmail(generate.randomEmail())
                                       .withPassword(generate.randomPassword(11))
                                       .withName(generate.randomName(nameLength));

        registrationResponse = registrationRequest.userRegistration(requestedUser);

        assertThat(registrationResponse.success()).isEqualTo(success);
        if(registrationResponse.success() == true) {
            assertThat(registrationRequest.statusCode).isEqualTo(200);
            assertThat(registrationResponse.user().email()).isEqualTo(requestedUser.email());
            assertThat(registrationResponse.user().name()).isEqualTo(requestedUser.name());
            assertThat(registrationResponse.accessToken()).isNotNull();
            assertThat(registrationResponse.refreshToken()).isNotNull();
        } else {
            assertThat(registrationRequest.statusCode).isEqualTo(404);
            assertThat(registrationResponse.getMessage()).isEqualTo(messages.getINVALID_NAME_LENGTH());
        }


    }

    @AfterMethod
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
