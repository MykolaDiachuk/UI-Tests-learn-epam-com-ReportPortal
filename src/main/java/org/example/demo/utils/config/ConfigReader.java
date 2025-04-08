package org.example.demo.utils.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {

            String env = System.getProperty("env");

            if (env == null || env.isEmpty()) {
                properties.load(fileInputStream);
                env = properties.getProperty("env");
            }

            String envConfigPath = String.format("src/main/resources/config.%s.properties", env);

            try (FileInputStream envConfig = new FileInputStream(envConfigPath)) {
                properties.load(envConfig);
            }

        } catch (IOException e) {
            logger.error("Failed to open file: ", e);
        }
    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
