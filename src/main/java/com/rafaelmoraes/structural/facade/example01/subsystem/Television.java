package com.rafaelmoraes.structural.facade.example01.subsystem;

public class Television {
    private boolean on = false;
    private int volume = 10;
    private int channel = 1;
    private String inputSource = "HDMI1";

    public void turnOn() {
        on = true;
        IO.println("📺 TV: Ligando...");
        IO.println("   Carregando sistema operacional...");
        simulateDelay(1000);
        IO.println("   ✅ TV ligada");
    }

    public void turnOff() {
        on = false;
        IO.println("📺 TV: Desligando...");
        IO.println("   ✅ TV desligada");
    }

    public void setInputSource(String source) {
        this.inputSource = source;
        IO.println("📺 TV: Entrada alterada para " + source);
    }

    public void setVolume(int volume) {
        this.volume = volume;
        IO.println("📺 TV: Volume ajustado para " + volume);
    }

    public void setPictureMode(String mode) {
        IO.println("📺 TV: Modo de imagem: " + mode);
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
