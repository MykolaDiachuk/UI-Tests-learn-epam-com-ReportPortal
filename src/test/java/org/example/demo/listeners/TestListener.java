package org.example.demo.listeners;

import org.apache.commons.io.FileUtils;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
    private static final String SCREENSHOT_PATH = "target/report/screenshots/";

    @Override
    public void onTestFailure(ITestResult result) {
        cleanOldScreenshots();
        takeScreenshot(result.getName());
    }

    private void takeScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotFilePath = SCREENSHOT_PATH + testName + "_" + timestamp + ".png";

            FileUtils.copyFile(screenshot, new File(screenshotFilePath));
            logger.info("Screenshot saved: {}", screenshotFilePath);

        } catch (IOException | NullPointerException e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }


    private void cleanOldScreenshots() {
        File screenshotDir = new File(SCREENSHOT_PATH);
        if (!screenshotDir.exists()) return;

        File[] files = screenshotDir.listFiles();
        if (files == null) return;

        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        for (File file : files) {
            if (file.isFile() && file.getName().contains(todayDate)) {
                file.delete();
            }
        }
    }

}
