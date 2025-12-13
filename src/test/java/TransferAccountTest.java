import apistructure.steps.usercreate.UserCreateMainSteps;
import apistructure.steps.userdelete.UserDeleteMainSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uistructure.basedriverfactory.DriverFactory;
import uistructure.pageobjects.HomePage;
import uistructure.pageobjects.LoginPage;
import uistructure.pageobjects.ProfilePage;

import static constants.TestData.*;
import static uistructure.constants.Url.BASE_URL;

public class TransferAccountTest extends DriverFactory {

    private HomePage homePage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    private UserCreateMainSteps userCreateMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;

    @BeforeEach
    @DisplayName("Инициализация драйвера, запуск браузера, конструктора и создание нового пользователя")
    public void initBrowserAndNewUserApiCreation() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

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
    @DisplayName("Переход в личный кабинет ДО авторизации пользователя")
    public void checkTransferToPersonalAccountWithoutAuth() {
        homePage.clickButtonPersonalAccount();
        loginPage.checkAccessAutorizationPage();
    }

    @Test
    @DisplayName("Переход в личный кабинет ПОСЛЕ авторизации пользователя")
    public void checkTransferToPersonalAccountWithAuth() {
        homePage.clickButtonPersonalAccount();
        loginPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        loginPage.clickButtonSignIn();
        homePage.clickButtonPersonalAccount();

        profilePage.checkMessageForAuthUser();

    }

}
