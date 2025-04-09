package org.example.demo.utils.reporting;

import io.qameta.allure.Allure;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureAttachmentService {
    private static final Logger logger = LoggerFactory.getLogger(AllureAttachmentService.class);
    private static final String LOG_FILE_PATH = "target/report/logs/test.log";

    public static void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            logger.error("Failed to attach screenshot: {}", e.getMessage());
        }
    }

    public static void attachLogs() {
        try {
            byte[] logBytes = Files.readAllBytes(Paths.get(LOG_FILE_PATH));
            Allure.addAttachment("Execution Logs", "text/plain", new ByteArrayInputStream(logBytes), ".log");
            logger.info("Logs attached to Allure report");
        } catch (IOException e) {
            logger.error("Failed to attach logs: {}", e.getMessage());
        }
    }
}

