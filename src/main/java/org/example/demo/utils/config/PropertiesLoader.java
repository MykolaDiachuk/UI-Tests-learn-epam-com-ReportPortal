package org.example.demo.utils.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties load(String env) {
        Properties props = new Properties();
        String path = String.format("src/main/resources/config.%s.properties", env);

        try (FileInputStream in = new FileInputStream(path)) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from " + path, e);
        }

        return props;
    }

}
