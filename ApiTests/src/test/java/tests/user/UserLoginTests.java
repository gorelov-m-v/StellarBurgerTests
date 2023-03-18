package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserLoginRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.login.LoginResponse;
import model.responses.user.registration.RegistrationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLoginTests extends TestHelper {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserLoginRequest loginRequest = new UserLoginRequest();
    LoginResponse loginResponse;
    User requestedUser;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();

    @Before
    public void setUp() {
        RestAssured.baseURI = urls.getStellarBurgerProd();
        requestedUser = new User().withEmail(generate.randomEmail())
                                  .withPassword(generate.randomPassword(8))
                                  .withName(generate.randomName());

        registrationResponse = registrationRequest.userRegistration(requestedUser);
    }

    @Test
    public void smokeUserLoginTest() {
        loginResponse = loginRequest.userLogin(new User().withEmail(requestedUser.email())
                                                         .withPassword(requestedUser.password()));

        assertThat(loginRequest.statusCode).isEqualTo(200);
        assertThat(loginResponse.success()).isEqualTo(true);
        assertThat(loginResponse.user().email()).isEqualTo(requestedUser.email());
        assertThat(loginResponse.user().name()).isEqualTo(requestedUser.name());
        assertThat(loginResponse.accessToken()).isNotNull();
        assertThat(loginResponse.refreshToken()).isNotNull();
    }

    @After
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
