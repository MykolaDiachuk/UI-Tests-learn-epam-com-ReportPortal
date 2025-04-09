package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.pages.CourseEntityPage;
import org.example.demo.tests.basetest.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.EstimatedEffort.UP_TO_1_HOUR;
import static org.example.demo.enums.Language.ENGLISH;
import static org.example.demo.enums.TargetLevel.NOT_DEFINED;

public class CatalogCoursePageTests extends BaseTest {
    @Test(groups = "regression", description = "Verify that right course page was found.")
    @Story("Go to course page")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatCoursePageWasFoundTest() {
        catalogMainPage.selectCheckbox(ENGLISH.getLabel());
        catalogMainPage.selectCheckbox(UP_TO_1_HOUR.getLabel());
        catalogMainPage.selectCheckbox(NOT_DEFINED.getLabel());

        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> {
                    assertThat(course.getLanguage()).isEqualTo("ENG");
                    assertThat(course.getEffort().toHours()).isBetween(0L, 1L);
                });

        String courseName = "Amazon Aurora Service Primer";

        catalogMainPage.getAllVisibleCourses()
                .stream()
                .filter(course -> course.getTitle().equals(courseName))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Course not found"));

        CourseEntityPage courseEntityPage = catalogMainPage.goToCourse(courseName);

        assertThat(courseEntityPage.getTitle()).isEqualTo(courseName);
    }
}
