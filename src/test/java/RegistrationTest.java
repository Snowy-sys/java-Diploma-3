import apistructure.steps.userdelete.UserDeleteMainSteps;
import apistructure.steps.userlogin.UserLoginMainSteps;
import uistructure.basedriverfactory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uistructure.pageobjects.LoginPage;
import uistructure.pageobjects.RegistrationPage;

import static constants.TestData.*;
import static uistructure.constants.Url.REGISTER_URL;

public class RegistrationTest extends DriverFactory {

    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    private UserLoginMainSteps userLoginMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;

    @BeforeEach
    @DisplayName("Инициализация драйвера, запуск браузера и конструкторов")
    public void initRegistration() {
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);

        initUrlBrowser(REGISTER_URL);

        userLoginMainSteps = new UserLoginMainSteps();
        userDeleteMainSteps = new UserDeleteMainSteps();
    }

    @AfterEach
    @DisplayName("Авторизация и удаление нового пользователя после создания")
    public void deleteNewUser() {
        userLoginMainSteps.sendPostRequestUserLogin(EMAIL,PASSWORD);

        userDeleteMainSteps.setAccessToken(userLoginMainSteps.getAccessToken());
        userDeleteMainSteps.deleteUserIfExists();
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void checkCorrectNewUserRegistration() {
        registrationPage.registationOfNewUser(FIRST_NAME, EMAIL, PASSWORD);
        loginPage.checkAccessAutorizationPage();

    }

    @Test
    @DisplayName("Попытка регистрации с некорректным паролем")
    public void checkMessageOfIncorrectPassword() {
        registrationPage.registationOfNewUser(FIRST_NAME, EMAIL, INCORRECT_PASSWORD);
        registrationPage.checkMessageIncorrectPassword();
    }
}
