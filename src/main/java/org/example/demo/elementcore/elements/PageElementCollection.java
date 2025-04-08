package org.example.demo.elementcore.elements;

import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public interface PageElementCollection<T> extends Collection<T> {
    List<T> getElements();

    T get(int index);

    PageElementCollection<T> waitUntilPresent();

    PageElementCollection<T> waitUntilVisible();

    PageElementCollection<T> waitUntilAnyPresent();

    void addList(List<WebElement> list);
}
