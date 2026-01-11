package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.linux;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.TextField;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class LinuxTextField implements TextField {

    private final String placeholder;
    private final Theme theme;
    private String value = "";

    public LinuxTextField(String placeholder, Theme theme) {
        this.placeholder = placeholder;
        this.theme = theme;
    }

    @Override
    public void render() {
        IO.println("  🐧 [Linux TextField] Placeholder: '" + placeholder +
                "' | Value: '" + value + "' | Theme: " + theme.getName());
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getStyle() {
        return "GTK+ with flat design";
    }

    @Override
    public void setPlaceholder(String placeholder) {
        // Not implemented
    }
}
