package org.example.demo.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.utils.reporting.AllureAttachmentService.attachLogs;
import static org.example.demo.utils.reporting.AllureAttachmentService.attachScreenshot;
import static org.example.demo.utils.reporting.ScreenshotService.saveScreenshotToFile;

public class TestListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            saveScreenshotToFile(result.getName());
            attachScreenshot();
            attachLogs();
        }
    }
}
