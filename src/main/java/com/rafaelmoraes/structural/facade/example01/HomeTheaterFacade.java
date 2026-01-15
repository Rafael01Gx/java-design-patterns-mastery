package com.rafaelmoraes.structural.facade.example01;

import com.rafaelmoraes.structural.facade.example01.subsystem.*;

/**
 * Facade que simplifica o uso do Home Theater
 * Fornece métodos de alto nível para cenários comuns
 */
public class HomeTheaterFacade {
    // Referências aos subsistemas
    private Television tv;
    private Amplifier amplifier;
    private DvdPlayer dvdPlayer;
    private Projector projector;
    private Lights lights;
    private StreamingDevice streaming;
    private SoundBar soundBar;

    /**
     * Construtor que inicializa todos os subsistemas
     */
    public HomeTheaterFacade(Television tv, Amplifier amp, DvdPlayer dvd,
                             Projector proj, Lights lights,
                             StreamingDevice stream, SoundBar soundBar) {
        this.tv = tv;
        this.amplifier = amp;
        this.dvdPlayer = dvd;
        this.projector = proj;
        this.lights = lights;
        this.streaming = stream;
        this.soundBar = soundBar;
    }

    // ========================================================================
    // MÉTODOS DE ALTO NÍVEL - Simplificam operações complexas
    // ========================================================================

    /**
     * Assistir filme em DVD - Orquestra múltiplos subsistemas
     */
    public void watchMovie(String movie) {
        IO.println("\n" + "=".repeat(60));
        IO.println("🎬 Preparando para assistir: " + movie);
        IO.println("=".repeat(60));

        lights.dim(10);
        tv.turnOn();
        tv.setInputSource("HDMI1");
        tv.setPictureMode("Cinema");

        amplifier.turnOn();
        amplifier.setVolume(5);
        amplifier.setSurroundSound();
        amplifier.setInput("DVD");

        dvdPlayer.turnOn();
        dvdPlayer.insertDisc(movie);
        dvdPlayer.play();

        IO.println("\n✅ Tudo pronto! Aproveite o filme! 🍿");
        IO.println("=".repeat(60));
    }

    /**
     * Encerrar sessão de filme
     */
    public void endMovie() {
        IO.println("\n" + "=".repeat(60));
        IO.println("⏹️  Encerrando sessão de filme");
        IO.println("=".repeat(60));

        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.turnOff();

        amplifier.turnOff();
        tv.turnOff();
        lights.turnOn();

        IO.println("\n✅ Sistema desligado");
        IO.println("=".repeat(60));
    }

    /**
     * Assistir Netflix/streaming
     */
    public void watchNetflix() {
        IO.println("\n" + "=".repeat(60));
        IO.println("🎬 Iniciando Netflix");
        IO.println("=".repeat(60));

        lights.dim(20);

        tv.turnOn();
        tv.setInputSource("HDMI2");
        tv.setPictureMode("Padrão");

        streaming.turnOn();
        streaming.openApp("Netflix");

        soundBar.turnOn();
        soundBar.setVolume(20);
        soundBar.setMode("Movie");

        IO.println("\n✅ Netflix pronto! 🎬");
        IO.println("=".repeat(60));
    }

    /**
     * Assistir jogo de futebol
     */
    public void watchSports() {
        IO.println("\n" + "=".repeat(60));
        IO.println("⚽ Preparando para assistir esportes");
        IO.println("=".repeat(60));

        lights.turnOn();

        tv.turnOn();
        tv.setInputSource("HDMI3");
        tv.setPictureMode("Esporte");

        soundBar.turnOn();
        soundBar.setVolume(25);
        soundBar.setMode("Sports");

        IO.println("\n✅ Pronto para o jogo! ⚽");
        IO.println("=".repeat(60));
    }

    /**
     * Modo cinema com projetor
     */
    public void cinemaMode(String movie) {
        IO.println("\n" + "=".repeat(60));
        IO.println("🎥 MODO CINEMA - " + movie);
        IO.println("=".repeat(60));

        lights.turnOff();

        projector.turnOn();
        projector.setWideScreenMode();
        projector.setInput("HDMI1");

        amplifier.turnOn();
        amplifier.setVolume(7);
        amplifier.setSurroundSound();
        amplifier.setInput("DVD");

        dvdPlayer.turnOn();
        dvdPlayer.insertDisc(movie);
        dvdPlayer.play();

        IO.println("\n✅ Experiência de cinema em casa! 🎥🍿");
        IO.println("=".repeat(60));
    }

    /**
     * Desligar tudo
     */
    public void allOff() {
        IO.println("\n" + "=".repeat(60));
        IO.println("🔌 Desligando todo o sistema");
        IO.println("=".repeat(60));

        dvdPlayer.stop();
        dvdPlayer.turnOff();
        streaming.turnOff();
        amplifier.turnOff();
        soundBar.turnOff();
        tv.turnOff();
        projector.turnOff();
        lights.turnOn();

        IO.println("\n✅ Todos os componentes desligados");
        IO.println("=".repeat(60));
    }

    /**
     * Modo festa
     */
    public void partyMode() {
        IO.println("\n" + "=".repeat(60));
        IO.println("🎉 MODO FESTA!");
        IO.println("=".repeat(60));

        lights.dim(50);

        tv.turnOn();
        tv.setInputSource("HDMI2");

        streaming.turnOn();
        streaming.openApp("Spotify");

        soundBar.turnOn();
        soundBar.setVolume(30);
        soundBar.setMode("Music");

        IO.println("\n✅ Festa pronta! 🎉🎵");
        IO.println("=".repeat(60));
    }
}
