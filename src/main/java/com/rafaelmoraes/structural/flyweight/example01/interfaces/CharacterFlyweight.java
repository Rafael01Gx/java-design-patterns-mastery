package com.rafaelmoraes.structural.flyweight.example01.interfaces;

public interface CharacterFlyweight {

    /**
     * Renderiza o caractere em uma posição específica
     *
     * @param position Posição no documento (estado extrínseco)
     * @param color Cor do caractere (estado extrínseco)
     * @param fontSize Tamanho da fonte (estado extrínseco)
     */
    void render(int position, String color, int fontSize);

    /**
     * Retorna o caractere que este flyweight representa
     */
    char getCharacter();

    /**
     * Retorna a família da fonte (estado intrínseco)
     */
    String getFontFamily();
}
