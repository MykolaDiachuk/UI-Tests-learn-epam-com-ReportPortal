package org.example.demo.tests;

import io.qameta.allure.*;
import org.example.demo.basetest.BaseTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.Skill.*;
import static org.example.demo.enums.Skill.AI_IN_TEST_AUTOMATION;

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

        catalogMainPage.getAllVisibleCourses()
                .forEach(course -> {
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

        catalogMainPage.getAllVisibleCourses()
                .forEach(course -> {
                    assertThat(course.getSkills())
                            .doesNotContain(AT_JAVA.getLabel(), AWS_APP_RUNNER.getLabel(), AI_IN_TEST_AUTOMATION.getLabel());
                });
    }
}


