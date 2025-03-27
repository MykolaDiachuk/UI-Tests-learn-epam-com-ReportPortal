package org.example.demo.tests;

import io.qameta.allure.*;
import org.example.demo.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.Skill.*;
import static org.example.demo.enums.Skill.AI_IN_TEST_AUTOMATION;

@Epic("Catalog Page")
@Feature("Skill Filter")
@Listeners({TestListener.class})
public class CatalogSkillFilterTests extends BaseTest{

    @Test(groups = "regression", description = "Verify that skills can be selected and added for search.")
    @Story("Filter by skills")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatSkillsCanBeSelectedAndSearchTest() {
        catalogMainPage.openSkillSelection()
                .addSkill(AT_JAVA.getLabel())
                .addSkill(JAVA_CORE.getLabel())
                .addSkill(AI_IN_TEST_AUTOMATION.getLabel())
                .selectSkills();

        assertThat(catalogMainPage.isSkillSelected(AT_JAVA.getLabel())).isTrue();
        assertThat(catalogMainPage.isSkillSelected(JAVA_CORE.getLabel())).isTrue();
        assertThat(catalogMainPage.isSkillSelected(AI_IN_TEST_AUTOMATION.getLabel())).isTrue();
    }
}
