import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Homework7 {

    private Logger logger = LogManager.getLogger(Homework7.class);
    protected static WebDriver driver;

    ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeTest
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterTest
    public void end(){
        if (driver!=null)
            driver.quit();
    }


    @Test
    public void addressVerification() {
        //Открыть otus.ru
        driver.get(cfg.URL());
        logger.info("Страница открыта");

        //переход во вкладку "Контакты" и проверка адреса
        driver.findElement(By.cssSelector("a.header2_subheader-link[href='/contacts/']")).click();
        logger.info("Клик на 'Контакты' прошел успешно");

        Assert.assertEquals(driver.findElement(By.cssSelector(".styles__Block-c0qfa0-1>.styles__Content-c0qfa0-5")).getText(),"125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02");
        logger.info("Проверка адреса прошла успешно");

        //развертывание окна браузера на полный экран(не киоск)
        driver.manage().window().maximize();
        logger.info("Окно открыто в максимальном размере");

        //проверка title страницы
        Assert.assertEquals(driver.getTitle(), "Контакты | OTUS");
        logger.info("Проверка title страницы прошла успешно");

    }

    @Test
    public void searchVerification() {
        //Открыть tele2
        driver.get(cfg.URL_TELE2());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        logger.info("Страница открыта");

        //ввести в поле "поиск номера" 97 и начать поиск
        driver.findElement(By.id("searchNumber")).sendKeys("97");
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".catalog-overlay.loader-overlay"))));
        logger.info("Запушен поиск");

        //дождаться появления номеров
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='bundles-column'][1]//*[@class='phone-number-block'][1]")).isDisplayed());
        logger.info("Проверка появления номера прошла успешно");


    }

    @Test
    public void courseProgram() {
        //Открыть otus.ru
        driver.get(cfg.URL());
        logger.info("Страница открыта");

        //переход на F.A.Q, нажмите на вопрос: "Где посмотреть программу интересующего курса?"
        driver.get("https://otus.ru/faq");
        logger.info("Переход в раздел F.A.Q");

        //нажатие на вопрос
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[text()='Где посмотреть программу интересующего курса?']")).click();
        logger.info("Успешное нажатие на вопрос: 'Где посмотреть программу интересующего курса?'");

        //проверка текста
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Где посмотреть программу интересующего курса?']/..//*[@class='faq-question__answer js-faq-answer']"))));
        Assert.assertEquals(driver.findElement(By.xpath("//*[text()='Где посмотреть программу интересующего курса?']/..//*[@class='faq-question__answer js-faq-answer']")).getText(), "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”");
        logger.info("Проверка текста прошла успешно");

    }

    @Test
    public void subscriptionVerification() {
        //Открыть otus.ru
        driver.get(cfg.URL());
        logger.info("Страница открыта");

        //Заполнение тестовый почтовый ящик в поле "Подпишитесь на наши новости"
        driver.findElement(By.cssSelector(".input.footer2__subscribe-input")).sendKeys("test@test.test");
        logger.info("Нажатие на кнопку 'Подписаться' прошло успешно");

        //Нажматие на кнопку "Подписаться"
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".footer2__subscribe-button"))));
        driver.findElement(By.cssSelector(".footer2__subscribe-button")).submit();
        logger.info("Заполнен тестовый почтовый ящик в поле 'Подпишитесь на наши новости'");

        //Проверка сообщения: "Вы успешно подписались"
        Assert.assertEquals(driver.findElement(By.cssSelector(".subscribe-modal__success")).getText(), "Вы успешно подписались");
        logger.info("Появилось сообщение: Вы успешно подписались");


    }

}
