package org.example.demo.utils.config;

public class EnvironmentResolver {
    public static String getEnv() {
        String env = System.getProperty("env");
        return (env == null || env.isEmpty()) ? "dev" : env;
    }
}
