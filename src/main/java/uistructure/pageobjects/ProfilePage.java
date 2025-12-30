package uistructure.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilePage {

    private WebDriver driver;

    // Подтверждение перехода в личный кабинет авторизованного пользователя
    private final By textForAuthUser = By.xpath(".//nav[@class='Account_nav__Lgali']/p");

    // Кнопка "Выход"
    private final By buttonExitPersonalAccount = By.xpath(".//button[@type='button' and text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Найти сообщение в личном кабинете о наличии возможности изменения перс.данных")
    public String getTextForAuthUser() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textForAuthUser));
        assertTrue(driver.findElement(textForAuthUser).isEnabled());
        return driver.findElement(textForAuthUser).getText();
    }

    @Step("Проверить корректность текста на кнопке 'Собрать бургер'")
    public void checkMessageForAuthUser() {
        String actualResult = getTextForAuthUser();
        assertTrue(actualResult.contains("В этом разделе вы можете изменить свои персональные данные"), "Не найден текст о возможности изменения данных");
    }

    @Step("Нажать на кнопку 'Выход'")
    public void clickButtonExitPersonalAccount() {
        assertTrue(driver.findElement(buttonExitPersonalAccount).isEnabled());
        driver.findElement(buttonExitPersonalAccount).click();
    }
}
