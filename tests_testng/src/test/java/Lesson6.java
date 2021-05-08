import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.concurrent.TimeUnit;

public class Lesson6 {

    private Logger logger = LogManager.getLogger(Homework1.class);
    protected static WebDriver driver;

    ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeTest
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void auth() {
        String login = "test.ru";
        String password = "yturoex";
        String locator = "button.header2__auth";
        WebElement el = driver.findElement(By.cssSelector(locator));
        el.click();
        driver.findElement(By.cssSelector(locator));
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3)>input:nth-child(1)")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5)>button:nth-child(1)")).submit();

        logger.info("Авторизация прошла успешно");
    }

    public void enterLK(){
        String locator = ".ic-blog-default-avatar";
        WebElement icon = driver.findElement(By.cssSelector(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(icon).build().perform();
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Перешел в личный кабинет");
    }

    @Test
    public void openPage(){
        //Открыть otus.ru
        driver.get(cfg.URL());
        logger.info("Страница открыта");
        //Авторизоваться на сайте
        auth();
        //Войтив личный кабинет
        enterLK();
        //В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        driver
                .findElement(By.id("id_fname_latin"))
                .clear();
//        driver.findElement(By.id("id_lname")).clear();
//        driver.findElement(By.id("id_lname_latin")).clear();
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).clear();

        driver.findElement(By.id("id_fname_latin")).sendKeys("Nataly");
//        driver.findElement(By.id("id_lname")).sendKeys("N");
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).sendKeys("20.03.1990");

        //Страна
        if(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).isDisplayed())
        {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)"));
            driver.findElement(By.xpath("//*[contains(text(), 'Россия')]")).click();
        }

        //Нажать сохранить
        driver.findElement(By.xpath("//*[contains(text(), 'Сохранить и продолжить')]")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://otus.ru/lk/biography/skills/"));

        //Открыть в чистом браузере
        driver.quit();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
        driver.get(cfg.URL());

        //Авторизоваться на сайте
        auth();

        //Войти в личный кабинет
        enterLK();

        //Проверить, что в разделе "о себе" отображаются указанные ранее данные
        Assert.assertEquals("Nataly", driver.findElement(By.id("id_fname_latin")).getAttribute("Value"));
        Assert.assertEquals("20.03.1990", driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).getAttribute("Value"));


    }

}
