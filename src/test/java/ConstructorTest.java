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
import uistructure.pageobjects.TransferConstructor;

import static constants.TestData.*;
import static uistructure.constants.Url.BASE_URL;

public class ConstructorTest extends DriverFactory {

    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private UserCreateMainSteps userCreateMainSteps;
    private UserDeleteMainSteps userDeleteMainSteps;
    private TranferAccount tranferAccount;
    private TransferConstructor transferConstructor;

    @BeforeEach
    @DisplayName("Инициализация драйвера, запуск браузера, конструктора и создание нового пользователя")
    public void initBrowserAndNewUserApiCreation() {
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        tranferAccount = new TranferAccount(driver);
        transferConstructor = new TransferConstructor(driver);

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
    @DisplayName("Переход между разделами конструктора: булки, соусы, начинки ДО авторизации")
    public void checkTransferConstructorSectionsWithoutAuth() {
        transferConstructor.clickAllSectionOfConstructor();
    }

    @Test
    @DisplayName("Переход между разделами конструктора: булки, соусы, начинки ПОСЛЕ авторизации")
    public void checkTransferConstructorSectionsWithAuth() {
        loginPage.clickButtonPersonalAccount();
        registrationPage.checkAccessAutorizationPage();

        loginPage.authorizationOnUser(EMAIL, PASSWORD);
        registrationPage.clickButtonSignIn();

        transferConstructor.clickAllSectionOfConstructor();
    }
}
