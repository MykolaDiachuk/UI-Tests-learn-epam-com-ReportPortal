package org.example.demo.decorator.elements;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import org.openqa.selenium.WebElement;

public interface PageElementCollection<T extends WebElement> extends Collection<T> {
    List<T> getElements();
    T get(int index);
}
