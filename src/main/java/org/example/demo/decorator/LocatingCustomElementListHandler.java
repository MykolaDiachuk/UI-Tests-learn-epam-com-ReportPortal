package org.example.demo.decorator;

import org.example.demo.decorator.elements.PageElement;
import org.example.demo.decorator.elements.PageElementCollection;
import org.example.demo.decorator.elements.PageElementCollectionImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Field field;

    public LocatingCustomElementListHandler(ElementLocator locator, Field field) {
        this.locator = locator;
        //this.clazz = clazz;
        this.field = field;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        By by = (new Annotations(field)).buildBy();

        List<WebElement> rawElements = locator.findElements();
        List<PageElement> pageElements = rawElements.stream()
                .map(el -> new PageElement(el,by))
                .toList();

        PageElementCollection<PageElement> customs = new PageElementCollectionImpl<>(by,pageElements);
       // customs.addList(locator.findElements());
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw e.getCause();
        }
    }
}