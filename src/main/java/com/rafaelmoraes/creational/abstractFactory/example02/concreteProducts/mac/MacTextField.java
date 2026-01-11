package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.mac;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.TextField;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class MacTextField implements TextField {

    private final String placeholder;
    private final Theme theme;
    private String value = "";

    public MacTextField(String placeholder, Theme theme) {
        this.placeholder = placeholder;
        this.theme = theme;
    }

    @Override
    public void render() {
        IO.println("  🍎 [macOS TextField] Placeholder: '" + placeholder +
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
        return "macOS Big Sur style with focus ring";
    }

    @Override
    public void setPlaceholder(String placeholder) {
        // ...
    }
}
