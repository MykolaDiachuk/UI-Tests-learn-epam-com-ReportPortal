package org.example.demo.pages.modals;

import org.example.demo.decorator.elements.PageElement;
import org.example.demo.decorator.elements.PageElementCollection;
import org.example.demo.pages.BasePage;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LanguageSelectionModal extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(LanguageSelectionModal.class);

    @FindBy(xpath = "//div[@role='option']//div[@role='cell']")
    private PageElementCollection<PageElement> listOfLanguages;

    @FindBy(xpath = "//button[.//div[text()='Select']]")
    private PageElement selectButton;

    public LanguageSelectionModal() {
        super();
    }

    public LanguageSelectionModal selectLanguages(String... languages) {
        logger.info("Select languages: {}", Arrays.toString(languages));
        listOfLanguages.waitUntilPresent().getElements().stream()
                .filter(element -> isLanguagesContainsText(languages, element.scrollTo().getText()))
                .forEach(el -> el.scrollTo().clickWithJS());
        return this;
    }

    private boolean isLanguagesContainsText(String[] languages, String text) {
        return Arrays.stream(languages)
                .map(String::trim)
                .anyMatch(lang -> lang.equalsIgnoreCase(text.trim()));
    }

    public LanguageSelectionModal clickSelect() {
        logger.info("Click select button");
        selectButton.clickWithJS();
        return this;
    }
}
