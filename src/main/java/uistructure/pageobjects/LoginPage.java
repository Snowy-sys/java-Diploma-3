package uistructure.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Поле для ввода email
    private final By inputEmailField = By.xpath(".//input[@type='text']");

    // Поле для ввода пароля
    private final By inputPasswordField = By.xpath(".//input[@type='password']");

}
