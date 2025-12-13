package uistructure.pageobjects;

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

    // Кнопка "Оформить заказ"
    private final By buttonOrderCreate = By.xpath(".//button[text()='Оформить заказ']");

    // Кнопка "Личный кабинет"
    private final By buttonPersonalAccount = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']/p[text()='Личный Кабинет']");

    // Кнопка "Зарегистрироваться"
    private final By buttonSignUp = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");

    // Кнопка "Войти" на форме регистрации
    private final By buttonSignInRegistrationForm = By.cssSelector(".Auth_link__1fOlj");

    // Кнопка "Восстановить пароль"
    private final By buttonResetPassword = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    public void sendKeysEmailOfUser(String email) {
        driver.findElement(inputEmailField).sendKeys(email);
    }

    public void sendKeysPasswordOfUser(String password) {
        driver.findElement(inputPasswordField).sendKeys(password);
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void authorizationOnUser(String email, String password) {
        sendKeysEmailOfUser(email);
        sendKeysPasswordOfUser(password);
    }

    public String getNameOfOderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonOrderCreate));
        assertTrue(driver.findElement(buttonOrderCreate).isEnabled());
        return driver.findElement(buttonOrderCreate).getText();
    }

    public void clickButtonPersonalAccount() {
        assertTrue(driver.findElement(buttonPersonalAccount).isEnabled());
        driver.findElement(buttonPersonalAccount).click();
    }

    public void clickButtonSignUp() {
        assertTrue(driver.findElement(buttonSignUp).isEnabled());
        driver.findElement(buttonSignUp).click();
    }

    public void clickButtonSignInRegistrationForm() {
        assertTrue(driver.findElement(buttonSignInRegistrationForm).isEnabled());
        driver.findElement(buttonSignInRegistrationForm).click();
    }

    public void clickButtonResetPassword() {
        assertTrue(driver.findElement(buttonResetPassword).isEnabled());
        driver.findElement(buttonResetPassword).click();
    }
}
