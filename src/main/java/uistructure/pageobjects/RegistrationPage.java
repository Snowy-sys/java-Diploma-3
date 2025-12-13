package uistructure.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage {

    private WebDriver driver;

    // Кнопка "Войти в аккаунт"
    private final By buttonSignInAccount = By.className("button_button__33qZ0");

    // Кнопка "Зарегистрироваться"
    private final By buttonSignUpAccount = By.cssSelector(".Auth_link__1fOlj");

    // Поле для ввода имени
    private final By inputNameNewAccount = By.xpath(".//fieldset[1]//input[@type='text']");

    // Поле для ввода email
    private final By inputEmailNewAccount = By.xpath(".//fieldset[2]//input[@type='text']");

    // Поле для ввода пароля
    private final By inputPasswordNewAccount = By.xpath(".//input[@type='password']");

    // Доступность страницы авторизации
    private final By authorizationAcceptPage = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonSignIn() {
        assertTrue(driver.findElement(buttonSignInAccount).isEnabled());
        driver.findElement(buttonSignInAccount).click();
    }

    public void clickButtonSignUp() {
        assertTrue(driver.findElement(buttonSignUpAccount).isEnabled());
        driver.findElement(buttonSignUpAccount).click();
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

    public void registationOfNewUser(String name, String email, String password){
        sendKeysNameOfNewUser(name);
        sendKeysEmailOfNewUser(email);
        sendKeysPasswordOfNewUser(password);

        clickButtonSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationAcceptPage));

    }




}
