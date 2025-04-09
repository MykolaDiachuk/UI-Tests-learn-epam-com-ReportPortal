package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.tests.basetest.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.EstimatedEffort.ONE_TO_FOUR_HOURS;
import static org.example.demo.enums.Language.ENGLISH;
import static org.example.demo.enums.TargetLevel.NOVICE;

public class CatalogCheckboxFilterTests extends BaseTest {

    @Test(groups = "smoke", description = "Verify that filters can be selected successfully on the catalog page.")
    @Story("Courses have been filtered")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyThatFiltersCanBeSelectedSuccessfullyTest() {
        catalogMainPage.selectCheckbox(ENGLISH.getLabel())
                .selectCheckbox(ONE_TO_FOUR_HOURS.getLabel())
                .selectCheckbox(NOVICE.getLabel());

        assertThat(catalogMainPage.isCheckboxSelected(ENGLISH.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(ONE_TO_FOUR_HOURS.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(NOVICE.getLabel())).isTrue();

        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> {
                    assertThat(course.getLanguage()).isEqualTo("ENG");
                    assertThat(course.getEffort().toHours()).isBetween(1L, 4L);
                });
    }

    @Test(groups = "smoke", description = "Verify that the courses have been filtered by language")
    @Story("Courses have been filtered")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatCoursesFilteredByLanguageTest() {
        catalogMainPage.selectCheckbox(ENGLISH.getLabel());

        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course ->
                        assertThat(course.getLanguage()).isEqualTo("ENG"));

    }
}
