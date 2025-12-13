package uistructure.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TranferAccount {

    private WebDriver driver;

    // Подтверждение перехода в личный кабинет авторизованного пользователя
    private final By textForAuthUser = By.xpath(".//nav[@class='Account_nav__Lgali']/p");

    public TranferAccount(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextForAuthUser() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textForAuthUser));
        assertTrue(driver.findElement(textForAuthUser).isEnabled());
        return driver.findElement(textForAuthUser).getText();
    }
}
