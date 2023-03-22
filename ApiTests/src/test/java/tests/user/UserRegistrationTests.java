package tests.user;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.registration.RegistrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import util.Generator;
import static org.assertj.core.api.Assertions.*;

public class UserRegistrationTests extends TestHelper {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();
    Logger logger = LoggerFactory.getLogger(UserRegistrationTests.class);
    User requestedUser;
    Gson gson = new Gson();

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

        requestedUser = new User().withEmail(generate.randomEmail(20))
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

        User requestedUser = new User().withEmail(generate.randomEmail(20))
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

    @DataProvider(name = "emailLength")
    public static Object[][] emailLength() {

        return new Object[][] {{50,     true},
                               {99,     true},
                               {100,    true},
                               {101,   false},
                               {200,   false}};
    }

    @Test(dataProvider = "emailLength")
    public void emailLengthValidationTest(int emailLength, boolean success) {

        User requestedUser = new User().withEmail(generate.randomEmail(emailLength))
                                       .withPassword(generate.randomPassword(11))
                                       .withName(generate.randomName(15));

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
            assertThat(registrationResponse.getMessage()).isEqualTo(messages.getINVALID_EMAIL_LENGTH());
        }
    }

    @DataProvider(name = "requiredField")
    public static Object[][] requiredField() {
        Generator g = new Generator();
        return new Object[][] {
                {new User().withEmail(g.randomEmail(20)).withPassword(g.randomPassword(20)).withName(g.randomName(20)), true},
                {new User().withPassword(g.randomPassword(20)).withName(g.randomName(20)), false},
                {new User().withEmail(g.randomEmail(20)).withName(g.randomName(20)), false},
                {new User().withEmail(g.randomEmail(20)).withPassword(g.randomPassword(20)), false}};
    }

    @Test(dataProvider = "requiredField")
    public void requiredFieldValidationTest(User user, boolean success) {

        registrationResponse = registrationRequest.userRegistration(user);

        assertThat(registrationResponse.success()).isEqualTo(success);
        if(registrationResponse.success() == true) {
            assertThat(registrationRequest.statusCode).isEqualTo(200);
            assertThat(registrationResponse.user().email()).isEqualTo(user.email());
            assertThat(registrationResponse.user().name()).isEqualTo(user.name());
            assertThat(registrationResponse.accessToken()).isNotNull();
            assertThat(registrationResponse.refreshToken()).isNotNull();
        } else {
            assertThat(registrationRequest.statusCode).isEqualTo(403);
            assertThat(registrationResponse.getMessage()).isEqualTo(messages.getCREATING_USER_WITHOUT_FIELD());
        }
    }

    @AfterMethod
    public void tearDown() {
        logger.info(gson.toJson(requestedUser));
        logger.info(gson.toJson(registrationResponse));

        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
