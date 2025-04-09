package org.example.demo.utils.reporting;

import org.apache.commons.io.FileUtils;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotService {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotService.class);
    private static final String SCREENSHOT_PATH = "target/report/screenshots/";

    public static void saveScreenshotToFile(String testName) {
        try {
            File screenshotFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotFilePath = SCREENSHOT_PATH + testName + "_" + timestamp + ".png";

            FileUtils.copyFile(screenshotFile, new File(screenshotFilePath));
            logger.info("Screenshot saved: {}", screenshotFilePath);
        } catch (IOException | NullPointerException e) {
            logger.error("Failed to save screenshot: {}", e.getMessage());
        }
    }
}
