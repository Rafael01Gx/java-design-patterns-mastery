package com.rafaelmoraes.creational.abstractFactory.example02.abstractFactories;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.*;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Platform;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

public interface UIFactory {

    Button createButton(String label);
    TextField createTextField(String placeholder);
    Checkbox createCheckbox(String label);
    Dialog createDialog();
    Menu createMenu();

    // Métodos auxiliares
    Platform getPlatform();
    Theme getTheme();
}
