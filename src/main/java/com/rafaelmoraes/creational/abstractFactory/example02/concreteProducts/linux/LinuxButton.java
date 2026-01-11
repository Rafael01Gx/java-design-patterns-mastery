package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.linux;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Button;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class LinuxButton implements Button {

    private final String label;
    private final Theme theme;
    private boolean enabled = true;

    public LinuxButton(String label, Theme theme) {
        this.label = label;
        this.theme = theme;
    }

    @Override
    public void render() {
        String style = enabled ? "enabled" : "disabled";
        IO.println("  🐧 [Linux Button] " + label +
                " | Style: GTK+ | Theme: " + theme.getName() +
                " | State: " + style);
    }

    @Override
    public void onClick(String action) {
        if (enabled) {
            IO.println("     → Linux button activated: " + action);
        }
    }

    @Override
    public String getStyle() {
        return "GTK+ Adwaita theme";
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}