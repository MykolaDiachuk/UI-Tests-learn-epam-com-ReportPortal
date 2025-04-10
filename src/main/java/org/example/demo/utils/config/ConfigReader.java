package org.example.demo.utils.config;

import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    static {
        String env = EnvironmentResolver.getEnv();
        properties = PropertiesLoader.load(env);
    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
