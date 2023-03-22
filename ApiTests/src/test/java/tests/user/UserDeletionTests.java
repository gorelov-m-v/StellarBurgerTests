package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.deletion.DeletionResponse;
import model.responses.user.registration.RegistrationResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserDeletionTests extends TestHelper {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();
    DeletionResponse deletionResponse;
    User user;
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = urls.getStellarBurgerProd();
        user = new User().withEmail(generate.randomEmail())
                         .withPassword(generate.randomPassword(8))
                         .withName(generate.randomName(11));

        registrationResponse = registrationRequest.userRegistration(user);
    }

    @Test
    public void smokeDeletionTest() {
        deletionResponse = deletionRequest.userDeletion(registrationResponse.accessToken());

        assertThat(deletionRequest.statusCode).isEqualTo(202);
        assertThat(deletionResponse.success()).isEqualTo(true);
        assertThat(deletionResponse.message()).isEqualTo(messages.getUSER_SUCCESSFULLY_REMOVED());
    }

    @AfterMethod
    public void tearDown() {
        if(deletionRequest.statusCode != 202) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
