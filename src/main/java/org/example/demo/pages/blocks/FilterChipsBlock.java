package org.example.demo.pages.blocks;

import org.example.demo.decorator.PageElementDecorator;
import org.example.demo.decorator.elements.PageElement;
import org.example.demo.decorator.elements.PageElementCollection;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.example.demo.utils.Waiter.waitForAllElementsToBePresent;

public class FilterChipsBlock {
    private static final By FILTER_CHIP_TEXT = By.cssSelector(".ExploreFilterChips_chipText__FueZX");
    private static final By FILTER_CHIP_COUNT = By.cssSelector(".ExploreFilterChips_chipCount__j5YiR");
    private final Logger logger = LoggerFactory.getLogger(FilterChipsBlock.class);
    @FindBy(css = ".ExploreFilterChips_chip__OAl2H")
    private PageElementCollection<PageElement> chips;

    @FindBy(css = ".ExploreFilterChips_chipClearAll__qA6k9")
    private PageElement clearAllFiltersButton;

    public FilterChipsBlock() {
        PageFactory.initElements(new PageElementDecorator(DriverManager::getDriver), this);
    }

    public int getFilterCount(String filterName) {
        logger.info("Get filter count for: {}", filterName);

        //TODO: Refactor this to use PageElementCollection

        List<PageElement> chipsNew = waitForAllElementsToBePresent(By.cssSelector(".ExploreFilterChips_chip__OAl2H"));
        PageElement targetChip = chipsNew.stream()

        //PageElement targetChip = chips.getElements().stream()
                .filter(chip -> chip.findElement(FILTER_CHIP_TEXT)
                        .getText().equals(filterName))
                .findFirst()
                .orElseThrow();

        String countText = targetChip.findElement(FILTER_CHIP_COUNT).getText();
        return Integer.parseInt(countText);
    }

    public List<String> getFiltersText() {
        logger.info("Get filters text");
        return chips.waitUntilPresent().stream()
                .map(chip -> chip.findElement(FILTER_CHIP_TEXT).getText())
                .toList();
    }

    public void clearAllFilters() {
        logger.info("Clear all filters");
        clearAllFiltersButton.click();
    }
}
