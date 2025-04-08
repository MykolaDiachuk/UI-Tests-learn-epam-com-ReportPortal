package org.example.demo.elementcore;

import org.openqa.selenium.SearchContext;

@FunctionalInterface
public interface DriverProvider {
    SearchContext get();
}
