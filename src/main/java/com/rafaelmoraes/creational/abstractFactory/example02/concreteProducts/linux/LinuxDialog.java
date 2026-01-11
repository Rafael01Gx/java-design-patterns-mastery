package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.linux;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Dialog;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class LinuxDialog implements Dialog {

    private final Theme theme;

    public LinuxDialog(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void render() {
        IO.println("  🐧 [Linux Dialog] GTK+ Dialog | Theme: " + theme.getName());
    }

    @Override
    public void show(String title, String message) {
        IO.println("  +---------------------------------------+");
        IO.println("  | 🐧 " + title);
        IO.println("  +---------------------------------------+");
        IO.println("  | " + message);
        IO.println("  +---------------------------------------+");
    }

    @Override
    public void close() {
        IO.println("     → Linux dialog closed");
    }

    @Override
    public String getStyle() {
        return "GTK+ dialog with header bar";
    }
}