package org.example.demo.pages;

import org.example.demo.elementcore.elements.PageElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseEntityPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CourseEntityPage.class);

    @FindBy(xpath = "//h1[contains(@class, 'Typography_typography__ViAqw Typography_h1__l4vDP primary')]")
    private PageElement title;

    public CourseEntityPage() {
        super();
    }

    public String getTitle() {
        String titleText = title.getText();
        logger.info("Get course title: {}", titleText);
        return titleText;
    }
}
