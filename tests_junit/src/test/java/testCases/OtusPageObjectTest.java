package testCases;

import junitparams.JUnitParamsRunner;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;
import pages.LoginPage;
import pages.PersonalPage;


@RunWith(JUnitParamsRunner.class)
public class OtusPageObjectTest extends BaseHooks {

    Logger logger = LogManager.getLogger(OtusPageObjectTest.class);

    private static String username = System.getProperty("username");
    private static String password = System.getProperty("password");
    private static String name = System.getProperty("name");
    private static String surname = System.getProperty("surname");
    private static String nameLatin = System.getProperty("nameLatin");
    private static String surnameLatin = System.getProperty("nameLatin");
    private static String birthday = System.getProperty("birthday");
    private static String idBlogName = System.getProperty("idBlogName");

    @Test
    public void otusPageObjectTest() {

        // Открываем сайт
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        logger.info("Page was opened first time");

        // Авторизуемся на сайте
        loginPage.auth(username, password);
        logger.info("Authorization was successful");

        // В разделе "О себе" заполняем все поля "Личные данные" и добавить не менее двух контактов
        // И нажимаем "Сохранить"
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.enterPD(name, surname, nameLatin, surnameLatin, birthday, idBlogName);
        logger.info("Personal data were saved");

        // Открыть https://otus.ru в "чистом браузере"
        // teardown();
        // setup();
        cleanUp();
        logger.info("Page https://otus.ru opens in new browser");

        // Авторизоваться на сайте
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.auth(username, password);
        logger.info("Authorization was successful");

        // Войти в личный кабинет
        personalPage = new PersonalPage(driver);
        personalPage.open();
        logger.info("Personal page was opened");

        // Проверить, что в разделе "О себе" отображаются указанные ранее данные
        Assert.assertEquals(name, driver.findElement(personalPage.getNameLocator()).getAttribute("value"));
        logger.info("Name is correct");
        Assert.assertEquals(surname, driver.findElement(personalPage.getSurnameLocator()).getAttribute("value"));
        logger.info("Surname is correct");
        Assert.assertEquals(nameLatin, driver.findElement(personalPage.getNameLocatorLatin()).getAttribute("value"));
        logger.info("Name Latin is correct");
        Assert.assertEquals(surnameLatin, driver.findElement(personalPage.getSurnameLocatorLatin()).getAttribute("value"));
        logger.info("Surname Latin is correct");
        Assert.assertEquals(birthday, driver.findElement(personalPage.getBirthdayLocator()).getAttribute("value"));
        logger.info("Birthday is correct");
        Assert.assertEquals(idBlogName, driver.findElement(personalPage.getIdBlogNameLocator()).getAttribute("value"));
        logger.info("Id blog name is correct");
    }

}
