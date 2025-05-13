package org.example.demo.utils.reporting;
import com.epam.reportportal.service.ReportPortal;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class ReportPortalAttachmentService {
    private static final String LOG_FILE_PATH = "target/report/logs/test.log";

    public static void attachScreenshotToRP(String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            File tempFile = File.createTempFile("screenshot_", ".png");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(screenshot);
            }

            ReportPortal.emitLog(name, "ERROR", new Date(), tempFile);

        } catch (IOException e) {
            ReportPortal.emitLog("Failed to attach screenshot: " + e.getMessage(), "ERROR", new Date());
        }
    }

    public static void attachLogsToRP() {
        try {
            File logFile = new File(LOG_FILE_PATH);
            if (logFile.exists()) {
                ReportPortal.emitLog("Execution logs", "ERROR", new Date(), logFile);
            } else {
                ReportPortal.emitLog("Log file not found: " + LOG_FILE_PATH, "WARN", new Date());
            }
        } catch (Exception e) {
            ReportPortal.emitLog("Failed to attach logs: " + e.getMessage(), "ERROR", new Date());
        }
    }
}
