package org.example.demo.parsers;

import org.example.demo.dtos.CourseDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class CourseCardParser {
    private static final Logger logger = LoggerFactory.getLogger(CourseCardParser.class);

    private static final By TITLE_LOCATOR = By.xpath(".//h3//div[contains(@class, 'OverflowedTypography_content__')]");
    private static final By LANGUAGE_LOCATOR = By.xpath(".//*[@data-testid='label-language']//div[contains(@class, 'OverflowedTypography_content__')]");
    private static final By EFFORT_LOCATOR = By.xpath(".//*[@data-testid='test-estimated-efforts']//div[contains(@class, 'OverflowedTypography_content__')]");
    private static final By SKILLS_LOCATOR = By.xpath(".//*[@data-testid='test-learning-item-skills']//div[contains(@class, 'Typography_ellipses__')]");

    public static CourseDTO parse(WebElement card) {
        String title = "";
        String language = "";
        Duration effort = Duration.ZERO;
        List<String> skills = new ArrayList<>();

        try {
            title =  card.findElement(TITLE_LOCATOR).getText().trim();
        } catch (Exception e) {
            logger.warn("Title not found in course card");
        }

        try {
            language = card.findElement(LANGUAGE_LOCATOR).getText().trim();
        } catch (Exception e) {
            logger.warn("Language not found for course: {}", title);
        }

        try {
            effort = extractEffort(card.findElement(EFFORT_LOCATOR).getText().trim());
        } catch (Exception e) {
            logger.warn("Effort not found for course: {}", title);
        }

        try {
            List<WebElement> skillElements = card.findElements(SKILLS_LOCATOR);
            for (WebElement skill : skillElements) {
                skills.add(skill.getText().trim());
            }
        } catch (Exception e) {
            logger.warn("Skills not found for course: {}", title);
        }

        return CourseDTO.builder()
                .title(title)
                .language(language)
                .effort(effort)
                .skills(skills)
                .build();
    }

    private static Duration extractEffort(String effortText) {
        try {
            int hours = 0;
            int minutes = 0;
            String[] parts = effortText.split(" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].toLowerCase().contains("hour") || parts[i].toLowerCase().contains("hr")) {
                    hours = Integer.parseInt(parts[i - 1]);
                } else if (parts[i].toLowerCase().contains("min")) {
                    minutes = Integer.parseInt(parts[i - 1]);
                }
            }
            return Duration.ofHours(hours).plusMinutes(minutes);
        } catch (Exception e) {
            logger.warn("Failed to extract effort from text: {}", effortText);
            return Duration.ZERO;
        }
    }
}
