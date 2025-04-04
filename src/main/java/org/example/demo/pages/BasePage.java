package org.example.demo.pages;

import org.example.demo.decorator.PageElementDecorator;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.example.demo.utils.Waiter.*;

public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(new PageElementDecorator(DriverManager::getDriver), this);
    }

    protected void scrollAndClick(By locator) {
        WebElement element = waitForElementToBePresent(locator);
        scrollAndClick(element);
    }

    protected void scrollAndClick(WebElement element) {
        scrollToElement(element);
        clickElementWithJS(waitForElementToBeClickable(element));
    }

    protected WebElement scrollToElement(WebElement element) {
        if (element.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();", element);
        }
        return element;
    }

    public void clickElementWithJS(WebElement element) {
        highlightElement(element);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
    }
    public void clickElement(WebElement element) {
        waitForElementToBeVisible(element);
        highlightElement(element);
        waitForElementToBeClickable(element).click();
    }

    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);

    }
}
