package org.example.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private DriverFactory() {
    }

    static WebDriver createDriver(String browser) {
        WebDriver driver;
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--force-device-scale-factor=0.8");
                if (headless) options.addArguments("--headless=new");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("layout.css.devPixelsPerPx", "0.8");
                if (headless) firefoxOptions.addArguments("--headless=new");
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
