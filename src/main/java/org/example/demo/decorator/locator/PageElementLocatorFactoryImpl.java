package org.example.demo.decorator.locator;

import org.example.demo.decorator.DriverProvider;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class PageElementLocatorFactoryImpl implements ElementLocatorFactory {
    private final DriverProvider provider;

    public PageElementLocatorFactoryImpl(DriverProvider provider) {
        this.provider = provider;
    }

    public ElementLocator createLocator(Field field) {
        return new PageElementLocatorImpl(this.provider, field);
    }
}
