package com.rafaelmoraes.structural.flyweight.example01;

import com.rafaelmoraes.structural.flyweight.example01.client.TextDocument;
import com.rafaelmoraes.structural.flyweight.example01.factory.CharacterFactory;

public class Main {
    static void main() {
        IO.println("=".repeat(70));
        IO.println("🪶 FLYWEIGHT PATTERN - EDITOR DE TEXTO EFICIENTE");
        IO.println("=".repeat(70));

        // ====================================================================
        // EXEMPLO 1: Documento Simples
        // ====================================================================
        IO.println("\n📌 EXEMPLO 1: Criando Documento Simples\n");

        TextDocument doc1 = new TextDocument("Documento1.txt");

        IO.println("Adicionando texto 'HELLO':");
        doc1.addText("HELLO", "Arial", "Black", 12);

        IO.println("\nAdicionando texto 'WORLD':");
        doc1.addText("WORLD", "Arial", "Black", 12);

        doc1.render();
        CharacterFactory.showStatistics();

        // ====================================================================
        // EXEMPLO 2: Texto Repetitivo (Máximo Reuso)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 2: Texto com Muita Repetição\n");

        CharacterFactory.clearCache();

        TextDocument doc2 = new TextDocument("Documento2.txt");

        IO.println("Adicionando texto repetitivo:");
        doc2.addText("AAAA", "Times", "Red", 14);
        doc2.addText("AAAA", "Times", "Red", 14);
        doc2.addText("BBBB", "Times", "Blue", 14);

        CharacterFactory.showStatistics();
        IO.println("\n💡 Note: Mesmos caracteres são REUTILIZADOS!");

        // ====================================================================
        // EXEMPLO 3: Documento Grande (Economia de Memória)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 3: Documento Grande (Teste de Performance)\n");

        CharacterFactory.clearCache();

        TextDocument doc3 = new TextDocument("ArtigoGrande.txt");

        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco. ";

        IO.println("Adicionando texto longo (3x Lorem Ipsum)...\n");

        // Adiciona mesmo texto 3 vezes (muita repetição!)
        for (int i = 0; i < 3; i++) {
            doc3.addText(loremIpsum, "Helvetica", "Black", 12);
        }

        IO.println("✅ Texto adicionado!");

        CharacterFactory.showStatistics();
        doc3.showMemoryStats();

        // ====================================================================
        // EXEMPLO 4: Múltiplas Fontes (Mais Flyweights)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 4: Texto com Múltiplas Fontes\n");

        CharacterFactory.clearCache();

        TextDocument doc4 = new TextDocument("Documento4.txt");

        IO.println("Adicionando texto em diferentes fontes:");
        doc4.addText("Arial", "Arial", "Black", 12);
        doc4.addText("Times", "Times New Roman", "Blue", 14);
        doc4.addText("Courier", "Courier New", "Red", 10);

        CharacterFactory.showStatistics();
        IO.println("\n💡 Note: Cada combinação caractere+fonte é um flyweight único!");

        // ====================================================================
        // EXEMPLO 5: Comparação - Com vs Sem Flyweight
        // ====================================================================
        IO.println("\n📌 EXEMPLO 5: Comparação - COM vs SEM Flyweight\n");

        CharacterFactory.clearCache();

        // Texto de teste
        String testText = "The quick brown fox jumps over the lazy dog. " +
                "The quick brown fox jumps over the lazy dog. " +
                "The quick brown fox jumps over the lazy dog.";

        IO.println("Texto de teste (" + testText.length() + " caracteres):");
        IO.println("\"" + testText + "\"");
        IO.println();

        TextDocument doc5 = new TextDocument("Teste.txt");
        doc5.addText(testText, "Arial", "Black", 12);

        CharacterFactory.showStatistics();
        doc5.showMemoryStats();

        // ====================================================================
        // EXEMPLO 6: Cenário Extremo (10.000 caracteres)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 6: Cenário Extremo (10.000 caracteres)\n");

        CharacterFactory.clearCache();

        TextDocument hugeDocs = new TextDocument("DocumentoEnorme.txt");

        IO.println("Gerando documento com 10.000 caracteres...");

        // Gera texto com padrão repetitivo
        StringBuilder hugeText = new StringBuilder();
        String pattern = "ABCDEFGHIJ";
        for (int i = 0; i < 1000; i++) {
            hugeText.append(pattern);
        }

        long startTime = System.currentTimeMillis();
        hugeDocs.addText(hugeText.toString(), "Arial", "Black", 12);
        long endTime = System.currentTimeMillis();

        IO.println("✅ Documento criado em " + (endTime - startTime) + "ms");

        CharacterFactory.showStatistics();
        hugeDocs.showMemoryStats();

        IO.println("\n🎯 RESULTADOS:");
        IO.println("─".repeat(60));
        IO.println("Com apenas 10 flyweights únicos (A-J em Arial),");
        IO.println("conseguimos representar 10.000 caracteres!");
        IO.println("Taxa de reuso: 99.9%");
        IO.println("Economia de memória: ~92%");
        IO.println("─".repeat(60));

        IO.println("\n" + "=".repeat(70));
        IO.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
        IO.println("=".repeat(70));
    }
}
