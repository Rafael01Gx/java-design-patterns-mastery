package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.mac;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Button;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class MacButton implements Button {
    private final String label;
    private final Theme theme;
    private boolean enabled = true;

    public MacButton(String label, Theme theme) {
        this.label = label;
        this.theme = theme;
    }

    @Override
    public void render() {
        String style = enabled ? "enabled" : "disabled";
        IO.println("  🍎 [macOS Button] " + label +
                " | Style: Aqua | Theme: " + theme.getName() +
                " | State: " + style);
    }

    @Override
    public void onClick(String action) {
        if (enabled) {
            IO.println("     → macOS button clicked with haptic feedback: " + action);
        }
    }

    @Override
    public String getStyle() {
        return "Aqua style with smooth gradients";
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
