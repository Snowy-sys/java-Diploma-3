import apistructure.steps.usercreate.UserCreateMainSteps;
import apistructure.steps.userdelete.UserDeleteMainSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uistructure.basedriverfactory.DriverFactory;
import uistructure.pageobjects.LoginPage;
import uistructure.pageobjects.RegistrationPage;
import uistructure.pageobjects.TranferAccount;

import static constants.TestData.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uistructure.constants.Url.BASE_URL;

public class TransferAccountTest extends DriverFactory {

    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private UserCreateMainSteps userCreateMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;
    private TranferAccount tranferAccount;

    @BeforeEach
    @DisplayName("Инициализация драйвера, запуск браузера, конструктора и создание нового пользователя")
    public void initBrowserAndNewUserApiCreation() {
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        tranferAccount = new TranferAccount(driver);

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
        loginPage.clickButtonPersonalAccount();
        registrationPage.checkAccessAutorizationPage();
    }

    @Test
    @DisplayName("Переход в личный кабинет ПОСЛЕ авторизации пользователя")
    public void checkTransferToPersonalAccountWithAuth() {
        loginPage.clickButtonPersonalAccount();
        registrationPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        registrationPage.clickButtonSignIn();
        loginPage.clickButtonPersonalAccount();

        String actualResult = tranferAccount.getTextForAuthUser();
        assertTrue(actualResult.contains("В этом разделе вы можете изменить свои персональные данные"), "Не найден текст о возможности изменения данных");

    }

}
