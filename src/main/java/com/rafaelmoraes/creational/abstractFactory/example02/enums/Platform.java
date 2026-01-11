package com.rafaelmoraes.creational.abstractFactory.example02.enums;

public enum Platform {
    WINDOWS("Windows", "🪟"),
    MACOS("macOS", "🍎"),
    LINUX("Linux", "🐧"),
    WEB("Web", "🌐");

    private final String name;
    private final String icon;

    Platform(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() { return name; }
    public String getIcon() { return icon; }
}
