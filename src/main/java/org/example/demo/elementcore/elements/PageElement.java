package org.example.demo.elementcore.elements;

import lombok.Getter;
import lombok.Setter;
import org.example.demo.utils.driver.DriverManager;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;

import java.util.List;

import static org.example.demo.utils.selenium.Waiter.*;

@Setter
@Getter
public class PageElement implements WebElement {

    private WebElement element;
    private By locator;

    public PageElement(WebElement element, By locator) {
        this.element = element;
        this.locator = locator;
    }

    public PageElement type(CharSequence... keysToSend) {
        waitUntilPresent();
        element.sendKeys(keysToSend);
        return this;
    }

    public PageElement clickWithJS() {
        highlight();
        waitUntilClickable();
        return doJS("arguments[0].click();");
    }

    public PageElement scrollTo() {
        waitUntilPresent();
        return doJS("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();");
    }

    public PageElement highlight() {
        waitUntilPresent();
        return doJS("arguments[0].style.border='3px solid red'");
    }

    private PageElement doJS(String script) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script, element);
        return this;
    }

    public PageElement waitUntilClickable() {
        element = waitForElementToBeClickable(locator).getElement();
        return this;
    }

    public PageElement waitUntilPresent() {
        element = waitForElementToBePresent(locator).getElement();
        return this;
    }

    public PageElement waitUntilVisible() {
        element = waitForElementToBeVisible(locator).getElement();
        return this;
    }

    public PageElement refresh() {
        this.element = DriverManager.getDriver().findElement(locator);
        return this;
    }

    public boolean isExist() {
        try {
            DriverManager.getDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //WebElement methods
    @Override
    public void click() {
        highlight();
        waitUntilClickable();
        element.click();
    }
    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }
    @Override
    public void clear() {
        element.clear();
    }
    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public @Nullable String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return waitUntilPresent().getElement().isSelected();
    }
    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return this.waitUntilVisible().getElement().getText();
    }
    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }
    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return element.getScreenshotAs(target);
    }
}
