package org.example.demo.decorator.elements;

import lombok.Getter;
import lombok.Setter;
import org.example.demo.utils.DriverManager;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;

import java.util.List;

import static org.example.demo.utils.Waiter.waitForElementToBeClickable;

@Setter
@Getter
public class PageElement implements WebElement {

    private final WebElement element;
    private final By locator;
    private boolean single = false;

    public PageElement(WebElement element, By locator) {
        this.element = element;
        this.locator = locator;
    }

    @Override
    public void click() {
        WebElement fresh = waitForElementToBeClickable(locator).getElement();
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", fresh);
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
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
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

    public boolean isExist(WebDriver driver) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
