package com.rafaelmoraes.structural.facade.example01.subsystem;

public class StreamingDevice {
    private boolean on = false;
    private String currentApp = null;

    public void turnOn() {
        on = true;
        IO.println("📱 Streaming Device: Ligando...");
        IO.println("   Conectando à WiFi...");
        simulateDelay(600);
        IO.println("   ✅ Device pronto");
    }

    public void turnOff() {
        on = false;
        IO.println("📱 Streaming Device: Desligando...");
    }

    public void openApp(String app) {
        this.currentApp = app;
        IO.println("📱 Streaming Device: Abrindo " + app + "...");
        simulateDelay(500);
        IO.println("   ✅ " + app + " pronto");
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
