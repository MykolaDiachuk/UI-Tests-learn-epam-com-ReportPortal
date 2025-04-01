package org.example.demo.basetest;

import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.HomePage;
import org.example.demo.utils.ConfigReader;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;

public class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected CatalogMainPage catalogMainPage;

    @BeforeMethod(alwaysRun = true)
    protected void setup() {
        logger.info("Initialize browser");
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getProperty("base_url"));
    }

    @BeforeMethod(dependsOnMethods = "setup",alwaysRun = true)
    protected void prepareTest() {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        catalogMainPage = homePage.goToCatalog();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        logger.info("Quit driver");
        DriverManager.quitDriver();
    }
}
