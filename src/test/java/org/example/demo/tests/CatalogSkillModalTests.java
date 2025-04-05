package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.basetest.BaseTest;
import org.example.demo.dtos.CourseDTO;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.Skill.*;

public class CatalogSkillModalTests extends BaseTest {

    @Test(groups = "regression", description = "Verify that skills can be selected and added for search.")
    @Story("Filter by skills")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatSkillsCanBeSelectedAndSearchTest() {
        catalogMainPage.openSkillSelectionModal()
                .addSkill(AT_JAVA.getLabel())
                .addSkill(JAVA_CORE.getLabel())
                .addSkill(AI_IN_TEST_AUTOMATION.getLabel())
                .selectSkills();

        assertThat(catalogMainPage.isSkillSelected(AT_JAVA.getLabel())).isTrue();
        assertThat(catalogMainPage.isSkillSelected(JAVA_CORE.getLabel())).isTrue();
        assertThat(catalogMainPage.isSkillSelected(AI_IN_TEST_AUTOMATION.getLabel())).isTrue();

        List<CourseDTO> courses = catalogMainPage.getAllVisibleCourses();
        assertThat(courses)
                .as("Courses should be present on the page")
                .isNotEmpty();

        courses.forEach(course -> {
            assertThat(course.getSkills()).contains(AT_JAVA.getLabel(), JAVA_CORE.getLabel(), AI_IN_TEST_AUTOMATION.getLabel());
        });
    }

    @Test(groups = "regression", description = "Verify that skills not be selected.")
    @Story("Filter by skills")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatOtherSkillNotSelectedTest() {
        catalogMainPage.openSkillSelectionModal()
                .addSkill(CUCUMBER.getLabel())
                .addSkill(JAVA_CORE.getLabel())
                .addSkill(AT_PYTHON.getLabel())
                .selectSkills();

        List<CourseDTO> courses = catalogMainPage.getAllVisibleCourses();
        assertThat(courses)
                .as("Courses should be present on the page")
                .isNotEmpty();

        courses.forEach(course -> {
            assertThat(course.getSkills())
                    .doesNotContain(AT_JAVA.getLabel(), AWS_APP_RUNNER.getLabel(), AI_IN_TEST_AUTOMATION.getLabel());
            assertThat(course.getSkills())
                    .contains(CUCUMBER.getLabel(), JAVA_CORE.getLabel(), AT_PYTHON.getLabel());

        });
    }
}


