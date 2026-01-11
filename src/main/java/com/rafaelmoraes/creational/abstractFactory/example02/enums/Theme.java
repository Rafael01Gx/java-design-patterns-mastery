package com.rafaelmoraes.creational.abstractFactory.example02.enums;

public enum Theme {
    LIGHT("Light Theme", "☀️", "#FFFFFF", "#000000"),
    DARK("Dark Theme", "🌙", "#1E1E1E", "#FFFFFF"),
    HIGH_CONTRAST("High Contrast", "🎨", "#000000", "#FFFF00");

    private final String name;
    private final String icon;
    private final String background;
    private final String foreground;

    Theme(String name, String icon, String background, String foreground) {
        this.name = name;
        this.icon = icon;
        this.background = background;
        this.foreground = foreground;
    }

    public String getName() { return name; }
    public String getIcon() { return icon; }
    public String getBackground() { return background; }
    public String getForeground() { return foreground; }
}
