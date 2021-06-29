package testCases;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;
import pages.AbstractPage;
import pages.LoginPage;
import pages.PersonalPage;
import utils.BaseHooks;


@RunWith(JUnitParamsRunner.class)
public class OtusPageObjectTest extends BaseHooks {

    Logger logger = LogManager.getLogger(AbstractPage.class);

    @Test
    @Parameters({
            "username, password, name, surname, name_latin, surname_latin, birthday, id_blog_name"
    })
    public void otusPageObjectTest(String username,String password, String name, String surname,
                                   String name_latin, String surname_latin, String birthday, String id_blog_name) {

        // Открываем сайт
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        logger.info("Page opens in first time");

        // Авторизуемся на сайте
        loginPage.auth(username, password);
        logger.info("Successful Authorization");

        // В разделе "О себе" заполняем все поля "Личные данные" и добавить не менее двух контактов
        // И нажимаем "Сохранить"
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.enterPD(name, surname, name_latin, surname_latin, birthday, id_blog_name);
        logger.info("Personal data are saved");

        // Открыть https://otus.ru в "чистом браузере"
        teardown();
        setup();
        logger.info("Page https://otus.ru is open in a new browser");

        // Авторизоваться на сайте
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.auth(username, password);
        logger.info("Successful Authorization");

        // Войти в личный кабинет
        personalPage = new PersonalPage(driver);
        personalPage.open();
        logger.info("Personal page opens");

        // Проверить, что в разделе "О себе" отображаются указанные ранее данные
        Assert.assertEquals(name, driver.findElement(personalPage.getName_locator()).getAttribute("value"));
        logger.info("Name is correctly");
        Assert.assertEquals(surname, driver.findElement(personalPage.getSurname_locator()).getAttribute("value"));
        logger.info("Surname is correctly");
        Assert.assertEquals(name_latin, driver.findElement(personalPage.getName_locator_latin()).getAttribute("value"));
        logger.info("Name Latin is correctly");
        Assert.assertEquals(surname_latin, driver.findElement(personalPage.getSurname_locator_latin()).getAttribute("value"));
        logger.info("Surname Latin is correctly");
        Assert.assertEquals(birthday, driver.findElement(personalPage.getBirthday_locator()).getAttribute("value"));
        logger.info("Birthday is correctly");
        Assert.assertEquals(id_blog_name, driver.findElement(personalPage.getId_blog_name_locator()).getAttribute("value"));
        logger.info("Id blog name is correctly");
    }

}
