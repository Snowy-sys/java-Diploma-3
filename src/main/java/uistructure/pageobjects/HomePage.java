package uistructure.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {
    private WebDriver driver;

    // Кнопка "Войти в аккаунт"
    private final By buttonSignInAccount = By.className("button_button__33qZ0");

    // Кнопка "Оформить заказ"
    private final By buttonOrderCreate = By.xpath(".//button[text()='Оформить заказ']");

    // Кнопка "Личный кабинет"
    private final By buttonPersonalAccount = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']/p[text()='Личный Кабинет']");

    // Кнопка "Конструктор"
    private final By buttonConstructor = By.xpath(".//p[text()='Конструктор']");

    // Подтверждение перехода в конструктор
    private final By textAboutBurger = By.cssSelector(".text.text_type_main-large.mb-5.mt-10");

    // Кнопка логотипа
    private final By buttonMainLogo = By.cssSelector(".AppHeader_header__logo__2D0X2");

    // Кнопка "Булки" в конструкторе
    private final By buttonBuns = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");

    // Кнопка "Соусы" в конструкторе
    private final By buttonSauces = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");

    // Кнопка "Начинки" в конструкторе
    private final By buttonToppings = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonSignIn() {
        assertTrue(driver.findElement(buttonSignInAccount).isEnabled());
        driver.findElement(buttonSignInAccount).click();
    }

    public String getNameOfOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonOrderCreate));
        assertTrue(driver.findElement(buttonOrderCreate).isEnabled());
        return driver.findElement(buttonOrderCreate).getText();
    }

    public void checkNameOfOrderButton(){
        String actualResult = getNameOfOrderButton();
        assertTrue(actualResult.contains("Оформить заказ"), "Не найден текст 'Оформить заказ'");
    }

    public void clickButtonPersonalAccount() {
        assertTrue(driver.findElement(buttonPersonalAccount).isEnabled());
        driver.findElement(buttonPersonalAccount).click();
    }

    public void clickButtonConstructor() {
        assertTrue(driver.findElement(buttonConstructor).isEnabled());
        driver.findElement(buttonConstructor).click();
    }

    public String getTextAboutBurger() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textAboutBurger));
        assertTrue(driver.findElement(textAboutBurger).isEnabled());
        return driver.findElement(textAboutBurger).getText();
    }

    public void checkMessageAboutBurger() {
        String actualResult = getTextAboutBurger();
        assertTrue(actualResult.contains("Соберите бургер"), "Не найден текст 'Соберите бургер'");
    }

    public void clickButtonLogo() {
        assertTrue(driver.findElement(buttonMainLogo).isEnabled());
        driver.findElement(buttonMainLogo).click();
    }

    public void clickButtonBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonBuns));

        assertTrue(driver.findElement(buttonBuns).isEnabled());
        driver.findElement(buttonBuns).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(buttonBuns, "class", "current"));
    }

    public void clickButtonSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonSauces));

        assertTrue(driver.findElement(buttonSauces).isEnabled());
        driver.findElement(buttonSauces).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(buttonSauces, "class", "current"));
    }

    public void clickButtonToppings() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonToppings));

        assertTrue(driver.findElement(buttonToppings).isEnabled());
        driver.findElement(buttonToppings).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(buttonToppings, "class", "current"));
    }

    public void clickAllSectionOfConstructor(){
        clickButtonToppings();
        clickButtonSauces();
        clickButtonBuns();
    }
}
