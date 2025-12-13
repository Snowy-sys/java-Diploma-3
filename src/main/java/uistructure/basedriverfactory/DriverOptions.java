package uistructure.basedriverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static uistructure.constants.Url.BASE_URL;
import static uistructure.constants.Url.REGISTER_URL;

public class DriverOptions {

    public RemoteWebDriver driver;

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            setupFirefox();
        } else if ("yandex".equals(System.getProperty("browser"))) {
            initYandex();
        } else {
            setupChrome();
        }
    }

    public void initUrlBrowser(String url){

        if(url.equals(REGISTER_URL)) {
            driver.get(REGISTER_URL);
        } else if(url.equals(BASE_URL)) {
            driver.get(BASE_URL);
        }
    }

    public void setupChrome() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    public void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
        var opts = new FirefoxOptions().setBinary("/usr/bin/firefox");

        driver = new FirefoxDriver(opts);
    }

    public void initYandex() {
        WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();

        var options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.bin"));

        driver = new ChromeDriver(options);
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }
}
