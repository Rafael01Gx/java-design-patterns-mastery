package com.rafaelmoraes.structural.flyweight.example01.client;

import com.rafaelmoraes.structural.flyweight.example01.context.CharacterContext;
import com.rafaelmoraes.structural.flyweight.example01.factory.CharacterFactory;
import com.rafaelmoraes.structural.flyweight.example01.interfaces.CharacterFlyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um documento de texto
 * Usa flyweights para economizar memória
 */
public class TextDocument {

    private String name;
    private List<CharacterContext> characters;

    public TextDocument(String name) {
        this.name = name;
        this.characters = new ArrayList<>();
    }

    /**
     * Adiciona texto ao documento
     */
    public void addText(String text, String fontFamily, String color, int fontSize) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Obtém flyweight (compartilhado)
            CharacterFlyweight flyweight = CharacterFactory.getCharacter(c, fontFamily);

            // Cria contexto com estado extrínseco (único)
            int position = characters.size();
            CharacterContext context = new CharacterContext(
                    flyweight, position, color, fontSize
            );

            characters.add(context);
        }
    }

    /**
     * Renderiza todo o documento
     */
    public void render() {
        IO.println("\n" + "=".repeat(60));
        IO.println("📄 RENDERIZANDO DOCUMENTO: " + name);
        IO.println("=".repeat(60));

        for (CharacterContext context : characters) {
            context.render();
        }

        IO.println("=".repeat(60));
        IO.println("Total de caracteres: " + characters.size());
    }

    /**
     * Retorna estatísticas de memória
     */
    public void showMemoryStats() {
        IO.println("\n💾 ESTATÍSTICAS DE MEMÓRIA:");
        IO.println("─".repeat(60));

        // Memória dos flyweights compartilhados
        long flyweightMemory = CharacterFactory.getTotalMemoryUsage();

        // Memória dos contextos (estado extrínseco)
        // Cada contexto tem: referência (8 bytes) + int (4) + String ref (8) + int (4) = ~24 bytes
        long contextMemory = characters.size() * 24;

        long totalMemory = flyweightMemory + contextMemory;

        // Memória SEM flyweight (cada caractere seria um objeto completo)
        // Seria aproximadamente: characters.size() * 120 bytes
        long memoryWithoutFlyweight = characters.size() * 120;

        IO.println("Caracteres no documento: " + characters.size());
        IO.println("Flyweights únicos: " + CharacterFactory.flyweights.size());
        IO.println();
        IO.println("Memória dos Flyweights: " + flyweightMemory + " bytes");
        IO.println("Memória dos Contextos: " + contextMemory + " bytes");
        IO.println("Total COM Flyweight: " + totalMemory + " bytes");
        IO.println();
        IO.println("Memória SEM Flyweight: " + memoryWithoutFlyweight + " bytes");
        IO.println("Economia: " + (memoryWithoutFlyweight - totalMemory) + " bytes");
        IO.println("Redução: " +
                String.format("%.1f%%", (1 - (double) totalMemory / memoryWithoutFlyweight) * 100));
        IO.println("─".repeat(60));
    }

    public int getCharacterCount() {
        return characters.size();
    }
}
