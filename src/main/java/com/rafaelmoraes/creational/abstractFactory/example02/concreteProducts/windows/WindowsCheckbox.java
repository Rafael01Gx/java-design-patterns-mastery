package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.windows;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Checkbox;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class WindowsCheckbox implements Checkbox {
    private final String label;
    private final Theme theme;
    private boolean checked = false;

    public WindowsCheckbox(String label, Theme theme) {
        this.label = label;
        this.theme = theme;
    }

    @Override
    public void render() {
        String icon = checked ? "☑" : "☐";
        IO.println("  🪟 [Windows Checkbox] " + icon + " " + label +
                " | Theme: " + theme.getName());
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public String getStyle() {
        return "Fluent Design with accent color";
    }
}