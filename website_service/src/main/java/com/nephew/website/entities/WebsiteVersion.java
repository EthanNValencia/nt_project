package com.nephew.website.entities;

public enum WebsiteVersion {
    VERSION_1(1),
    VERSION_2(2),
    VERSION_3(3),
    VERSION_4(4),
    VERSION_5(5);

    private final int value;

    WebsiteVersion(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}