package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseHooks {
    protected static WebDriver driver;

    @Before
    public static void setup() {
        driver = WebDriverFactory.createDriver(Browsers.CHROME);

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @After
    public void cleanUp() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }

}