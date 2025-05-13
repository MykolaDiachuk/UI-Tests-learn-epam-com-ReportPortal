package org.example.demo.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;

import static org.example.demo.utils.reporting.AllureAttachmentService.attachLogsToAllure;
import static org.example.demo.utils.reporting.AllureAttachmentService.attachScreenshotToAllure;
import static org.example.demo.utils.reporting.ReportPortalAttachmentService.attachLogsToRP;
import static org.example.demo.utils.reporting.ReportPortalAttachmentService.attachScreenshotToRP;
import static org.example.demo.utils.reporting.ScreenshotService.saveScreenshotToFile;

public class TestListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            saveScreenshotToFile(result.getName());

            attachScreenshotToRP(result.getFullName());
            attachLogsToRP();

            attachScreenshotToAllure();
            attachLogsToAllure();
        }
    }
}
