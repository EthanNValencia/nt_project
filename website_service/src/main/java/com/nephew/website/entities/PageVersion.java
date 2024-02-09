package com.nephew.website.entities;

public enum PageVersion {
    VERSION_1(1),
    VERSION_2(2),
    VERSION_3(3);

    private final int value;

    PageVersion(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
