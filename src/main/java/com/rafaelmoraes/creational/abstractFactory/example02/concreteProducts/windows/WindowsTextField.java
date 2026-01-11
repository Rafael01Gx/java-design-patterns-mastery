package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.windows;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.TextField;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class WindowsTextField implements TextField {

    private final String placeholder;
    private final Theme theme;
    private String value = "";

    public WindowsTextField(String placeholder, Theme theme) {
        this.placeholder = placeholder;
        this.theme = theme;
    }

    @Override
    public void render() {
        IO.println("  🪟 [Windows TextField] Placeholder: '" + placeholder +
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
        return "Modern Windows 11 style with subtle shadows";
    }

    @Override
    public void setPlaceholder(String placeholder) {
        // ...
    }
}
