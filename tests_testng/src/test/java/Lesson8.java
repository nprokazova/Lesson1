import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Lesson8 {

    protected static WebDriver driver;
    protected Actions action;

    private Logger logger = LogManager.getLogger(SampleTest.class);

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4L, TimeUnit.SECONDS);
        action = new Actions(driver);
        logger.info("Драйвер поднят");
    }

    @AfterTest
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void alert() throws InterruptedException {
        driver.get("http://subdomain.localhost/alert.html");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Появился alert");
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        String actual = alert.getText();
        logger.info(alert.getText());
        alert.accept();
        Assert.assertEquals(actual, "Test Alert");
    }

    @Test
    public void confirm_accept() {
        driver.get("http://subdomain.localhost/confirm.html");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Появился confirm");
        Alert alert = driver.switchTo().alert();
        String actual = alert.getText();
        logger.info(alert.getText());

        alert.accept();

        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Появился alert");
        alert = driver.switchTo().alert();
        actual = alert.getText();
        logger.info(actual);
        alert.accept();
        Assert.assertEquals(actual, "true");
    }

    @Test
    public void confirm_dismiss() {
        driver.get("http://subdomain.localhost/confirm.html");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Появился confirm");
        Alert alert = driver.switchTo().alert();
        logger.info(alert.getText());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alert.dismiss();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Появился alert");
        alert = driver.switchTo().alert();
        String actual = alert.getText();
        logger.info(actual);
        alert.accept();
        Assert.assertEquals(actual, "false");

    }

    @Test
    public void prompt() {
        driver.get("http://subdomain.localhost/promrt.html");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Появился prompt");
        Alert alert = driver.switchTo().alert();
        logger.info(alert.getText());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alert.sendKeys("Test");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alert.accept();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Появился alert");
        alert = driver.switchTo().alert();
        String actual = alert.getText();
        logger.info(actual);
        alert.accept();
        Assert.assertEquals(actual, "Hello Test");
    }

    @Test
    public void upload() {
        String filePath = "C:\\User\\...";
        String fileName = "test.jpg";
        driver.get("http://subdomain.localhost/deletefiles.php");
        logger.info("Удалили все картинки");
        driver.get("http://subdomain.localhost/upload.php");
        driver
                .findElement(By.id("inputfile"))
                .sendKeys(filePath + fileName);

        driver.findElement(By.id("submit")).click();
        logger.info("Загрузили картинку");
        driver.get("http://subdomain.localhost/files/" + fileName);
        List <WebElement> imgs = driver.findElements(By.tagName("img"));
        Assert.assertEquals(imgs.size(), 1);
    }

    @Test
    public void iframe() {
        driver.get("http://subdomain.localhost/iframe.html");
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        //driver.switchTo().defaultContent();
        logger.info("Переходим в iframe");
        String actual = driver.findElement(By.tagName("H1")).getText();
        Assert.assertEquals(actual, "Wee");
    }
}