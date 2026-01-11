package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.windows;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Button;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class WindowsButton implements Button {
    private final String label;
    private final Theme theme;
    private boolean enabled = true;

    public WindowsButton(String label, Theme theme) {
        this.label = label;
        this.theme = theme;
    }

    @Override
    public void render() {
        String style = enabled ? "enabled" : "disabled";
        IO.println("  🪟 [Windows Button] " + label +
                " | Style: Aero Glass | Theme: " + theme.getName() +
                " | State: " + style);
    }

    @Override
    public void onClick(String action) {
        if (enabled) {
            IO.println("     → Windows button clicked: " + action);
        }
    }

    @Override
    public String getStyle() {
        return "Aero Glass with rounded corners";
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
