import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SampleTest {

    private Logger logger = LogManager.getLogger(Homework1.class);
    protected static WebDriver driver;

    @BeforeTest
    public void StartUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void testBootstrapr() throws InterruptedException {

        final String URL = "https://ng-bootstrap.github.io/#/components/alert/examples";
        final String ERROR_MESSAGE = "Message are equal";
        final String BUTTON = "//button[contains(text(),'Change message')]";

        driver.get(URL);
        logger.info(String.format("Страница открыта %s", URL));
        WebElement element = driver.findElement(By.xpath(BUTTON));
        logger.info("Кнопка найдена");

        String alertTextBefore = getAlertText(element);
        logger.info(String.format("Alert text %s", alertTextBefore));

        logger.info("Начато ожидание");
        Thread.sleep(1500);
        logger.info("Ожидание закончено");

        String alertTextAfter = getAlertText(element);
        logger.info(String.format("Alert text %s", alertTextAfter));

        Assert.assertNotEquals(alertTextBefore, alertTextAfter, ERROR_MESSAGE);

    }

    private String getAlertText(WebElement element) {
        String alert = "//ngb-alert[contains(text(), 'Message successfully changed'";
        element.click();
        WebElement alertBox = driver.findElement(By.xpath(alert));

        new WebDriverWait(driver, 4).until(visibilityOf(alertBox));
        return alertBox.getText();

    }

}
