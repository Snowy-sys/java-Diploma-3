package uistructure.basedriverfactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

public class DriverFactory extends DriverOptions {

    @BeforeEach
    void setup() {
        initDriver();

        if (driver == null) {
            throw new IllegalStateException("WebDriver не инициализирован");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            getDriver().quit();
        }
    }
}
