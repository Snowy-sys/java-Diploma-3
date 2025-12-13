import apistructure.steps.usercreate.UserCreateMainSteps;
import apistructure.steps.userdelete.UserDeleteMainSteps;
import apistructure.steps.userlogin.UserLoginMainSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uistructure.basedriverfactory.DriverFactory;
import uistructure.pageobjects.LoginPage;
import uistructure.pageobjects.RegistrationPage;

import static constants.TestData.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uistructure.constants.Url.*;

public class AuthorizationTest extends DriverFactory {

    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private UserCreateMainSteps userCreateMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;

    @BeforeEach
    @DisplayName("Инициализация драйвера, запуск браузера, конструктора и создание нового пользователя")
    public void initBrowserAndNewUserApiCreation() {
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
        registrationPage.clickButtonSignIn();
        registrationPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        registrationPage.clickButtonSignIn();

        String actualResult = loginPage.getNameOfOderButton();
        assertTrue(actualResult.contains("Оформить заказ"), "Не найден текст 'Оформить заказ'");
    }

    @Test
    @DisplayName("Авторизация через кнопку Личный Кабинет на главной странице")
    public void checkSignInThroughButtonPersonalAccount() {
        loginPage.clickButtonPersonalAccount();
        registrationPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        registrationPage.clickButtonSignIn();

        String actualResult = loginPage.getNameOfOderButton();
        assertTrue(actualResult.contains("Оформить заказ"), "Не найден текст 'Оформить заказ'");
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме регистрации")
    public void checkSignInThroughButtonInSignUpForm() {
        registrationPage.clickButtonSignIn();
        registrationPage.checkAccessAutorizationPage();

        loginPage.clickButtonSignUp();
        loginPage.clickButtonSignInRegistrationForm();
        registrationPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        registrationPage.clickButtonSignIn();

        String actualResult = loginPage.getNameOfOderButton();
        assertTrue(actualResult.contains("Оформить заказ"), "Не найден текст 'Оформить заказ'");
    }

    @Test
    @DisplayName("Авторизация через кнопку восстановления пароля")
    public void checkSignInThroughButtonResetPassword() {
        registrationPage.clickButtonSignIn();
        registrationPage.checkAccessAutorizationPage();

        loginPage.clickButtonResetPassword();
        loginPage.clickButtonSignInRegistrationForm();
        registrationPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        registrationPage.clickButtonSignIn();

        String actualResult = loginPage.getNameOfOderButton();
        assertTrue(actualResult.contains("Оформить заказ"), "Не найден текст 'Оформить заказ'");
    }
}
