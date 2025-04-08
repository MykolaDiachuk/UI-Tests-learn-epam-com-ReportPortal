package org.example.demo.decorator.elements;

import org.example.demo.decorator.factory.WrapperFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.example.demo.utils.Waiter.*;

public class PageElementCollectionImpl<T> implements PageElementCollection<T> {

    private final By locator;
    private List<T> elements;
    private Class<T> clazz;

    public PageElementCollectionImpl(By locator, Class<T> clazz) {
        this.locator = locator;
        this.clazz = clazz;
        this.elements = new ArrayList<>();
    }

    @Override
    public PageElementCollection<T> waitUntilPresent() {
        addList(waitForAllElementsToBePresent(locator));
        return this;
    }

    @Override
    public PageElementCollection<T> waitUntilVisible() {
        addList(waitForAllElementsToBeVisible(locator));
        return this;
    }
    @Override
    public PageElementCollection<T> waitUntilAnyPresent() {
        getFluentWait().until(driver -> {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty();
        });
        return waitUntilPresent();
    }

    public void addList(List<WebElement> list) {
        elements.clear();
        for (int i = 0; i < list.size(); i++) {
            By singleElementLocator = By.xpath('(' + locator.toString().substring(10) + ')' + '[' + (i + 1) + ']');
            elements.add(WrapperFactory.createInstance(clazz, list.get(i), singleElementLocator));
        }
    }

    @Override
    public List<T> getElements() {
        return elements;
    }

    @Override
    public T get(int index) {
        return elements.get(index);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return elements.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return elements.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return elements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return elements.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return elements.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    @Override
    public void clear() {
        elements.clear();
    }
}
