package org.example.demo.utils.selenium;

import org.example.demo.elementcore.elements.PageElement;
import org.example.demo.elementcore.factory.WrapperFactory;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiter {

    private static final Duration TIMEOUT = Duration.ofSeconds(15);
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(500);

    private Waiter() {
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
    }

    public static PageElement waitForElementToBeVisible(By locator) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return WrapperFactory.createInstance(PageElement.class, element, locator);
    }

    public static PageElement waitForElementToBeVisible(PageElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element.getElement()));
        return element;
    }

    public static List<WebElement> waitForAllElementsToBeVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static PageElement waitForElementToBeClickable(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        return WrapperFactory.createInstance(PageElement.class, element, locator);
    }

    public static PageElement waitForElementToBeClickable(PageElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element.getElement()));
        return element;
    }

    public static PageElement waitForElementToBePresent(By locator) {
        WebElement element = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        return WrapperFactory.createInstance(PageElement.class, element, locator);
    }

    public static List<WebElement> waitForAllElementsToBePresent(By locator) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitForAllElementsToBeInvisible(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static void waitForAllElementsToBeInvisible(PageElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element.getElement()));
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_INTERVAL);
    }
}
