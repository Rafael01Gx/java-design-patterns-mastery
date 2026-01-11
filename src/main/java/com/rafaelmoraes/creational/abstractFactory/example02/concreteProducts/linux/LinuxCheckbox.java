package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.linux;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Checkbox;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class LinuxCheckbox implements Checkbox {

    private final String label;
    private final Theme theme;
    private boolean checked = false;

    public LinuxCheckbox(String label, Theme theme) {
        this.label = label;
        this.theme = theme;
    }

    @Override
    public void render() {
        String icon = checked ? "[✓]" : "[ ]";
        IO.println("  🐧 [Linux Checkbox] " + icon + " " + label +
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
        return "GTK+ checkbox with border";
    }
}