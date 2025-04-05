package org.example.demo.pages;

import org.example.demo.decorator.PageElementDecorator;
import org.example.demo.decorator.elements.PageElement;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import static org.example.demo.utils.Waiter.*;

public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(new PageElementDecorator(DriverManager::getDriver), this);
    }

    protected void scrollAndClick(PageElement element) {
        scrollToElement(element);
        clickElementWithJS(waitForElementToBeClickable(element));
    }

    protected PageElement scrollToElement(PageElement element) {
        waitForElementToBePresent(element.getLocator());
        if (element.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();", element.getElement());
        }
        return element;
    }

    public void clickElementWithJS(PageElement element) {
        waitForElementToBePresent(element.getLocator());
        highlightElement(element);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element.getElement());
    }

    public void clickElement(PageElement element) {
        waitForElementToBeVisible(element);
        highlightElement(element);
        waitForElementToBeClickable(element).click();
    }

    private void highlightElement(PageElement element) {
        waitForElementToBePresent(element.getLocator());
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element.getElement());
    }
    //TODO WAITS
}
