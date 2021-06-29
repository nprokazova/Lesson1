package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {
    private static final String URL = "https://otus.ru";
    private By button_auth = By.cssSelector("button.header2__auth");
    private By login_auth = By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)");
    private By password_auth = By.cssSelector(".js-psw-input");
    private By button_submit = By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        if (driver != null) {
            driver.get(URL);
        }
        return this;
    }

    public void auth(String login, String password)
    {
        driver.findElement(button_auth).click();
        driver.findElement(login_auth).sendKeys(login);
        driver.findElement(password_auth).sendKeys(password);
        driver.findElement(button_submit).submit();
    }

}