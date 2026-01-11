package com.rafaelmoraes.creational.abstractFactory.example02.concreteFactories;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractFactories.UIFactory;
import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.*;
import com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.linux.*;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Platform;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public class LinuxUIFactory implements UIFactory {

    private final Theme theme;

    public LinuxUIFactory(Theme theme) {
        this.theme = theme;
    }

    @Override
    public Button createButton(String label) {
        return new LinuxButton(label, theme);
    }

    @Override
    public TextField createTextField(String placeholder) {
        return new LinuxTextField(placeholder, theme);
    }

    @Override
    public Checkbox createCheckbox(String label) {
        return new LinuxCheckbox(label, theme);
    }

    @Override
    public Dialog createDialog() {
        return new LinuxDialog(theme);
    }

    @Override
    public Menu createMenu() {
        return new LinuxMenu(theme);
    }

    @Override
    public Platform getPlatform() {
        return Platform.LINUX;
    }

    @Override
    public Theme getTheme() {
        return theme;
    }
}