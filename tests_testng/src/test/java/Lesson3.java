import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Lesson3 {

    private Logger logger = LogManager.getLogger(Lesson3.class);
    protected static WebDriver driver;

    @BeforeTest
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @AfterTest
    public void end(){
        if (driver!=null)
            driver.quit();
    }

    @Test
    public void logExample(){
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

        logger.info("Start test: info level");
        logger.error("Start test: error level");
        logger.warn("Start test: warm level");
        logger.fatal("Start test: fatal level");
        logger.debug("Start test: debug level");
        logger.trace("Start test: trace level");

        System.out.println("Server " + cfg.hostname() + ":" + cfg.port() +
                " will run " + cfg.maxThreads());
    }

    @Test
    public void webDriverTest(){
        driver.get("https://otus.ru");
        logger.info("Сайт открыт");

    }

}
