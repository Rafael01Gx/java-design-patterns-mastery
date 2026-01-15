package com.rafaelmoraes.structural.facade.example01.subsystem;

public class DvdPlayer {

    private boolean on = false;
    private String currentDisc = null;
    private boolean playing = false;

    public void turnOn() {
        on = true;
        IO.println("💿 DVD Player: Ligando...");
        IO.println("   Inicializando leitor óptico...");
        simulateDelay(500);
        IO.println("   ✅ DVD Player pronto");
    }

    public void turnOff() {
        on = false;
        playing = false;
        IO.println("💿 DVD Player: Desligando...");
        IO.println("   ✅ DVD Player desligado");
    }

    public void insertDisc(String movie) {
        this.currentDisc = movie;
        IO.println("💿 DVD Player: Disco inserido - '" + movie + "'");
        IO.println("   Lendo informações do disco...");
        simulateDelay(700);
    }

    public void play() {
        if (currentDisc != null) {
            playing = true;
            IO.println("💿 DVD Player: ▶️  Reproduzindo '" + currentDisc + "'");
        } else {
            IO.println("💿 DVD Player: ❌ Nenhum disco inserido");
        }
    }

    public void pause() {
        playing = false;
        IO.println("💿 DVD Player: ⏸️  Pausado");
    }

    public void stop() {
        playing = false;
        IO.println("💿 DVD Player: ⏹️  Parado");
    }

    public void eject() {
        playing = false;
        IO.println("💿 DVD Player: Ejetando disco '" + currentDisc + "'");
        currentDisc = null;
    }

    private void simulateDelay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
