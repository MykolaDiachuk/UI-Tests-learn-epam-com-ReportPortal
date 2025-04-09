package org.example.demo.dtos.parsers;

import org.example.demo.dtos.CourseDTO;
import org.openqa.selenium.WebElement;

public class CourseCardParser {
    public static CourseDTO parse(WebElement card) {
        return CourseDTO.builder()
                .title(CourseCardExtractor.extractTitle(card))
                .language(CourseCardExtractor.extractLanguage(card))
                .effort(CourseCardExtractor.extractEffort(card))
                .skills(CourseCardExtractor.extractSkills(card))
                .build();
    }
}
