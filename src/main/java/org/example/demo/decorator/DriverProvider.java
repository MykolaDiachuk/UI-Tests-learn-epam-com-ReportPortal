package org.example.demo.decorator;

import org.openqa.selenium.SearchContext;

@FunctionalInterface
public interface DriverProvider {
    SearchContext get();
}
