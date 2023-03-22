package tests.user;

import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserResetPasswordRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.registration.RegistrationResponse;
import model.responses.user.resetpassword.ResetPasswordResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserResetPasswordTests extends TestHelper {

    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;
    UserResetPasswordRequest resetPasswordRequest = new UserResetPasswordRequest();
    ResetPasswordResponse resetPasswordResponse;
    UserDeletionRequest deletionRequest = new UserDeletionRequest();
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
    public void smokeUserResetPasswordTest() {
        resetPasswordResponse = resetPasswordRequest.resetPassword(new User().withEmail(user.email()));

        assertThat(resetPasswordRequest.statusCode).isEqualTo(200);
        assertThat(resetPasswordResponse.getSuccess()).isEqualTo(true);
        assertThat(resetPasswordResponse.getMessage()).isEqualTo(messages.getRESET_EMAIL_SENT());
    }

    @AfterMethod
    public void tearDown() {
        if(registrationResponse.accessToken() != null) {
            deletionRequest.userDeletion(registrationResponse.accessToken());
        }
    }

}
