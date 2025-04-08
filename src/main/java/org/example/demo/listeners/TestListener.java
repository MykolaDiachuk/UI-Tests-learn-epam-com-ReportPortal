package org.example.demo.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.apache.commons.io.FileUtils;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements TestLifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
    private static final String SCREENSHOT_PATH = "target/report/screenshots/";
    private static final String LOG_FILE_PATH = "target/report/logs/test.log";

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            saveScreenshotToFile(result.getName());
            attachScreenshot();
            attachLogs();
        }
    }

    private void saveScreenshotToFile(String testName) {
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

    private void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            logger.error("Failed to attach screenshot: {}", e.getMessage());
        }
    }

    private void attachLogs() {
        try {
            byte[] logBytes = Files.readAllBytes(Paths.get(LOG_FILE_PATH));
            Allure.addAttachment("Execution Logs", "text/plain", new ByteArrayInputStream(logBytes), ".log");
            logger.info("Logs attached to Allure report");
        } catch (IOException e) {
            logger.error("Failed to attach logs: {}", e.getMessage());
        }
    }
}
