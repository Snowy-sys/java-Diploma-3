package uistructure.pageobjects;

import io.qameta.allure.Step;
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

    @Step("Нажать на кнопку 'Войти в аккаунт'")
    public void clickButtonSignIn() {
        assertTrue(driver.findElement(buttonSignInAccount).isEnabled());
        driver.findElement(buttonSignInAccount).click();
    }

    @Step("Найти кнопку 'Оформить заказ'")
    public String getNameOfOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonOrderCreate));
        assertTrue(driver.findElement(buttonOrderCreate).isEnabled());
        return driver.findElement(buttonOrderCreate).getText();
    }

    @Step("Проверить корректность текста на кнопке 'Оформить заказ'")
    public void checkNameOfOrderButton(){
        String actualResult = getNameOfOrderButton();
        assertTrue(actualResult.contains("Оформить заказ"), "Не найден текст 'Оформить заказ'");
    }

    @Step("Нажать на кнопку 'Личный кабинет'")
    public void clickButtonPersonalAccount() {
        assertTrue(driver.findElement(buttonPersonalAccount).isEnabled());
        driver.findElement(buttonPersonalAccount).click();
    }

    @Step("Нажать на кнопку 'Конструктор'")
    public void clickButtonConstructor() {
        assertTrue(driver.findElement(buttonConstructor).isEnabled());
        driver.findElement(buttonConstructor).click();
    }

    @Step("Найти кнопку 'Собрать бургер'")
    public String getTextAboutBurger() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textAboutBurger));
        assertTrue(driver.findElement(textAboutBurger).isEnabled());
        return driver.findElement(textAboutBurger).getText();
    }

    @Step("Проверить корректность текста на кнопке 'Собрать бургер'")
    public void checkMessageAboutBurger() {
        String actualResult = getTextAboutBurger();
        assertTrue(actualResult.contains("Соберите бургер"), "Не найден текст 'Соберите бургер'");
    }

    @Step("Нажать на кнопку логотипа Stellar Burgers")
    public void clickButtonLogo() {
        assertTrue(driver.findElement(buttonMainLogo).isEnabled());
        driver.findElement(buttonMainLogo).click();
    }

    @Step("Нажать на кнопку 'Булки' в конструкторе")
    public void clickButtonBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonBuns));

        assertTrue(driver.findElement(buttonBuns).isEnabled());
        driver.findElement(buttonBuns).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(buttonBuns, "class", "current"));
    }

    @Step("Нажать на кнопку 'Соусы' в конструкторе")
    public void clickButtonSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonSauces));

        assertTrue(driver.findElement(buttonSauces).isEnabled());
        driver.findElement(buttonSauces).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(buttonSauces, "class", "current"));
    }

    @Step("Нажать на кнопку 'Начинки' в конструкторе")
    public void clickButtonToppings() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonToppings));

        assertTrue(driver.findElement(buttonToppings).isEnabled());
        driver.findElement(buttonToppings).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(buttonToppings, "class", "current"));
    }

    @Step("Общий шаг: нажать на кнопки в конструкторе: 'Начинки', 'Соусы', 'Булки'")
    public void clickAllSectionOfConstructor(){
        clickButtonToppings();
        clickButtonSauces();
        clickButtonBuns();
    }
}
