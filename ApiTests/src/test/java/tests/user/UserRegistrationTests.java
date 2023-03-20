package tests.user;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;
import model.data.User;
import model.requests.user.UserDeletionRequest;
import model.requests.user.UserRegistrationRequest;
import model.responses.user.registration.RegistrationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.assertj.core.api.Assertions.*;
@RunWith(DataProviderRunner.class)
public class UserRegistrationTests extends TestHelper {
    UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
    RegistrationResponse registrationResponse;

    UserDeletionRequest deletionRequest = new UserDeletionRequest();
    @Before
    public void setUp(){
        RestAssured.baseURI = urls.getStellarBurgerProd();
    }


    @DataProvider
    public static Object[][] data() {
    return new Object[][] {
            {1,  true},
            {2,  true},
            {0, false}
        };
    }

    @Test
    @UseDataProvider("data")
    public void smokeUserRegistrationTest(int length, boolean success) {
        User requestedUser = new User().withEmail(generate.randomEmail())
                                       .withPassword(generate.randomPassword(length))
                                       .withName(generate.randomName());

        registrationResponse = registrationRequest.userRegistration(requestedUser);

        assertThat(registrationResponse.success()).isEqualTo(success);
    }
    @Test
    public void smokeUserRegisterationTest2() {

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
