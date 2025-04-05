package org.example.demo.decorator.elements;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import static org.example.demo.utils.Waiter.waitForAllElementsToBePresent;

public class PageElementCollectionImpl<T extends WebElement> implements PageElementCollection<T> {

    @Getter
    private final By locator;
    private final List<T> elements;
    private final Class<T> type;

    public PageElementCollectionImpl(By locator, List<T> elements, Class<T> type) {
        this.locator = locator;
        this.elements = elements;
        this.type = type;
    }

    @Override
    public PageElementCollection<T> waitUntilPresent() {
        addList((List<T>) waitForAllElementsToBePresent(locator));
        return this;
    }

    public void addList(List<T> list) {
        elements.clear();
        elements.addAll(list);
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
