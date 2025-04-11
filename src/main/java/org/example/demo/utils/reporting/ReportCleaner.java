package org.example.demo.utils.reporting;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ReportCleaner {
    protected static final Logger logger = LoggerFactory.getLogger(ReportCleaner.class);
    private static final String REPORT_PATH = "target/report/screenshots";

    public static void cleanReports() {
        try {
            File reportDir = new File(REPORT_PATH);
            if (reportDir.exists()) {
                FileUtils.deleteDirectory(reportDir);
                logger.info ("Report directory cleaned successfully.");
            }
        } catch (Exception e) {
            logger.error("Failed to clean report directory: {}", e.getMessage());
        }
    }
}
