package org.example.demo.pages;

import org.example.demo.elementcore.PageElementDecorator;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(new PageElementDecorator(DriverManager::getDriver), this);
    }
}
