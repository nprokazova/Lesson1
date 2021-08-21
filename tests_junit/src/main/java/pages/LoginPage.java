package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {
    private static final String URL = "https://otus.ru";
    private By buttonAuth = By.cssSelector("button.header2__auth");
    private By loginAuth = By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)");
    private By passwordAuth = By.cssSelector(".js-psw-input");
    private By buttonSubmit = By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        if (driver != null) {
            driver.get(URL);
        }
        return this;
    }

    public void auth(String login, String password) {
        driver.findElement(buttonAuth).click();
        driver.findElement(loginAuth).sendKeys(login);
        driver.findElement(passwordAuth).sendKeys(password);
        driver.findElement(buttonSubmit).submit();
    }

}