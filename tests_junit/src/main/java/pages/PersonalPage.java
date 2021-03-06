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

    private By save = By.cssSelector("[name='continue']");

    By nameLocator = By.cssSelector("#id_fname");
    By surnameLocator = By.cssSelector("#id_lname");
    By nameLocatorLatin = By.cssSelector("#id_fname_latin");
    By surnameLocatorLatin = By.cssSelector("#id_lname_latin");
    By idBlogNameLocator = By.cssSelector("#id_blog_name");
    By birthdayLocator = By.cssSelector(".input-icon > input:nth-child(1)");
    By addContactButton = By.cssSelector(".lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add");
    By viberTitle = By.cssSelector("[title='Viber']");
    By textField = By.cssSelector("[name='contact-0-value']");

    String locator = ".ic-blog-default-avatar";

    WebElement icon = driver.findElement(By.cssSelector(locator));

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public PersonalPage open() {
        driver.get(URL_PERSONAL);

        return this;
    }

    public PersonalPage enterPD(String name, String surname, String name_latin, String surname_latin, String birthday, String id_blog_name) {
        // В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        Actions actions = new Actions(driver);
        actions.moveToElement(icon).build().perform();
        driver.get(URL_PERSONAL);

        driver.findElement(nameLocatorLatin).clear();
        driver.findElement(surnameLocatorLatin).clear();
        driver.findElement(nameLocator).clear();
        driver.findElement(surnameLocator).clear();
        driver.findElement(idBlogNameLocator).clear();
        driver.findElement(birthdayLocator).clear();

        driver.findElement(nameLocator).sendKeys(name);
        driver.findElement(surnameLocator).sendKeys(surname);
        driver.findElement(nameLocatorLatin).sendKeys(name_latin);
        driver.findElement(surnameLocatorLatin).sendKeys(surname_latin);
        driver.findElement(idBlogNameLocator).sendKeys(id_blog_name);
        driver.findElement(birthdayLocator).sendKeys(birthday);

        driver.findElement(addContactButton).click();
        driver.findElement(viberTitle).submit();
        driver.findElement(textField).sendKeys("Viber");

        // Нажать сохранить
        driver.findElement(save).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(URL_SKILLS));
        return this;
    }

    public By getNameLocator() {
        return nameLocator;
    }

    public By getSurnameLocator() {
        return surnameLocator;
    }

    public By getBirthdayLocator() {
        return birthdayLocator;
    }

    public By getNameLocatorLatin() {
        return nameLocatorLatin;
    }

    public By getSurnameLocatorLatin() {
        return surnameLocatorLatin;
    }

    public By getIdBlogNameLocator() {
        return idBlogNameLocator;
    }

    public By getViberTitle() {
        return viberTitle;
    }
}
