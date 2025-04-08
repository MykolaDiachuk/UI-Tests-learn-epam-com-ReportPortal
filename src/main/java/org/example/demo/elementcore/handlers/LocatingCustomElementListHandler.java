package org.example.demo.elementcore.handlers;

import org.example.demo.elementcore.elements.PageElement;
import org.example.demo.elementcore.elements.PageElementCollection;
import org.example.demo.elementcore.elements.PageElementCollectionImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Field field;

    public LocatingCustomElementListHandler(ElementLocator locator, Field field) {
        this.locator = locator;
        this.field = field;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        By by = (new Annotations(field)).buildBy();

        PageElementCollection<PageElement> customs = new PageElementCollectionImpl<>(by, PageElement.class);
        customs.addList(locator.findElements());
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw e.getCause();
        }
    }
}