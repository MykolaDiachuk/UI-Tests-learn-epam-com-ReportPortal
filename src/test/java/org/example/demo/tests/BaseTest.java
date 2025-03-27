package org.example.demo.tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.example.demo.listeners.TestListener;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.HomePage;
import org.example.demo.utils.ConfigReader;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners({AllureTestNg.class, TestListener.class})
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

    @AfterSuite
    @Attachment(value = "Execution Logs", type = "text/plain")
    public static byte[] attachLogs() {
        try {
            return Files.readAllBytes(Paths.get("target/report/logs/test.log"));
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to attach logs".getBytes();
        }
    }
}
