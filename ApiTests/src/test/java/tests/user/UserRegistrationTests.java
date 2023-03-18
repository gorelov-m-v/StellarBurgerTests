package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.registration.RegistrationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class UserRegistrationTests extends TestHelper {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;

    UserDeletionRequest deletionRequest = new UserDeletionRequest();
    @Before
    public void setUp(){
        RestAssured.baseURI = urls.getStellarBurgerProd();
    }

    @Test
    public void smokeUserRegistrationTest() {
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

    @After
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
