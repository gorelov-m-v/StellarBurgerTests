package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserGetInfoRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.getinfo.GetInfoResponse;
import model.responses.user.registration.RegistrationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserGetInfoTest extends TestHelper {

    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserGetInfoRequest getInfoRequest = new UserGetInfoRequest();
    GetInfoResponse getInfoResponse;
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
    public void smokeUserGetInfoTest() {
        getInfoResponse = getInfoRequest.userGetInfo(registrationResponse.accessToken());

        assertThat(getInfoRequest.statusCode).isEqualTo(200);
        assertThat(getInfoResponse.success()).isEqualTo(true);
        assertThat(getInfoResponse.user().email()).isEqualTo(requestedUser.email());
        assertThat(getInfoResponse.user().name()).isEqualTo(requestedUser.name());
    }

    @After
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }


}