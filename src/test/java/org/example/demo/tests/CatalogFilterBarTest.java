package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.pages.blocks.FilterChipsBlock;
import org.example.demo.tests.basetest.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.EstimatedEffort.ONE_TO_FOUR_HOURS;
import static org.example.demo.enums.Filters.*;
import static org.example.demo.enums.Language.*;
import static org.example.demo.enums.TargetLevel.NOVICE;

public class CatalogFilterBarTest extends BaseTest {

    @Test(groups = "smoke", description = "Verify that filter bar have chips that was selected.")
    @Story("Filter bar")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyThatFilterBarHaveProperChipsTest() {
        catalogMainPage.selectCheckbox(ONE_TO_FOUR_HOURS.getLabel())
                .selectCheckbox(NOVICE.getLabel())
                .openLanguageSelectionModal()
                .selectLanguages(
                        ENGLISH.getLabel(),
                        ARMENIAN.getLabel())
                .clickSelect();

        assertThat(catalogMainPage.getFilterChipsBlock().getFiltersText())
                .allSatisfy(filter -> {
                    assertThat(filter).isIn(
                            ESTIMATION_EFFORTS.getLabel(),
                            TARGET_LEVEL.getLabel(),
                            LANGUAGE.getLabel());
                });

    }

    @Test(groups = "smoke", description = "Verify that count of filters on filter bar is working well.")
    @Story("Filter bar")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyThatCountOnFilterBarWorksWellTest() {
        catalogMainPage.selectCheckbox(ONE_TO_FOUR_HOURS.getLabel())
                .selectCheckbox(NOVICE.getLabel())
                .openLanguageSelectionModal()
                .selectLanguages(
                        ARMENIAN.getLabel(),
                        FRENCH.getLabel(),
                        HEBREW.getLabel(),
                        SPANISH.getLabel(),
                        CZECH.getLabel(),
                        BELARUSIAN.getLabel())
                .clickSelect();

        FilterChipsBlock filterChipsBlock = catalogMainPage.getFilterChipsBlock();

        assertThat(filterChipsBlock.getFilterCount(LANGUAGE.getLabel())).isEqualTo(6);
        assertThat(filterChipsBlock.getFilterCount(TARGET_LEVEL.getLabel())).isEqualTo(1);
        assertThat(filterChipsBlock.getFilterCount(ESTIMATION_EFFORTS.getLabel())).isEqualTo(1);
    }

    @Test(groups = "smoke", description = "Verify that filter bar have chips that was selected.")
    @Story("Filter bar")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyThatClearAllButtonInFilterBarWorksWellTest() {
        catalogMainPage.selectCheckbox(ONE_TO_FOUR_HOURS.getLabel())
                .selectCheckbox(NOVICE.getLabel())
                .openLanguageSelectionModal()
                .selectLanguages(FRENCH.getLabel())
                .clickSelect();

        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> {
                    assertThat(course.getLanguage()).isEqualTo("FRA");
                    assertThat(course.getEffort().toHours()).isBetween(1L, 4L);
                });

        catalogMainPage.getFilterChipsBlock().clearAllFilters();

        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> {
                    assertThat(course.getEffort().toMinutes()).isBetween(0L, 12000L);
                });
    }
}
