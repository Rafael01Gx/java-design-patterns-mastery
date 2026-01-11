package com.rafaelmoraes.creational.abstractFactory.example02.concreteFactories;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractFactories.UIFactory;
import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.*;
import com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.windows.*;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Platform;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class WindowsUIFactory implements UIFactory {

    private final Theme theme;

    public WindowsUIFactory(Theme theme) {
        this.theme = theme;
    }

    @Override
    public Button createButton(String label) {
        return new WindowsButton(label, theme);
    }

    @Override
    public TextField createTextField(String placeholder) {
        return new WindowsTextField(placeholder, theme);
    }

    @Override
    public Checkbox createCheckbox(String label) {
        return new WindowsCheckbox(label, theme);
    }

    @Override
    public Dialog createDialog() {
        return new WindowsDialog(theme);
    }

    @Override
    public Menu createMenu() {
        return new WindowsMenu(theme);
    }

    @Override
    public Platform getPlatform() {
        return Platform.WINDOWS;
    }

    @Override
    public Theme getTheme() {
        return theme;
    }
}