package uistructure.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {

    private WebDriver driver;

    // Поле для ввода email
    private final By inputEmailField = By.xpath(".//input[@type='text']");

    // Поле для ввода пароля
    private final By inputPasswordField = By.xpath(".//input[@type='password']");

    // Кнопка "Зарегистрироваться"
    private final By buttonSignUp = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");

    // Кнопка "Войти"
    private final By buttonSignIn = By.xpath(".//button[text()='Войти']");

    // Кнопка "Восстановить пароль"
    private final By buttonResetPassword = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    // Доступность страницы авторизации
    private final By authorizationAcceptPage = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email пользователя")
    public void sendKeysEmailOfUser(String email) {
        driver.findElement(inputEmailField).sendKeys(email);
    }

    @Step("Ввести пароль пользователя")
    public void sendKeysPasswordOfUser(String password) {
        driver.findElement(inputPasswordField).sendKeys(password);
    }

    @Step("Общий шаг: ввести email и пароль пользователя")
    public void authorizationOnUser(String email, String password) {
        sendKeysEmailOfUser(email);
        sendKeysPasswordOfUser(password);

        clickButtonSignIn();
    }

    @Step("Нажать на кнопку 'Войти'")
    public void clickButtonSignIn() {
        assertTrue(driver.findElement(buttonSignIn).isEnabled());
        driver.findElement(buttonSignIn).click();
    }

    @Step("Нажать на кнопку 'Зарегистрироваться'")
    public void clickButtonSignUp() {
        assertTrue(driver.findElement(buttonSignUp).isEnabled());
        driver.findElement(buttonSignUp).click();
    }

    @Step("Нажать на кнопку 'Восстановить пароль'")
    public void clickButtonResetPassword() {
        assertTrue(driver.findElement(buttonResetPassword).isEnabled());
        driver.findElement(buttonResetPassword).click();
    }

    @Step("Проверить переход на страницу авторизации пользователя")
    public void checkAccessAutorizationPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationAcceptPage));
        assertTrue(driver.findElement(authorizationAcceptPage).isEnabled());
    }
}
