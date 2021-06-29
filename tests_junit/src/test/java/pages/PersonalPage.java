package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalPage extends AbstractPage {

    private static final String URL_PERSONAL = "https://otus.ru/lk/biography/personal/";
    private static final String URL_SKILLS = "https://otus.ru/lk/biography/skills/";

    private By save = By.xpath("//*[contains(text(), 'Сохранить и продолжить')]");

    By name_locator = By.cssSelector("#id_fname");
    By surname_locator = By.cssSelector("#id_lname");
    By name_locator_latin = By.cssSelector("#id_fname_latin");
    By surname_locator_latin = By.cssSelector("#id_lname_latin");
    By id_blog_name_locator = By.cssSelector("#id_blog_name");
    By birthday_locator = By.cssSelector(".input-icon > input:nth-child(1)");

    String locator = ".ic-blog-default-avatar";

    WebElement icon = driver.findElement(By.cssSelector(locator));

    public PersonalPage (WebDriver driver) {
        super(driver);
    }

    public PersonalPage open() {
        driver.get(URL_PERSONAL);

        return this;
    }

    public PersonalPage enterPD(String name, String surname, String name_latin, String surname_latin, String birthday, String id_blog_name)
    {
        // В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        Actions actions = new Actions(driver);
        actions.moveToElement(icon).build().perform();
        driver.get(URL_PERSONAL);

        driver.findElement(name_locator_latin).clear();
        driver.findElement(surname_locator_latin).clear();
        driver.findElement(name_locator).clear();
        driver.findElement(surname_locator).clear();
        driver.findElement(id_blog_name_locator).clear();
        driver.findElement(birthday_locator).clear();

        driver.findElement(name_locator).sendKeys(name);
        driver.findElement(surname_locator).sendKeys(surname);
        driver.findElement(name_locator_latin).sendKeys(name_latin);
        driver.findElement(surname_locator_latin).sendKeys(surname_latin);
        driver.findElement(id_blog_name_locator).sendKeys(id_blog_name);
        driver.findElement(birthday_locator).sendKeys(birthday);

        // Нажать сохранить
        driver.findElement(save).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(URL_SKILLS));
        return this;
    }

    public By getName_locator() {
        return name_locator;
    }

    public By getSurname_locator() {
        return surname_locator;
    }

    public By getBirthday_locator() {
        return birthday_locator;
    }

    public By getName_locator_latin() {
        return name_locator_latin;
    }

    public By getSurname_locator_latin() {
        return surname_locator_latin;
    }

    public By getId_blog_name_locator() {
        return id_blog_name_locator;
    }
}
