package org.example.demo.pages;

import org.example.demo.decorator.PageElementDecorator;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(new PageElementDecorator(DriverManager::getDriver), this);
    }
}
