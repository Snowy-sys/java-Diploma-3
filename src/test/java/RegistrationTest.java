import apistructure.steps.general.ResponseSteps;
import apistructure.steps.general.StatusCodeSteps;
import apistructure.steps.userdelete.UserDeleteMainSteps;
import apistructure.steps.userlogin.UserLoginMainSteps;
import uistructure.basedriverfactory.DriverFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uistructure.pageobjects.RegistrationPage;

import static constants.TestData.*;
import static uistructure.constants.Url.REGISTER_URL;

public class RegistrationTest extends DriverFactory {

    private RegistrationPage registrationPage;
    private UserLoginMainSteps userLoginMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;
    private ResponseSteps responseSteps;
    private StatusCodeSteps statusCodeSteps;

    @BeforeEach
    public void initRegistration() {
        registrationPage = new RegistrationPage(driver);
        initUrlBrowser(REGISTER_URL);

        userLoginMainSteps = new UserLoginMainSteps();
        userDeleteMainSteps = new UserDeleteMainSteps();
        responseSteps = new ResponseSteps();
        statusCodeSteps = new StatusCodeSteps();
    }

    @AfterEach
    @DisplayName("Авторизация и удаление нового пользователя после создания")
    public void deleteNewUser() {

        Response response =
                userLoginMainSteps.sendPostRequestUserLogin(EMAIL,PASSWORD);

        statusCodeSteps.checkSuccessfulCodeStatus200(response);
        responseSteps.checkSuccessfulResponse(response);
        responseSteps.checkAccessTokenNewUser(response);
        responseSteps.checkRefreshTokenNewUser(response);
        responseSteps.checkEmailNewUser(response);
        responseSteps.checkNameNewUser(response);

        userDeleteMainSteps.setAccessToken(userLoginMainSteps.getAccessToken());
        userDeleteMainSteps.deleteUserIfExists();
    }

    @Test
    public void checkCorrectNewUserRegistration() {
        registrationPage.registationOfNewUser(FIRST_NAME, EMAIL, PASSWORD);
    }
}
