package org.example.demo.decorator.locator;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

public class PageElementLocatorImpl implements ElementLocator {
    private final SearchContext searchContext;
    private final boolean shouldCache;
    private final By by;
    private WebElement cachedElement;
    private List<WebElement> cachedElementList;

    public PageElementLocatorImpl(SearchContext searchContext, Field field) {
        this(searchContext, new Annotations(field));
    }

    public PageElementLocatorImpl(SearchContext searchContext, AbstractAnnotations annotations) {
        this.searchContext = searchContext;
        this.shouldCache = annotations.isLookupCached();
        this.by = annotations.buildBy();
    }

    public WebElement findElement() {
        if (this.cachedElement != null && this.shouldCache()) {
            return this.cachedElement;
        } else {
            WebElement element = this.searchContext.findElement(this.by);
            if (this.shouldCache()) {
                this.cachedElement = element;
            }

            return element;
        }
    }

    public List<WebElement> findElements() {
        if (this.cachedElementList != null && this.shouldCache()) {
            return this.cachedElementList;
        } else {
            List<WebElement> elements = this.searchContext.findElements(this.by);
            if (this.shouldCache()) {
                this.cachedElementList = elements;
            }

            return elements;
        }
    }

    protected boolean shouldCache() {
        return this.shouldCache;
    }

    public String toString() {
        String var10000 = this.getClass().getSimpleName();
        return var10000 + " '" + this.by + "'";
    }
}
