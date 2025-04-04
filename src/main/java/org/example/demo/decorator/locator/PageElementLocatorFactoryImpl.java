package org.example.demo.decorator.locator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class PageElementLocatorFactoryImpl implements ElementLocatorFactory {
    private final SearchContext searchContext;

    public PageElementLocatorFactoryImpl(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ElementLocator createLocator(Field field) {
        return new PageElementLocatorImpl(this.searchContext, field);
    }

}
