package org.example.demo.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.demo.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.Language.*;
import static org.example.demo.enums.Language.HEBREW;

@Listeners({TestListener.class})
public class CatalogLanguageModalTests extends BaseTest {

    @Test(groups = "smoke", description = "Verify that selecting valid languages in modal.")
    @Story("Select languages")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatSelectingValidLanguagesTest() {
        catalogMainPage.openLanguageSelectionModal();
        catalogMainPage.selectLanguages(CZECH.getLabel(), SPANISH.getLabel(), FRENCH.getLabel());

        assertThat(catalogMainPage.isLanguageSelected(CZECH.getLabel())).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(SPANISH.getLabel())).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(FRENCH.getLabel())).isTrue();
    }

    @Test(groups = "smoke", description = "Verify that other languages was not selected.")
    @Story("Other languages not selected")
    @Severity(SeverityLevel.NORMAL)
    public void verifyThatOtherLanguagesWasNotSelectedTest() {
        catalogMainPage.openLanguageSelectionModal();
        catalogMainPage.selectLanguages(ENGLISH.getLabel(), SPANISH.getLabel(), FRENCH.getLabel());

        assertThat(catalogMainPage.isLanguageSelected(ARMENIAN.getLabel())).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(BELARUSIAN.getLabel())).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(HEBREW.getLabel())).isFalse();
    }
}
