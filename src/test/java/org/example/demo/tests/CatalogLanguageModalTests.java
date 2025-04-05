package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.basetest.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.Language.*;

public class CatalogLanguageModalTests extends BaseTest {

    @Test(groups = "regression", description = "Verify that selecting valid languages in modal.")
    @Story("Select languages")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatSelectingValidLanguagesTest() {
        catalogMainPage.openLanguageSelectionModal()
                .selectLanguages(SPANISH.getLabel(), CZECH.getLabel(), FRENCH.getLabel())
                .clickSelect();

        assertThat(catalogMainPage.isLanguageSelected(CZECH.getLabel())).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(SPANISH.getLabel())).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(FRENCH.getLabel())).isTrue();

        catalogMainPage.getAllVisibleCourses()
                .forEach(course -> {
                    assertThat(course.getLanguage()).isIn("CZE", "SPA", "FRA");
                });
    }

    @Test(groups = "regression", description = "Verify that other languages was not selected.")
    @Story("Select languages")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatOtherLanguagesWasNotSelectedTest() {
        catalogMainPage.openLanguageSelectionModal()
                .selectLanguages(CZECH.getLabel(), SPANISH.getLabel(), FRENCH.getLabel())
                .clickSelect();

        assertThat(catalogMainPage.isLanguageSelected(ARMENIAN.getLabel())).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(BELARUSIAN.getLabel())).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(HEBREW.getLabel())).isFalse();

        catalogMainPage.getAllVisibleCourses()
                .forEach(course -> {
                    assertThat(course.getLanguage()).isNotIn("ARM", "BEL", "HEB");
                });
    }
}
