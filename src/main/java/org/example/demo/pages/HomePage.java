package org.example.demo.pages;

import org.example.demo.decorator.elements.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.utils.Waiter.waitForElementToBePresent;

public class HomePage extends BasePage {
    private static final By COOKIE_ACCEPT_BUTTON = By.id("onetrust-accept-btn-handler");
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "a.AppMenuItem_appMenuItem__-8c3R[href='/catalog']")
    private PageElement catalogLink;

    public HomePage() {
        super();
    }

    public CatalogMainPage goToCatalog() {
        logger.info("Navigating to catalog page");
        clickElement(catalogLink);
        return new CatalogMainPage();
    }

    public void acceptCookies() {
        try {
            PageElement acceptButton = waitForElementToBePresent(COOKIE_ACCEPT_BUTTON);
            clickElement(acceptButton);
        } catch (NoSuchElementException e) {
            logger.warn("Cookie accept button not found, skipping");
        }
    }
}
