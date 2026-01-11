package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.windows;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Dialog;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class WindowsDialog implements Dialog {
    private final Theme theme;

    public WindowsDialog(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void render() {
        IO.println("  🪟 [Windows Dialog] Metro style | Theme: " + theme.getName());
    }

    @Override
    public void show(String title, String message) {
        IO.println("  ╔═══════════════════════════════════════╗");
        IO.println("  ║ 🪟 " + title);
        IO.println("  ╠═══════════════════════════════════════╣");
        IO.println("  ║ " + message);
        IO.println("  ╚═══════════════════════════════════════╝");
    }

    @Override
    public void close() {
        IO.println("     → Windows dialog closed with fade effect");
    }

    @Override
    public String getStyle() {
        return "Windows 11 rounded corners with acrylic background";
    }
}