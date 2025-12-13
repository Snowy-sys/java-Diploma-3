import apistructure.steps.usercreate.UserCreateMainSteps;
import apistructure.steps.userdelete.UserDeleteMainSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uistructure.basedriverfactory.DriverFactory;
import uistructure.pageobjects.HomePage;
import uistructure.pageobjects.LoginPage;
import uistructure.pageobjects.RegistrationPage;

import static constants.TestData.*;
import static uistructure.constants.Url.*;

public class AuthorizationTest extends DriverFactory {

    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    private UserCreateMainSteps userCreateMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;

    @BeforeEach
    @DisplayName("Инициализация драйвера, запуск браузера, конструктора и создание нового пользователя")
    public void initBrowserAndNewUserApiCreation() {
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);

        initUrlBrowser(BASE_URL);

        userCreateMainSteps = new UserCreateMainSteps();
        userDeleteMainSteps = new UserDeleteMainSteps();

        userCreateMainSteps.sendPostRequestUserCreation(FIRST_NAME, EMAIL, PASSWORD);
        userDeleteMainSteps.setAccessToken(userCreateMainSteps.getAccessToken());

    }

    @AfterEach
    @DisplayName("Авторизация и удаление нового пользователя после создания")
    public void deleteNewUser() {
        userDeleteMainSteps.deleteUserIfExists();
    }

    @Test
    @DisplayName("Авторизация через кнопку Войти в аккаунт на главной странице")
    public void checkSignInThroughButtonSignIn() {
        homePage.clickButtonSignIn();
        loginPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        loginPage.clickButtonSignIn();

        homePage.checkNameOfOrderButton();
    }

    @Test
    @DisplayName("Авторизация через кнопку Личный Кабинет на главной странице")
    public void checkSignInThroughButtonPersonalAccount() {
        homePage.clickButtonPersonalAccount();
        loginPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        loginPage.clickButtonSignIn();

        homePage.checkNameOfOrderButton();
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме регистрации")
    public void checkSignInThroughButtonInSignUpForm() {
        homePage.clickButtonSignIn();
        loginPage.checkAccessAutorizationPage();

        loginPage.clickButtonSignUp();
        registrationPage.clickButtonSignInRegistrationForm();
        loginPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        loginPage.clickButtonSignIn();

        homePage.checkNameOfOrderButton();
    }

    @Test
    @DisplayName("Авторизация через кнопку восстановления пароля")
    public void checkSignInThroughButtonResetPassword() {
        homePage.clickButtonSignIn();
        loginPage.checkAccessAutorizationPage();

        loginPage.clickButtonResetPassword();
        registrationPage.clickButtonSignInRegistrationForm();
        loginPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        loginPage.clickButtonSignIn();

        homePage.checkNameOfOrderButton();
    }
}
