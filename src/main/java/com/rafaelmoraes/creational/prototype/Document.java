package com.rafaelmoraes.creational.prototype;

import java.util.*;

public class Document  implements Cloneable {
    

    private String title;
    private String content;
    private String author;
    private String category;
    private int pages;
    private boolean published;

    // Lista de seções (objeto complexo aninhado)
    private List<String> sections;

    // Metadados (objeto complexo aninhado)
    private Map<String, String> metadata;

    // CONSTRUTORES
    public Document(String title, String author) {
        this.title = title;
        this.author = author;
        this.content = "";
        this.category = "Geral";
        this.pages = 0;
        this.published = false;
        this.sections = new ArrayList<>();
        this.metadata = new HashMap<>();

        IO.println("✨ Novo documento criado: " + title);
    }

    
    /**
     * SHALLOW COPY - Cópia Superficial
     * Copia os valores primitivos e as referências dos objetos
     * PROBLEMA: Collections compartilham a mesma referência!
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        IO.println("📋 Clonando documento (shallow copy): " + this.title);
        return super.clone();
    }

    /**
     * DEEP COPY - Cópia Profunda
     * Cria novos objetos para Collections e objetos aninhados
     * SOLUÇÃO: Cada clone tem suas próprias Collections independentes
     */
    public Document deepClone() {
        IO.println("📋 Clonando documento (deep copy): " + this.title);

        // Cria novo documento
        Document cloned = new Document(this.title + " (Cópia)", this.author);

        // Copia valores primitivos e Strings
        cloned.content = this.content;
        cloned.category = this.category;
        cloned.pages = this.pages;
        cloned.published = this.published;

        // IMPORTANTE: Cria novas Collections (deep copy)
        cloned.sections = new ArrayList<>(this.sections);
        cloned.metadata = new HashMap<>(this.metadata);

        return cloned;
    }

    /**
     * Método de conveniência para clonar com novo título
     */
    public Document cloneWithTitle(String newTitle) {
        Document cloned = this.deepClone();
        cloned.title = newTitle;
        return cloned;
    }

    // MÉTODOS DE NEGÓCIO
    public void addSection(String section) {
        this.sections.add(section);
        this.pages++;
    }

    public void addMetadata(String key, String value) {
        this.metadata.put(key, value);
    }

    public void publish() {
        this.published = true;
        this.metadata.put("publishDate", new Date().toString());
        IO.println("📢 Documento publicado: " + this.title);
    }

    public void displayInfo() {
        IO.println("\n" + "=".repeat(60));
        IO.println("📄 DOCUMENTO: " + title);
        IO.println("=".repeat(60));
        IO.println("Autor: " + author);
        IO.println("Categoria: " + category);
        IO.println("Páginas: " + pages);
        IO.println("Status: " + (published ? "✅ Publicado" : "📝 Rascunho"));

        if (!sections.isEmpty()) {
            IO.println("\n📑 Seções (" + sections.size() + "):");
            for (int i = 0; i < sections.size(); i++) {
                IO.println("   " + (i + 1) + ". " + sections.get(i));
            }
        }

        if (!metadata.isEmpty()) {
            IO.println("\n🏷️  Metadados:");
            metadata.forEach((k, v) -> IO.println("   " + k + ": " + v));
        }

        if (content != null && !content.isEmpty()) {
            IO.println("\n📝 Conteúdo:");
            IO.println("   " + content.substring(0, Math.min(100, content.length())) + "...");
        }

        IO.println("=".repeat(60));
    }

    // GETTERS E SETTERS
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPages() {
        return pages;
    }

    public boolean isPublished() {
        return published;
    }

    public List<String> getSections() {
        return sections;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    // REGISTRY DE PROTÓTIPOS (DESIGN AVANÇADO)
    /**
     * Registro central de protótipos (templates)
     * Permite armazenar e recuperar protótipos prontos
     */
    public static class PrototypeRegistry {

        private static Map<String, Document> prototypes = new HashMap<>();

        /**
         * Registra um protótipo com uma chave
         */
        public static void addPrototype(String key, Document prototype) {
            prototypes.put(key, prototype);
            IO.println("📚 Protótipo registrado: " + key);
        }

        /**
         * Recupera e clona um protótipo
         */
        public static Document getPrototype(String key) {
            Document prototype = prototypes.get(key);
            if (prototype != null) {
                return prototype.deepClone();
            }
            return null;
        }

        /**
         * Lista todos os protótipos disponíveis
         */
        public static void listPrototypes() {
            IO.println("\n📚 PROTÓTIPOS DISPONÍVEIS:");
            IO.println("─".repeat(60));
            prototypes.forEach((key, doc) ->
                    IO.println("   • " + key + " - " + doc.getTitle())
            );
            IO.println("─".repeat(60));
        }

        /**
         * Remove um protótipo
         */
        public static void removePrototype(String key) {
            prototypes.remove(key);
            IO.println("🗑️  Protótipo removido: " + key);
        }

        /**
         * Limpa todos os protótipos
         */
        public static void clear() {
            prototypes.clear();
            IO.println("🗑️  Todos os protótipos removidos");
        }
    }
}