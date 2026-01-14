package com.rafaelmoraes.structural.flyweight.example01.context;

import com.rafaelmoraes.structural.flyweight.example01.interfaces.CharacterFlyweight;

/**
 * Representa um caractere em um documento específico
 * Armazena ESTADO EXTRÍNSECO (não compartilhável)
 */
public class CharacterContext {

    // Referência ao flyweight compartilhado
    private final CharacterFlyweight flyweight;

    // ESTADO EXTRÍNSECO - Específico deste contexto
    private final int position;
    private final String color;
    private final int fontSize;

    public CharacterContext(CharacterFlyweight flyweight, int position,
                            String color, int fontSize) {
        this.flyweight = flyweight;
        this.position = position;
        this.color = color;
        this.fontSize = fontSize;
    }

    public void render() {
        flyweight.render(position, color, fontSize);
    }

    public CharacterFlyweight getFlyweight() {
        return flyweight;
    }
}
