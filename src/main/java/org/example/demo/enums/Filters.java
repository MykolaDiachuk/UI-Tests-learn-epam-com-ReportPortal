package org.example.demo.enums;

public enum Filters {
    LANGUAGE("Language"),
    ESTIMATION_EFFORTS("Estimated efforts"),
    TARGET_SKILL("Target skill"),
    TARGET_LEVEL("Target level"),
    VENDOR("Vendor");

    private final String label;

    Filters(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
