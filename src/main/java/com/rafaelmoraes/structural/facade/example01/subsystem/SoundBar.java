package com.rafaelmoraes.structural.facade.example01.subsystem;

public class SoundBar {
    private boolean on = false;
    private int volume = 15;

    public void turnOn() {
        on = true;
        IO.println("🔈 SoundBar: Ligando...");
        IO.println("   ✅ SoundBar pronta");
    }

    public void turnOff() {
        on = false;
        IO.println("🔈 SoundBar: Desligando...");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        IO.println("🔈 SoundBar: Volume em " + volume);
    }

    public void setMode(String mode) {
        IO.println("🔈 SoundBar: Modo " + mode);
    }
}
