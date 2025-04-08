package org.example.demo.pages.modals;

import org.example.demo.elementcore.elements.PageElement;
import org.example.demo.pages.BasePage;
import org.example.demo.utils.selenium.FormatElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.utils.selenium.Waiter.waitForAllElementsToBeInvisible;

public class SkillSelectorModal extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(SkillSelectorModal.class);

    private final FormatElement skillLocator = new FormatElement("//div[contains(text(), '%s')]");

    @FindBy(css = "input[placeholder='Type text for quick search']")
    private PageElement skillSearchInput;

    @FindBy(xpath = "//button[.//div[text()='Select']]")
    private PageElement selectButton;

    @FindBy(css = "div.uui-modal-window")
    private PageElement modalWindow;

    public SkillSelectorModal() {
        super();
    }

    public SkillSelectorModal addSkill(String skillName) {
        logger.info("Added skill {} in modal", skillName);
        searchSkill(skillName);
        selectSkillFromResults(skillName);
        clearSearchInput();
        return this;
    }

    private void searchSkill(String skillName) {
        logger.info("Searching for skill '{}' in modal", skillName);
        skillSearchInput.scrollTo().type(skillName, Keys.ENTER);
    }

    private void selectSkillFromResults(String skillName) {
        logger.info("Selecting skill '{}' from results in modal", skillName);
        skillLocator.format(skillName).clickWithJS();
    }

    private void clearSearchInput() {
        logger.info("Clear search input in modal");
        skillSearchInput
                .type(Keys.CONTROL + "a")
                .type(Keys.BACK_SPACE);
    }

    public void selectSkills() {
        logger.info("Selecting skills from modal");
        selectButton.click();
        waitForAllElementsToBeInvisible(modalWindow);
    }
}