package com.rafaelmoraes.structural.facade.example01.subsystem;

public class Projector {
    private boolean on = false;
    private String aspectRatio = "16:9";

    public void turnOn() {
        on = true;
        IO.println("📽️  Projetor: Ligando...");
        IO.println("   Aquecendo lâmpada...");
        simulateDelay(1200);
        IO.println("   ✅ Projetor pronto");
    }

    public void turnOff() {
        on = false;
        IO.println("📽️  Projetor: Desligando...");
        IO.println("   Resfriando lâmpada...");
        simulateDelay(800);
        IO.println("   ✅ Projetor desligado");
    }

    public void setWideScreenMode() {
        this.aspectRatio = "16:9";
        IO.println("📽️  Projetor: Modo widescreen (16:9)");
    }

    public void setInput(String input) {
        IO.println("📽️  Projetor: Entrada: " + input);
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
