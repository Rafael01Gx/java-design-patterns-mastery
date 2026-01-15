package com.rafaelmoraes.structural.facade.example01.subsystem;

public class Lights {
    private int brightness = 100; // 0-100%

    public void dim(int level) {
        this.brightness = level;
        IO.println("💡 Luzes: Diminuindo para " + level + "%");
    }

    public void turnOn() {
        this.brightness = 100;
        IO.println("💡 Luzes: Acesas (100%)");
    }

    public void turnOff() {
        this.brightness = 0;
        IO.println("💡 Luzes: Apagadas");
    }
}
