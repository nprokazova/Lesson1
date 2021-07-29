package testCases;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import utils.Browsers;
import utils.WebDriverFactory;

import java.util.concurrent.TimeUnit;


public class BaseHooks {

    protected static WebDriver driver;
    private static String BROWSER;

    @BeforeClass
    public static void setup() {

        BROWSER = System.getProperty("browser");

        if (BROWSER == null || BROWSER.equalsIgnoreCase("CHROME")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(true);
            driver = WebDriverFactory.createDriver(Browsers.CHROME, chromeOptions);
        } else if (BROWSER.equalsIgnoreCase("FIREFOX")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setHeadless(true);
            driver = WebDriverFactory.createDriver(Browsers.FIREFOX, firefoxOptions);
        } else if (BROWSER.equalsIgnoreCase("OPERA")) {
            OperaOptions operaOptions = new OperaOptions();
            operaOptions.addArguments("headless");
            driver = WebDriverFactory.createDriver(Browsers.OPERA, operaOptions);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


//  @BeforeClass
//  public static void setup() {
//      driver = WebDriverFactory.createDriver(Browsers.CHROME);

//      if (driver != null) {
//          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//          driver.manage().window().maximize();
//      }
//  }

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