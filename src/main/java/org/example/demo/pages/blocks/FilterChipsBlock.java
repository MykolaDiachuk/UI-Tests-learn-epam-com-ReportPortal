package org.example.demo.pages.blocks;

import org.example.demo.decorator.PageElementDecorator;
import org.example.demo.decorator.elements.PageElement;
import org.example.demo.decorator.elements.PageElementCollection;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FilterChipsBlock {
    private final Logger logger = LoggerFactory.getLogger(FilterChipsBlock.class);

    @FindBy(css = ".ExploreFilterChips_chip__OAl2H")
    private PageElementCollection<PageElement> chips;

    @FindBy(css = ".ExploreFilterChips_chipClearAll__qA6k9")
    private PageElement clearAllFiltersButton;

    @FindBy(css = ".ExploreFilterChips_chipText__FueZX")
    private PageElement filterChipText;

    @FindBy(css = ".ExploreFilterChips_chipCount__j5YiR")
    private PageElement filterChipCount;

    public FilterChipsBlock() {
        PageFactory.initElements(new PageElementDecorator(DriverManager::getDriver), this);
    }

    public int getFilterCount(String filterName) {
        logger.info("Get filter count for: {}", filterName);
        PageElement targetChip = chips.waitUntilPresent().getElements().stream()
                .filter(chip -> chip.findElement(filterChipText.getLocator())
                        .getText().equals(filterName))
                .findFirst()
                .orElseThrow();

        String countText = targetChip.findElement(filterChipCount.getLocator()).getText();
        return Integer.parseInt(countText);
    }

    public List<String> getFiltersText() {
        logger.info("Get filters text");
        return chips.waitUntilPresent().stream()
                .map(chip -> chip.findElement(filterChipText.getLocator()).getText())
                .toList();
    }

    public void clearAllFilters() {
        logger.info("Clear all filters");
        clearAllFiltersButton.click();
    }
}
