package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.basetest.BaseTest;
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
                .selectLanguages(ENGLISH.getLabel(), ARMENIAN.getLabel())
                .clickSelect();

        catalogMainPage.getFiltersText()
                .forEach(filter -> {
                    assertThat(filter).isIn(ESTIMATION_EFFORTS.getLabel(), TARGET_LEVEL.getLabel(), LANGUAGE.getLabel());
                });

    }

    @Test(groups = "smoke", description = "Verify that count of filters on filter bar is working well.")
    @Story("Filter bar")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyThatCountOnFilterBarWorksWellTest() {
        catalogMainPage.selectCheckbox(ONE_TO_FOUR_HOURS.getLabel())
                .selectCheckbox(NOVICE.getLabel())
                .openLanguageSelectionModal()
                .selectLanguages(ENGLISH.getLabel(), ARMENIAN.getLabel(),
                        FRENCH.getLabel(), HEBREW.getLabel(), SPANISH.getLabel(),
                        CZECH.getLabel(), BELARUSIAN.getLabel())
                .clickSelect();

        assertThat(catalogMainPage.getFilterCount(LANGUAGE.getLabel())).isEqualTo(7);
        assertThat(catalogMainPage.getFilterCount(TARGET_LEVEL.getLabel())).isEqualTo(1);
        assertThat(catalogMainPage.getFilterCount(ESTIMATION_EFFORTS.getLabel())).isEqualTo(1);
    }

    @Test(groups = "smoke", description = "Verify that filter bar have chips that was selected.")
    @Story("Filter bar")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyThatClearAllButtonInFilterBarWorksWellTest() {
        catalogMainPage.selectCheckbox(ONE_TO_FOUR_HOURS.getLabel())
                .selectCheckbox(NOVICE.getLabel())
                .openLanguageSelectionModal()
                .selectLanguages(ENGLISH.getLabel())
                .clickSelect();

        catalogMainPage.getAllVisibleCourses()
                .forEach(course -> {
                    assertThat(course.getLanguage()).isEqualTo("ENG");
                    long effortHours = course.getEffort().toHours();
                    assertThat(effortHours).isBetween(1L, 4L);
                });

        catalogMainPage.clearAllFilters();

        catalogMainPage.getAllVisibleCourses()
                .forEach(course -> {
                    long effortHours = course.getEffort().toMinutes();
                    assertThat(effortHours).isBetween(0L, 12000L);
                });
    }
}
