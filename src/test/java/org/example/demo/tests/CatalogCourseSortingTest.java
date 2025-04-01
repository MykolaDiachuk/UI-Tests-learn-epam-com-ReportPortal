package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.basetest.BaseTest;
import org.example.demo.dtos.CourseDTO;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CatalogCourseSortingTest extends BaseTest {

    @Test(groups = "smoke", description = "Verify that descent sorting by course name works proper.")
    @Story("Sorting courses")
    @Severity(SeverityLevel.CRITICAL)
    public void descentSortByCourseNameTest() {
        catalogMainPage.selectDescentSortBy("Course Name");

        List<String> actualCourseTitles = catalogMainPage.getAllVisibleCourses().stream()
                .map(CourseDTO::getTitle)
                .toList();

        List<String> sortedCourseTitles = new ArrayList<>(actualCourseTitles);
        sortedCourseTitles.sort(String::compareToIgnoreCase);
        Collections.reverse(sortedCourseTitles);

        assertThat(actualCourseTitles)
                .as("Courses should be sorted by course name")
                .isEqualTo(sortedCourseTitles);
    }

    @Test(groups = "smoke", description = "Verify that ascent sorting by course name works proper.")
    @Story("Sorting courses")
    @Severity(SeverityLevel.CRITICAL)
    public void ascentSortByRelevanceTest() {
        catalogMainPage.selectAscentSortBy("Course Name");

        List<String> actualCourseTitles = catalogMainPage.getAllVisibleCourses().stream()
                .map(CourseDTO::getTitle)
                .toList();

        List<String> sortedCourseTitles = new ArrayList<>(actualCourseTitles);
        sortedCourseTitles.sort(String::compareToIgnoreCase);

        assertThat(actualCourseTitles)
                .as("Courses should be sorted by course name")
                .isEqualTo(sortedCourseTitles);
    }
}
