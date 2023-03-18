package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserChangeDataRequest;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.changedata.ChangeDataResponse;
import model.responses.user.deletion.DeletionResponse;
import model.responses.user.registration.RegistrationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserChangeDataTests extends TestHelper {

    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserChangeDataRequest changeDataRequest = new UserChangeDataRequest();
    ChangeDataResponse changeDataResponse;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();
    User firstUserData;
    User secondUserData;

    @Before
    public void setUp() {
        RestAssured.baseURI = urls.getStellarBurgerProd();
        firstUserData = new User().withEmail(generate.randomEmail())
                                  .withPassword(generate.randomPassword(8))
                                  .withName(generate.randomName());

        registrationResponse = registrationRequest.userRegistration(firstUserData);
    }

    @Test
    public void smokeUserChangeDataTest() {
        secondUserData = new User().withEmail(generate.randomEmail())
                                   .withPassword(generate.randomPassword(7))
                                   .withName(generate.randomName());

        changeDataResponse = changeDataRequest.changeUserData(secondUserData, registrationResponse.accessToken());

        assertThat(changeDataRequest.statusCode).isEqualTo(200);
        assertThat(changeDataResponse.success()).isEqualTo(true);
        assertThat(changeDataResponse.user().email()).isEqualTo(secondUserData.email());
        assertThat(changeDataResponse.user().name()).isEqualTo(secondUserData.name());
    }

    @After
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
