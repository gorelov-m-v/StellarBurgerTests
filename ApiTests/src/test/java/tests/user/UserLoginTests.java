package tests.user;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserLoginRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.login.LoginResponse;
import model.responses.user.registration.RegistrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserLoginTests extends TestHelper {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserLoginRequest loginRequest = new UserLoginRequest();
    LoginResponse loginResponse;
    User requestedUser;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();

    Gson gson = new Gson();
    Logger logger = LoggerFactory.getLogger(UserRegistrationTests.class);

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = urls.getStellarBurgerProd();
        requestedUser = new User().withEmail(generate.randomEmail(20))
                                  .withPassword(generate.randomPassword(8))
                                  .withName(generate.randomName(11));

        registrationResponse = registrationRequest.userRegistration(requestedUser);
    }

    @Test
    public void loginPositiveTest() {
        loginResponse = loginRequest.userLogin(new User().withEmail(requestedUser.email())
                                                         .withPassword(requestedUser.password()));

        assertThat(loginRequest.statusCode).isEqualTo(200);
        assertThat(loginResponse.success()).isEqualTo(true);
        assertThat(loginResponse.user().email()).isEqualTo(requestedUser.email());
        assertThat(loginResponse.user().name()).isEqualTo(requestedUser.name());
        assertThat(loginResponse.accessToken()).isNotNull();
        assertThat(loginResponse.refreshToken()).isNotNull();
    }

    @Test
    public void loginWithWrongPasswordTest() {
        loginResponse = loginRequest.userLogin(new User().withEmail(requestedUser.email())
                                                         .withPassword(generate.randomPassword(8)));

        assertThat(loginRequest.statusCode).isEqualTo(401);
        assertThat(loginResponse.success()).isEqualTo(false);
        assertThat(loginResponse.message()).isEqualTo(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @Test
    public void loginWithWrongEmailTest() {
        loginResponse = loginRequest.userLogin(new User().withEmail(generate.randomEmail(16))
                                                         .withPassword(requestedUser.password()));

        assertThat(loginRequest.statusCode).isEqualTo(401);
        assertThat(loginResponse.success()).isEqualTo(false);
        assertThat(loginResponse.message()).isEqualTo(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @Test
    public void loginWithoutPasswordTest() {
        loginResponse = loginRequest.userLogin(new User().withEmail(generate.randomEmail(16)));

        assertThat(loginRequest.statusCode).isEqualTo(401);
        assertThat(loginResponse.success()).isEqualTo(false);
        assertThat(loginResponse.message()).isEqualTo(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @Test
    public void loginWithoutEmailTest() {
        loginResponse = loginRequest.userLogin(new User().withPassword(requestedUser.password()));

        assertThat(loginRequest.statusCode).isEqualTo(401);
        assertThat(loginResponse.success()).isEqualTo(false);
        assertThat(loginResponse.message()).isEqualTo(messages.getEMAIL_PASSWORD_INCORRECT());
    }
    @Test
    public void loginWithoutEmailT2est() {
        loginResponse = loginRequest.userLogin(new User().withPassword(requestedUser.password()));

        assertThat(loginRequest.statusCode).isEqualTo(401);
        assertThat(loginResponse.success()).isEqualTo(false);
        assertThat(loginResponse.message()).isEqualTo(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Request body: " + gson.toJson(requestedUser));
        logger.info("StatusCode: " + registrationRequest.statusCode + " / Response body: " + gson.toJson(registrationResponse));
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
