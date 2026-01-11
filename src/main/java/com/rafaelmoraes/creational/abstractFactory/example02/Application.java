package com.rafaelmoraes.creational.abstractFactory.example02;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractFactories.UIFactory;
import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.*;

public class Application {
    private final UIFactory factory;
    private Button submitButton;
    private TextField nameField;
    private Checkbox termsCheckbox;
    private Dialog alertDialog;
    private Menu mainMenu;

    public Application(UIFactory factory) {
        this.factory = factory;
        createUI();
    }

    private void createUI() {
        // Criando componentes usando a factory
        submitButton = factory.createButton("Submit");
        nameField = factory.createTextField("Enter your name");
        termsCheckbox = factory.createCheckbox("I accept the terms");
        alertDialog = factory.createDialog();
        mainMenu = factory.createMenu();

        // Configurando o menu
        mainMenu.addItem("File", "file");
        mainMenu.addItem("Edit", "edit");
        mainMenu.addItem("View", "view");
        mainMenu.addItem("Help", "help");
    }

    public void render() {
        IO.println("\n" + "=".repeat(60));
        IO.println(factory.getPlatform().getIcon() + " RENDERING APPLICATION - " +
                factory.getPlatform().getName());
        IO.println("Theme: " + factory.getTheme().getIcon() + " " +
                factory.getTheme().getName());
        IO.println("=".repeat(60));

        mainMenu.render();
        IO.println();
        nameField.render();
        termsCheckbox.render();
        submitButton.render();
        IO.println();
        alertDialog.render();
    }

    public void interact() {
        IO.println("\n" + "─".repeat(60));
        IO.println("INTERACTING WITH UI");
        IO.println("─".repeat(60));

        nameField.setValue("Rafael Moraes");
        termsCheckbox.setChecked(true);
        submitButton.onClick("submit-form");

        IO.println();
        alertDialog.show("Success", "Form submitted successfully!");
        alertDialog.close();
    }
}
