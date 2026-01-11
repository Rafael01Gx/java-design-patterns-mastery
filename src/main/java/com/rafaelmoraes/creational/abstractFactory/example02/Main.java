package com.rafaelmoraes.creational.abstractFactory.example02;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractFactories.UIFactory;
import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Button;
import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Dialog;
import com.rafaelmoraes.creational.abstractFactory.example02.concreteFactories.LinuxUIFactory;
import com.rafaelmoraes.creational.abstractFactory.example02.concreteFactories.MacUIFactory;
import com.rafaelmoraes.creational.abstractFactory.example02.concreteFactories.WindowsUIFactory;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

import java.util.List;

public class Main {
    static void main() throws InterruptedException {

        IO.println("=".repeat(70));
        IO.println("🏭 ABSTRACT FACTORY PATTERN - SISTEMA DE UI MULTI-PLATAFORMA");
        IO.println("=".repeat(70));

        // ====================================================================
        // EXEMPLO 1: Aplicação Windows com tema Dark
        // ====================================================================
        IO.println("\n📌 EXEMPLO 1: Windows Application (Dark Theme)\n");

        UIFactory windowsFactory = new WindowsUIFactory(Theme.DARK);
        Application windowsApp = new Application(windowsFactory);
        windowsApp.render();
        windowsApp.interact();

        Thread.sleep(1000);

        // ====================================================================
        // EXEMPLO 2: Aplicação macOS com tema Light
        // ====================================================================
        IO.println("\n📌 EXEMPLO 2: macOS Application (Light Theme)\n");

        UIFactory macFactory = new MacUIFactory(Theme.LIGHT);
        Application macApp = new Application(macFactory);
        macApp.render();
        macApp.interact();

        Thread.sleep(1000);

        // ====================================================================
        // EXEMPLO 3: Aplicação Linux com tema High Contrast
        // ====================================================================
        IO.println("\n📌 EXEMPLO 3: Linux Application (High Contrast Theme)\n");

        UIFactory linuxFactory = new LinuxUIFactory(Theme.HIGH_CONTRAST);
        Application linuxApp = new Application(linuxFactory);
        linuxApp.render();
        linuxApp.interact();

        // ====================================================================
        // EXEMPLO 4: Alternando plataformas dinamicamente
        // ====================================================================
        IO.println("\n📌 EXEMPLO 4: Dynamic Platform Switching\n");

        List<UIFactory> factories = List.of(
                new WindowsUIFactory(Theme.LIGHT),
                new MacUIFactory(Theme.DARK),
                new LinuxUIFactory(Theme.LIGHT)
        );

        IO.println("Testing all platforms with same application logic:\n");

        for (UIFactory factory : factories) {
            Button btn = factory.createButton("Test Button");
            btn.render();
            btn.onClick("test-action");
            IO.println();
            Thread.sleep(300);
        }

        // ====================================================================
        // EXEMPLO 5: Comparação de Componentes
        // ====================================================================
        IO.println("\n📌 EXEMPLO 5: Component Comparison Across Platforms\n");

        IO.println("DIALOGS COMPARISON:");
        IO.println("─".repeat(60));

        Dialog windowsDialog = new WindowsUIFactory(Theme.DARK).createDialog();
        Dialog macDialog = new MacUIFactory(Theme.DARK).createDialog();
        Dialog linuxDialog = new LinuxUIFactory(Theme.DARK).createDialog();

        windowsDialog.show("Alert", "This is a Windows dialog");
        IO.println();

        macDialog.show("Alert", "This is a macOS dialog");
        IO.println();

        linuxDialog.show("Alert", "This is a Linux dialog");

        IO.println("\n" + "=".repeat(70));
        IO.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
        IO.println("=".repeat(70));
    }

}
