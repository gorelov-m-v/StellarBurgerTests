package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserChangeDataRequest;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.changedata.ChangeDataResponse;
import model.responses.user.registration.RegistrationResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserChangeDataTests extends TestHelper {

    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserChangeDataRequest changeDataRequest = new UserChangeDataRequest();
    ChangeDataResponse changeDataResponse;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();
    User firstUserData;
    User secondUserData;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = urls.getStellarBurgerProd();
        firstUserData = new User().withEmail(generate.randomEmail(20))
                                  .withPassword(generate.randomPassword(8))
                                  .withName(generate.randomName(11));

        registrationResponse = registrationRequest.userRegistration(firstUserData);
    }

    @Test
    public void smokeUserChangeDataTest() {
        secondUserData = new User().withEmail(generate.randomEmail(20))
                                   .withPassword(generate.randomPassword(8))
                                   .withName(generate.randomName(11));

        changeDataResponse = changeDataRequest.changeUserData(secondUserData, registrationResponse.accessToken());

        assertThat(changeDataRequest.statusCode).isEqualTo(200);
        assertThat(changeDataResponse.success()).isEqualTo(true);
        assertThat(changeDataResponse.user().email()).isEqualTo(secondUserData.email());
        assertThat(changeDataResponse.user().name()).isEqualTo(secondUserData.name());
    }

    @AfterMethod
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }
}
