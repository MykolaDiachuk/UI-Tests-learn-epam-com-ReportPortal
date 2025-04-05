package org.example.demo.utils;

import org.example.demo.decorator.elements.PageElement;
import org.example.demo.decorator.elements.PageElementCollectionImpl;
import org.example.demo.decorator.factory.WrapperFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<PageElement> waitForAllElementsToBePresent(By locator) {
        List<WebElement> elements = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return elements.stream()
                .map(el -> WrapperFactory.createInstance(PageElement.class, el, locator))
                .collect(Collectors.toList());
    }

    public static void waitForAllElementsToBeInvisible(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_INTERVAL);
    }

    public static PageElementCollectionImpl<PageElement> waitAsCollection(By locator) {
        List<PageElement> elements = waitForAllElementsToBePresent(locator);
        return new PageElementCollectionImpl<>(locator, elements, PageElement.class);
    }
}
