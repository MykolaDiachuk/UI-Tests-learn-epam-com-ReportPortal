package org.example.demo.pages;

import org.example.demo.dtos.CourseDTO;
import org.example.demo.pages.modals.LanguageSelectionModal;
import org.example.demo.pages.modals.SkillSelectorModal;
import org.example.demo.parsers.CourseCardParser;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.example.demo.utils.Waiter.*;


public class CatalogMainPage extends BasePage {
    private static final By LANGUAGE_MODAL = By.xpath("//button[contains(@class, 'uui-button-box') and .//div[text()='SHOW ALL 33 LANGUAGES']]");
    private static final By SKILL_SELECTION_INPUT = By.cssSelector("input.uui-input[placeholder='Search skill']");
    private static final By COURSE_CARDS = By.xpath("//div[contains(@class, 'CatalogCard_catalogCard__+qmum')]");
    private static final By FILTER_CHIP = By.cssSelector(".ExploreFilterChips_chip__OAl2H");
    private static final By FILTER_CHIP_COUNT = By.cssSelector(".ExploreFilterChips_chipCount__j5YiR");
    private static final By FILTER_CHIP_TEXT = By.cssSelector(".ExploreFilterChips_chipText__FueZX");
    private static final By CLEAR_ALL_BUTTON = By.cssSelector(".ExploreFilterChips_chipClearAll__qA6k9");
    private static final By SORT_BY_BUTTON = By.xpath("//button[contains(@class,'DropdownSortingTarget_linkButton__yEhkX')]");

    private final Logger logger = LoggerFactory.getLogger(CatalogMainPage.class);

    public CatalogMainPage() {
        super();
    }

    public CatalogMainPage selectCheckbox(String checkboxText) {
        logger.info("Select checkbox: {}", checkboxText);
        scrollAndClick(getCheckboxLocator(checkboxText));
        return this;
    }

    public LanguageSelectionModal openLanguageSelectionModal() {
        logger.info("Open language selection modal");
        scrollAndClick(LANGUAGE_MODAL);
        return new LanguageSelectionModal();
    }

    public SkillSelectorModal openSkillSelectionModal() {
        logger.info("Open skill selection modal");
        scrollAndClick(SKILL_SELECTION_INPUT);
        return new SkillSelectorModal();
    }

    public List<CourseDTO> getAllVisibleCourses() {
        logger.info("Collecting all visible courses info");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> courseCards = DriverManager.getDriver().findElements(COURSE_CARDS);
        return courseCards.stream()
                .map(CourseCardParser::parse)
                .toList();
    }

    public int getFilterCount(String filterName) {
        logger.info("Get filter count for: {}", filterName);
        WebElement filterChip = waitForElementToBePresent(getFilterChipLocator(filterName));
        String countText = filterChip.findElement(FILTER_CHIP_COUNT).getText();
        return Integer.parseInt(countText);
    }

    public List<String> getFiltersText() {
        logger.info("Get filters text");
        List<WebElement> filterChips = waitForAllElementsToBePresent(FILTER_CHIP);
        return filterChips.stream()
                .map(filterChip -> filterChip.findElement(FILTER_CHIP_TEXT).getText())
                .toList();
    }

    public void clearAllFilters() {
        logger.info("Clear all filters");
        WebElement clearAllButton = waitForElementToBePresent(CLEAR_ALL_BUTTON);
        clickElementWithJS(clearAllButton);
    }

    public CourseEntityPage goToCourse(String courseName) {
        logger.info("Go to course: {}", courseName);
        WebElement courseLink = waitForElementToBePresent(getCourseLocator(courseName));
        clickElementWithJS(courseLink);
        return new CourseEntityPage();
    }

    public void selectDescentSortBy(String sortItem) {
        logger.info("Select descending sort by: {}", sortItem);
        scrollAndClick(SORT_BY_BUTTON);
        clickElementWithJS(waitForElementToBePresent(getSortDropdownItem(sortItem)));
    }
    public void selectAscentSortBy(String sortItem) {
        logger.info("Select ascending sort by: {}", sortItem);
        scrollAndClick(SORT_BY_BUTTON);
        clickElementWithJS(waitForElementToBePresent(getSortDropdownItem(sortItem)));
        clickElementWithJS(waitForElementToBePresent(getSortDropdownItem(sortItem)));
    }

    public boolean isCheckboxSelected(String label) {
        WebElement checkbox = waitForElementToBePresent(getCheckboxInputLocator(label));
        return checkbox.isSelected();
    }

    public boolean isSkillSelected(String skillName) {
        WebElement checkbox = waitForElementToBePresent(getSelectedSkillCheckboxLocator(skillName));
        return checkbox.isSelected();
    }

    public boolean isLanguageSelected(String language) {
        try {
            WebElement languageOption = DriverManager
                    .getDriver()
                    .findElement(By.xpath("//div[@role='option'][.//div[contains(text(), '"
                            + language + "')]]"));
            return "true".equals(languageOption.getDomAttribute("aria-checked"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private By getCheckboxLocator(String label) {
        return By.xpath("//label[.//div[contains(@class, 'uui-input-label') and text()='" + label + "']]");
    }

    private By getCheckboxInputLocator(String label) {
        return By.xpath("//div[@role='option'][.//div[text()='" + label + "']]//input[@type='checkbox']");
    }

    private By getSelectedSkillCheckboxLocator(String skillName) {
        return By.xpath("//div[@role='option'][@aria-checked='true'][.//div[@class='uui-input-label' and text()='" + skillName + "']]//input[@type='checkbox']");
    }

    private By getCourseLocator(String courseName) {
        return By.xpath("//div[contains(@class, 'OverflowedTypography_content__') and text()='" + courseName + "']");
    }

    private By getFilterChipLocator(String filterName) {
        return By.xpath("//div[contains(@class,'ExploreFilterChips_chip__OAl2H')]/div[text()='" + filterName + "']/parent::div");
    }

    private By getSortDropdownItem(String sortItem) {
        return By.xpath("//div[contains(@class,'DropdownBaseItem_md__IT1ES')]/div/div[text()='" + sortItem + "']");
    }
}
