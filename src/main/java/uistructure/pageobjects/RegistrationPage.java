package uistructure.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage {

    private WebDriver driver;

    // Поле для ввода имени
    private final By inputNameNewAccount = By.xpath(".//fieldset[1]//input[@type='text']");

    // Поле для ввода email
    private final By inputEmailNewAccount = By.xpath(".//fieldset[2]//input[@type='text']");

    // Поле для ввода пароля
    private final By inputPasswordNewAccount = By.xpath(".//input[@type='password']");

    // Кнопка "Зарегистрироваться"
    private final By buttonSignUp = By.xpath(".//button[text()='Зарегистрироваться']");

    // Сообщение о некорректном пароле
    private final By messageIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");

    // Кнопка "Войти" на форме регистрации
    private final By buttonSignInRegistrationForm = By.cssSelector(".Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeysNameOfNewUser(String name) {
        driver.findElement(inputNameNewAccount).sendKeys(name);
    }

    public void sendKeysEmailOfNewUser(String email) {
        driver.findElement(inputEmailNewAccount).sendKeys(email);
    }

    public void sendKeysPasswordOfNewUser(String password) {
        driver.findElement(inputPasswordNewAccount).sendKeys(password);
    }

    public void clickButtonSignUp() {
        assertTrue(driver.findElement(buttonSignUp).isEnabled());
        driver.findElement(buttonSignUp).click();
    }

    public void registationOfNewUser(String name, String email, String password){
        sendKeysNameOfNewUser(name);
        sendKeysEmailOfNewUser(email);
        sendKeysPasswordOfNewUser(password);

        clickButtonSignUp();
    }

    public String getMessageIncorrectPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(messageIncorrectPassword));
        assertTrue(driver.findElement(messageIncorrectPassword).isEnabled());
        return driver.findElement(messageIncorrectPassword).getText();
    }

    public void checkMessageIncorrectPassword() {
        String actualResult = getMessageIncorrectPassword();
        assertTrue(actualResult.contains("Некорректный пароль"), "Не найден текст 'Некорректный пароль'");
    }

    public void clickButtonSignInRegistrationForm() {
        assertTrue(driver.findElement(buttonSignInRegistrationForm).isEnabled());
        driver.findElement(buttonSignInRegistrationForm).click();
    }
}
