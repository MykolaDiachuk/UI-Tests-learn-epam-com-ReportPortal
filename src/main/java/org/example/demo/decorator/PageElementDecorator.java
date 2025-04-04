package org.example.demo.decorator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import org.example.demo.decorator.elements.PageElement;
import org.example.demo.decorator.elements.PageElementCollection;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;


public class PageElementDecorator extends DefaultFieldDecorator {

    public PageElementDecorator(DriverProvider provider) {
        super(new PageElementLocatorFactoryImpl(provider.get()));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<PageElement> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            if (PageElementCollection.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass, field);
            }
            return createElement(loader, locator, decoratableClass, field);
        }
        return super.decorate(loader, field);
    }

    @SuppressWarnings("unchecked")
    private Class<PageElement> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        if (PageElementCollection.class.isAssignableFrom(clazz)) {
            if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clazz = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }
        if (PageElement.class.isAssignableFrom(clazz)) {
            return (Class<PageElement>) clazz;
        } else {
            return null;
        }
    }

    protected PageElement createElement(ClassLoader loader, ElementLocator locator, Class<PageElement> clazz, Field field) {
        WebElement proxy = proxyForLocator(loader, locator);
        PageElement element = WrapperFactory.createInstance(clazz, proxy, field);
        element.setSingle(true);
        return element;
    }

    @SuppressWarnings("unchecked")
    protected <T extends WebElement> PageElementCollection<T> createList(ClassLoader loader, ElementLocator locator, Class<PageElement> clazz, Field field) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, field);
        return (PageElementCollection<T>) Proxy.newProxyInstance(loader, new Class[]{PageElementCollection.class}, handler);
    }
}