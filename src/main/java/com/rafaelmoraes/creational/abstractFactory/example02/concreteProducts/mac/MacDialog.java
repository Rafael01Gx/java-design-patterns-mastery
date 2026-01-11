package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.mac;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Dialog;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class MacDialog implements Dialog {

    private final Theme theme;

    public MacDialog(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void render() {
        IO.println("  🍎 [macOS Dialog] Sheet style | Theme: " + theme.getName());
    }

    @Override
    public void show(String title, String message) {
        IO.println("  ┌───────────────────────────────────────┐");
        IO.println("  │ 🍎 " + title);
        IO.println("  ├───────────────────────────────────────┤");
        IO.println("  │ " + message);
        IO.println("  └───────────────────────────────────────┘");
    }

    @Override
    public void close() {
        IO.println("     → macOS dialog dismissed with slide animation");
    }

    @Override
    public String getStyle() {
        return "macOS sheet with vibrancy and blur";
    }
}