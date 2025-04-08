package org.example.demo.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class ReportCleaner {
    private static final String REPORT_PATH = "target/report/screenshots";

    public static void cleanReports() {
        try {
            File reportDir = new File(REPORT_PATH);
            if (reportDir.exists()) {
                FileUtils.deleteDirectory(reportDir);
                System.out.println("Report directory cleaned successfully.");
            }
        } catch (Exception e) {
            System.err.println("Failed to clean report directory: " + e.getMessage());
        }
    }
}
