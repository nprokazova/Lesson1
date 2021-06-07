import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Homework8 {
    protected static WebDriver driver;
    protected Actions action;

    private Logger logger = LogManager.getLogger(Homework8.class);
    ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
    public void productComparison(){
        //Открыть в Chrome сайт Яндекс.Маркет
        driver.get(cfg.URL_MARKET_YA());
        logger.info("Страница https://market.yandex.ru/ открыта");

        //Переход на страницу "Электроника"
        driver.findElement(By.cssSelector("a[href='/catalog--elektronika/54440']")).click();
        logger.info("Клик на 'Электроника' прошел успешно");

        //Переход на страницу "Смартфоны"
        driver.findElement(By.xpath("//a[text()='Смартфоны']")).click();
        logger.info("Клик на 'Смартфоны' прошел успешно");

        //Отфильтровать список товаров: Samsung
        driver.findElement(By.xpath("//input[@id='7893318_153061']/..")).click();
        logger.info("Выбор Samsung прошел успешно");

        //Отфильтровать список товаров: Xiaomi
        driver.findElement(By.xpath("//input[@id='7893318_7701962']/..")).click();
        logger.info("Выбор Xiaomi прошел успешно");

        //Отсортировать список товаров по цене (от меньшей к большей)
        driver.findElement(By.cssSelector("[data-autotest-id='dprice']")).click();
        logger.info("Сортировка по цене от меньшей к большей");
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[data-tid='8bc8e36b']"))));

        //Добавить первый в списке Samsung
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("((//*[@data-autotest-id='product-snippet']//*[contains(span, 'Samsung')])[1]/../../../..//*[@data-tid='64a067c1'])[2]")));
        driver.findElement(By.xpath("((//*[@data-autotest-id='product-snippet']//*[contains(span, 'Samsung')])[1]/../../../..//*[@data-tid='64a067c1'])[2]")).click();

        //Проверить, что отобразилась плашка "Товар Samsung добавлен к сравнению"
        new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-tid='11882e1c']")));
        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='Сравнить']")).size()!=0);
        logger.info("Проверка, что отобразилась плашка \"Товар Samsung добавлен к сравнению\", прошла успешно");

        //Добавить первый в списке Xiaomi
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("((//*[@data-autotest-id='product-snippet']//*[contains(span, 'Xiaomi')])[1]/../../../..//*[@data-tid='64a067c1'])[2]")));
        driver.findElement(By.xpath("((//*[@data-autotest-id='product-snippet']//*[contains(span, 'Xiaomi')])[1]/../../../..//*[@data-tid='64a067c1'])[2]")).click();

        //Проверить, что отобразилась плашка "Товар Xiaomi добавлен к сравнению"
        new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-tid='11882e1c']")));
        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='Сравнить']")).size()!=0);
        logger.info("Проверка, что отобразилась плашка \"Товар Xiaomi добавлен к сравнению\", прошла успешно");


        //Перейти в раздел Сравнение
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Сравнить']"))));
        driver.findElement(By.xpath("//*[text()='Сравнить']")).click();
        logger.info("Переход на страницу Сравнения");

        //Проверить, что в списке товаров 2 позиции
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-tid='7144661']"))));
        Assert.assertEquals(driver.findElements(By.cssSelector("[data-tid='412661c']")).size(),2);
        logger.info("Проверка, что в списке товаров 2 позиции");
    }
}
