package com.rafaelmoraes.structural.facade.example01;

import com.rafaelmoraes.structural.facade.example01.subsystem.*;

public class Main {
    
    static void main () throws InterruptedException {
        IO.println("=".repeat(70));
        IO.println("🏠 FACADE PATTERN - SISTEMA DE HOME THEATER");
        IO.println("=".repeat(70));

        // Criando todos os subsistemas
        Television tv = new Television();
        Amplifier amplifier = new Amplifier();
        DvdPlayer dvdPlayer = new DvdPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();
        StreamingDevice streaming = new StreamingDevice();
        SoundBar soundBar = new SoundBar();

        // Criando a Facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
                tv, amplifier, dvdPlayer, projector, lights, streaming, soundBar
        );

        // ====================================================================
        // EXEMPLO 1: SEM Facade (Modo Manual - Complexo)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 1: SEM Facade (Modo Manual)\n");
        IO.println("❌ PROBLEMA: Cliente precisa conhecer TODOS os subsistemas!");
        IO.println();

        IO.println("--- Preparando para assistir filme manualmente ---");
        lights.dim(10);
        tv.turnOn();
        tv.setInputSource("HDMI1");
        tv.setPictureMode("Cinema");
        amplifier.turnOn();
        amplifier.setVolume(5);
        amplifier.setSurroundSound();
        amplifier.setInput("DVD");
        dvdPlayer.turnOn();
        dvdPlayer.insertDisc("Matrix");
        dvdPlayer.play();

        IO.println("\n😰 Muitos passos! Fácil esquecer algo!");

        Thread.sleep(1000);

        // Desligando manualmente
        IO.println("\n--- Desligando manualmente ---");
        dvdPlayer.stop();
        dvdPlayer.turnOff();
        amplifier.turnOff();
        tv.turnOff();
        lights.turnOn();

        Thread.sleep(1000);

        // ====================================================================
        // EXEMPLO 2: COM Facade (Simplificado)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 2: COM Facade (Simplificado)\n");
        IO.println("✅ SOLUÇÃO: Uma única chamada faz tudo!");
        IO.println();

        homeTheater.watchMovie("Interstellar");

        Thread.sleep(2000);

        homeTheater.endMovie();

        Thread.sleep(1000);

        // ====================================================================
        // EXEMPLO 3: Assistir Netflix
        // ====================================================================
        IO.println("\n📌 EXEMPLO 3: Assistir Netflix\n");

        homeTheater.watchNetflix();

        Thread.sleep(2000);

        homeTheater.allOff();

        Thread.sleep(1000);

        // ====================================================================
        // EXEMPLO 4: Modo Cinema com Projetor
        // ====================================================================
        IO.println("\n📌 EXEMPLO 4: Modo Cinema com Projetor\n");

        homeTheater.cinemaMode("The Godfather");

        Thread.sleep(2000);

        homeTheater.allOff();

        Thread.sleep(1000);

        // ====================================================================
        // EXEMPLO 5: Assistir Jogo de Futebol
        // ====================================================================
        IO.println("\n📌 EXEMPLO 5: Assistir Jogo de Futebol\n");

        homeTheater.watchSports();

        Thread.sleep(2000);

        homeTheater.allOff();

        Thread.sleep(1000);

        // ====================================================================
        // EXEMPLO 6: Modo Festa
        // ====================================================================
        IO.println("\n📌 EXEMPLO 6: Modo Festa\n");

        homeTheater.partyMode();

        Thread.sleep(2000);

        homeTheater.allOff();

        // ====================================================================
        // COMPARAÇÃO FINAL
        // ====================================================================
        IO.println("""
                
         COMPARAÇÃO: SEM vs COM Facade
        
        SEM FACADE (Complexo):
        --------------------------------------------
        lights.dim(10)
        tv.turnOn()
        tv.setInputSource(\"HDMI1\");
        tv.setPictureMode(\"Cinema\");
        amplifier.turnOn();
        amplifier.setVolume(5);
        amplifier.setSurroundSound();
        amplifier.setInput(\"DVD\");
        dvdPlayer.turnOn();
        dvdPlayer.insertDisc(\\"Matrix\\");
        dvdPlayer.play();
        --------------------------------------------
        ❌ 11 linhas de código!
        ❌ Cliente precisa conhecer 7 subsistemas!
        ❌ Fácil cometer erros!
        IO.println()
        COM FACADE (Simples):
        
        --------------------------------------------
        
        homeTheater.watchMovie(\\"Matrix\\");
        ─
        ✅ 1 linha de código!
        ✅ Cliente só conhece a Facade!
        ✅ Impossível errar!
        
        --------------------------------------------
        
        ✅ DEMONSTRAÇÃO CONCLUÍDA!
        
        """);
    }


}
