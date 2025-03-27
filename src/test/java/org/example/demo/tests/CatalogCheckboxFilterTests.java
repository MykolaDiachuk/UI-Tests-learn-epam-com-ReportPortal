package org.example.demo.tests;

import io.qameta.allure.*;
import org.example.demo.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.EstimatedEffort.ONE_TO_FOUR_HOURS;
import static org.example.demo.enums.Language.ENGLISH;
import static org.example.demo.enums.TargetLevel.NOVICE;

@Epic("Catalog Page")
@Feature("Language Filter")
@Listeners({TestListener.class})
public class CatalogCheckboxFilterTests extends BaseTest{

    @Test(groups = "smoke", description = "Verify that filters can be selected successfully on the catalog page.")
    @Story("Filter by language, duration and level")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyThatFiltersCanBeSelectedSuccessfullyTest() {
        catalogMainPage.selectCheckbox(ENGLISH.getLabel());
        catalogMainPage.selectCheckbox(ONE_TO_FOUR_HOURS.getLabel());
        catalogMainPage.selectCheckbox(NOVICE.getLabel());

        assertThat(catalogMainPage.isCheckboxSelected(ENGLISH.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(ONE_TO_FOUR_HOURS.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(NOVICE.getLabel())).isTrue();
    }

    @Test(groups = "smoke", description = "Verify that the courses have been filtered by language")
    @Story("Courses have been filtered by language")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatCoursesFilteredByLanguageTest() {
        catalogMainPage.selectCheckbox(ENGLISH.getLabel());

        assertThat(catalogMainPage.isAllCoursesHaveLanguage("ENG")).isTrue();
    }
}
