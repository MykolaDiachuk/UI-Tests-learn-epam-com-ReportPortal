package org.example.demo.pages.modals;

import org.example.demo.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.example.demo.utils.Waiter.*;

public class LanguageSelectionModal extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(LanguageSelectionModal.class);
    private static final By LIST_OF_LANGUAGES = By.xpath("//div[@role='option']//div[@role='cell']");
    private static final By SELECT_BUTTON = By.xpath("//button[.//div[text()='Select']]");

    public LanguageSelectionModal() {
        super();
    }

    public LanguageSelectionModal selectLanguages(String... languages) {
        logger.info("Select languages: {}", Arrays.toString(languages));
        getListOfLanguages().stream()
                .filter(element -> isLanguagesContainsText(languages, scrollToElement(element).getText()))
                .forEach(this::scrollAndClick);
        return this;
    }

    private List<WebElement> getListOfLanguages() {
        logger.info("Get list of languages");
        return waitForAllElementsToBePresent(LIST_OF_LANGUAGES);
    }

    private boolean isLanguagesContainsText(String[] languages, String text) {
        return Arrays.stream(languages)
                .map(String::trim)
                .anyMatch(lang -> lang.equalsIgnoreCase(text.trim()));
    }

    public LanguageSelectionModal clickSelect() {
        logger.info("Click select button");
        clickElementWithJS(waitForElementToBeClickable(SELECT_BUTTON));
        return this;
    }
}
