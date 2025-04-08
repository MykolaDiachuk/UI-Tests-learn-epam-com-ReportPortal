package org.example.demo.utils.selenium;

import org.example.demo.elementcore.elements.PageElement;
import org.openqa.selenium.By;

import static org.example.demo.utils.selenium.Waiter.waitForElementToBePresent;

public class FormatElement {
    private final String xpathTemplate;

    public FormatElement(String xpathTemplate) {
        this.xpathTemplate = xpathTemplate;
    }

    public PageElement format(String value) {
        By locator = asBy(value);
        return waitForElementToBePresent(locator);
    }

    public By asBy(String value) {
        return By.xpath(String.format(xpathTemplate, value));
    }
}
