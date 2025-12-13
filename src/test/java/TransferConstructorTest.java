import apistructure.steps.usercreate.UserCreateMainSteps;
import apistructure.steps.userdelete.UserDeleteMainSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uistructure.basedriverfactory.DriverFactory;
import uistructure.pageobjects.*;

import static constants.TestData.*;
import static uistructure.constants.Url.BASE_URL;

public class TransferConstructorTest extends DriverFactory {

    private HomePage homePage;
    private LoginPage loginPage;

    private UserCreateMainSteps userCreateMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;

    @BeforeEach
    @DisplayName("Инициализация драйвера, запуск браузера, конструктора и создание нового пользователя")
    public void initBrowserAndNewUserApiCreation() {
        homePage = new HomePage(driver);
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
    @DisplayName("Переход в конструктор ИЗ личного кабинета ДО авторизации пользователя")
    public void checkTransferToConstructorWithoutAuth() {
        homePage.clickButtonPersonalAccount();
        loginPage.checkAccessAutorizationPage();
        homePage.clickButtonConstructor();

        homePage.checkMessageAboutBurger();
    }

    @Test
    @DisplayName("Переход в конструктор через логотип ИЗ личного кабинета ДО авторизации пользователя")
    public void checkTransferToConstructorThroughLogoWithoutAuth() {
        homePage.clickButtonPersonalAccount();
        loginPage.checkAccessAutorizationPage();
        homePage.clickButtonLogo();

        homePage.checkMessageAboutBurger();
    }

    @Test
    @DisplayName("Переход в конструктор ИЗ личного кабинета ПОСЛЕ авторизации пользователя")
    public void checkTransferToConstructorWithAuth() {
        homePage.clickButtonPersonalAccount();
        loginPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        homePage.clickButtonPersonalAccount();

        homePage.clickButtonConstructor();
        homePage.checkMessageAboutBurger();
    }

    @Test
    @DisplayName("Переход в конструктор через логотип ИЗ личного кабинета ПОСЛЕ авторизации пользователя")
    public void checkTransferToConstructorThroughLogoWithAuth() {
        homePage.clickButtonPersonalAccount();
        loginPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        homePage.clickButtonPersonalAccount();

        homePage.clickButtonLogo();
        homePage.checkMessageAboutBurger();
    }
}
