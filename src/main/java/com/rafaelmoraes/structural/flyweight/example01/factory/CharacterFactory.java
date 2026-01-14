package com.rafaelmoraes.structural.flyweight.example01.factory;

import com.rafaelmoraes.structural.flyweight.example01.concrete.ConcreteCharacter;
import com.rafaelmoraes.structural.flyweight.example01.interfaces.CharacterFlyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory que garante que flyweights sejam compartilhados
 * Usa padrão Singleton internamente
 */
public class CharacterFactory {

    // Pool de flyweights compartilhados
    // Key: "character-fontFamily" (ex: "A-Arial")
    public static final Map<String, CharacterFlyweight> flyweights = new HashMap<>();

    // Estatísticas
    private static int totalCreated = 0;
    private static int totalRequests = 0;

    /**
     * Retorna flyweight para o caractere e fonte
     * Cria novo se não existir, reutiliza se já existir
     */
    public static CharacterFlyweight getCharacter(char character, String fontFamily) {
        totalRequests++;

        String key = character + "-" + fontFamily;

        // Verifica se já existe (compartilhamento!)
        CharacterFlyweight flyweight = flyweights.get(key);

        if (flyweight == null) {
            // Não existe - cria novo
            flyweight = new ConcreteCharacter(character, fontFamily);
            flyweights.put(key, flyweight);
            totalCreated++;
        } else {
            // Já existe - reutiliza
            IO.println("   ♻️  Flyweight REUTILIZADO: '" + character + "' em " + fontFamily);
        }

        return flyweight;
    }

    /**
     * Retorna estatísticas de uso
     */
    public static void showStatistics() {
        IO.println("\n" + "=".repeat(60));
        IO.println("📊 ESTATÍSTICAS DA FLYWEIGHT FACTORY");
        IO.println("=".repeat(60));
        IO.println("Total de Flyweights Criados: " + totalCreated);
        IO.println("Total de Requisições: " + totalRequests);
        IO.println("Taxa de Reuso: " +
                String.format("%.1f%%", (1 - (double) totalCreated / totalRequests) * 100));
        IO.println("Flyweights Únicos em Cache: " + flyweights.size());
        IO.println("=".repeat(60));
    }

    /**
     * Calcula memória total usada pelos flyweights
     */
    public static long getTotalMemoryUsage() {
        long total = 0;
        for (CharacterFlyweight flyweight : flyweights.values()) {
            if (flyweight instanceof ConcreteCharacter) {
                total += ((ConcreteCharacter) flyweight).getMemorySize();
            }
        }
        return total;
    }

    /**
     * Limpa o cache (útil para testes)
     */
    public static void clearCache() {
        flyweights.clear();
        totalCreated = 0;
        totalRequests = 0;
        IO.println("🗑️  Cache limpo");
    }
}

