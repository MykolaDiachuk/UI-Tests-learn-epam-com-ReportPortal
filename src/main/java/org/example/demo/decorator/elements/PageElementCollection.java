package org.example.demo.decorator.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public interface PageElementCollection<T extends WebElement> extends Collection<T> {
    List<T> getElements();

    T get(int index);

    PageElementCollection<T> waitUntilPresent();

    By getLocator();

    void addList(List<T> list);
}
