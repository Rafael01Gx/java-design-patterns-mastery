package com.rafaelmoraes.structural.flyweight.example01.concrete;

import com.rafaelmoraes.structural.flyweight.example01.interfaces.CharacterFlyweight;

/**
 * Flyweight concreto - representa um caractere com fonte específica
 * Este objeto é COMPARTILHADO entre múltiplas instâncias
 */
public class ConcreteCharacter implements CharacterFlyweight {

    // ESTADO INTRÍNSECO - Compartilhado e Imutável
    private final char character;
    private final String fontFamily;

    // Metadados sobre renderização (compartilhados)
    private final int glyphWidth; // Largura do glifo
    private final byte[] glyphData; // Dados do glifo (simulado)

    public ConcreteCharacter(char character, String fontFamily) {
        this.character = character;
        this.fontFamily = fontFamily;

        // Simula carregamento de dados pesados do glifo
        // Em um editor real, isso seria vetores, bitmaps, etc
        this.glyphWidth = 10;
        this.glyphData = new byte[100]; // Simula 100 bytes de dados

        IO.println("   🆕 Flyweight CRIADO: '" + character + "' em " + fontFamily);
    }

    @Override
    public void render(int position, String color, int fontSize) {
        // Usa estado intrínseco (compartilhado) + extrínseco (parâmetros)
        IO.println(
                String.format("   Renderizando '%c' [%s, %s, %dpt] na posição %d",
                        character, fontFamily, color, fontSize, position)
        );
    }

    @Override
    public char getCharacter() {
        return character;
    }

    @Override
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * Retorna tamanho aproximado em bytes que este flyweight ocupa
     */
    public int getMemorySize() {
        return 2 + // char (2 bytes)
                fontFamily.length() * 2 + // String
                4 + // int glyphWidth
                glyphData.length; // byte array
    }
}