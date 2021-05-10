import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Homework1 {

    private Logger logger = LogManager.getLogger(Homework1.class);
    protected static WebDriver driver;

    @BeforeTest
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @AfterTest
    public void end() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void titleVerification() {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

        driver.get(cfg.URL());
        logger.info("Открыта страница");
        logger.debug("Открыта страница: " + cfg.URL());

        Assert.assertNotNull(driver.getTitle());
        logger.info("Проверка, что заголовок не пустой");
        logger.debug("Проверка, что заголовок не пустой: " + driver.getTitle());

        Assert.assertEquals(driver.getTitle(), "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
        logger.info("Проверка названия заголовка");
        logger.debug("Проверка названия заголовка " + driver.getTitle());

    }
}
