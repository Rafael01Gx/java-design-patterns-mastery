package com.rafaelmoraes.structural.facade.example01.subsystem;

public class Amplifier {
    private boolean on = false;
    private int volume = 5;
    private String mode = "Stereo";

    public void turnOn() {
        on = true;
        IO.println("🔊 Amplificador: Inicializando...");
        IO.println("   Calibrando canais de áudio...");
        simulateDelay(800);
        IO.println("   ✅ Amplificador pronto");
    }

    public void turnOff() {
        on = false;
        IO.println("🔊 Amplificador: Desligando...");
        IO.println("   ✅ Amplificador desligado");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        IO.println("🔊 Amplificador: Volume em " + volume);
    }

    public void setSurroundSound() {
        this.mode = "Surround 5.1";
        IO.println("🔊 Amplificador: Modo Surround 5.1 ativado");
    }

    public void setStereo() {
        this.mode = "Stereo";
        IO.println("🔊 Amplificador: Modo Stereo ativado");
    }

    public void setInput(String input) {
        IO.println("🔊 Amplificador: Entrada: " + input);
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
