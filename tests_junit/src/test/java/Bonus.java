import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;



public class Bonus {
    protected static WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void properties(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "sameProperties.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Привет мир!");
        String property1 = appProps.getProperty("prop1");
        assertEquals("value1", property1);
    }

    @Test
    public void propertiesXML(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String iconConfigPath = rootPath + "prop.xml";
        Properties iconProps = new Properties();
        try {
            iconProps.loadFromXML(new FileInputStream(iconConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("icon1.jpg", iconProps.getProperty("prop1"));
    }

    @Test
    public void softAssert(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "sameProperties.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(appProps.getProperty("prop1")).isEqualTo("value1");
        softAssertions.assertThat(appProps.getProperty("prop2")).isEqualTo("value2");
        softAssertions.assertThat(appProps.getProperty("prop3")).isEqualTo("value3");
        softAssertions.assertAll();
    }

    @Test
    public void partAssert(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "sameProperties.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(appProps.getProperty("prop3").contains("vaue"));
    }

    @Test
    public void cookie(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://otus.ru/");
        //driver.manage().addCookie(new Cookie("auth_token_expires","1715339163"));
        //driver.manage().addCookie(new Cookie("auth_token","-LdpHug9FCzV9lFEtCZlRw"));
        //driver.manage().addCookie(new Cookie("oid","31c496ae747c4c3c2018348af56769f5"));
        driver.manage().addCookie(new Cookie("sessionid","qlsgfv3g1x444cfcb2e9tg6i65fq4fqi"));

        driver.get("https://otus.ru/learning/");
    }
}
