package tests.user;

import io.restassured.RestAssured;
import model.data.Token;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserLoginRequest;
import model.requests.user.UserLogoutRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.login.LoginResponse;
import model.responses.user.logout.LogoutResponse;
import model.responses.user.registration.RegistrationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLogoutTests extends TestHelper {

    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserLoginRequest loginRequest = new UserLoginRequest();
    LoginResponse loginResponse;
    UserLogoutRequest logoutRequest = new UserLogoutRequest();
    LogoutResponse logoutResponse;
    User requestedUser;

    UserDeletionRequest deletionRequest = new UserDeletionRequest();

    @Before
    public void setUp() {
        RestAssured.baseURI = urls.getStellarBurgerProd();
        requestedUser = new User().withEmail(generate.randomEmail())
                                  .withPassword(generate.randomPassword(8))
                                  .withName(generate.randomName());

        registrationResponse = registrationRequest.userRegistration(requestedUser);
        loginResponse = loginRequest.userLogin(new User().withEmail(requestedUser.email())
                                                         .withPassword(requestedUser.password()));
    }

    @Test
    public void smokeUserLoginTest() {
        logoutResponse = logoutRequest.userLogout(new Token().withToken(loginResponse.refreshToken()));

        assertThat(logoutRequest.statusCode).isEqualTo(200);
        assertThat(logoutResponse.success()).isEqualTo(true);
        assertThat(logoutResponse.message()).isEqualTo(messages.getSUCCESSFUL_LOGOUT());
    }

    @After
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }

}
