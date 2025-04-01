package org.example.demo.enums;

public enum Skill {
    AT_JAVA("AT/Java"),
    JAVA_CORE("Java Core"),
    AI_IN_TEST_AUTOMATION("Gen AI in Test Automation"),
    AT_PYTHON("AT/Python"),
    AWS_APP_RUNNER("AWS App Runner"),
    CUCUMBER("Cucumber");



    private final String label;

    Skill(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
