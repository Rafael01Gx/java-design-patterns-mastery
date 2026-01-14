package com.rafaelmoraes.structural.composite.client;

import com.rafaelmoraes.structural.composite.component.FileSystemComponent;
import com.rafaelmoraes.structural.composite.leaf.Folder;

public class FileSystemManager {

    private Folder root;

    public FileSystemManager(String rootName) {
        this.root = new Folder(rootName);
        IO.println("🖥️  Sistema de arquivos criado: " + rootName);
    }

    public Folder getRoot() {
        return root;
    }

    /**
     * Exibe toda a estrutura do sistema de arquivos
     */
    public void displayFileSystem() {
        IO.println("\n" + "=".repeat(60));
        IO.println("📂 ESTRUTURA DO SISTEMA DE ARQUIVOS");
        IO.println("=".repeat(60));
        root.display("");
        IO.println("=".repeat(60));
    }

    /**
     * Mostra estatísticas do sistema
     */
    public void showStatistics() {
        IO.println("\n" + "=".repeat(60));
        IO.println("📊 ESTATÍSTICAS DO SISTEMA");
        IO.println("=".repeat(60));
        IO.println("Total de Arquivos: " + root.countFiles());
        IO.println("Total de Pastas: " + root.countFolders());
        IO.println("Tamanho Total: " + root.calculateTotalSize() + " KB");
        IO.println("Tamanho em MB: " + String.format("%.2f", root.calculateTotalSize() / 1024.0) + " MB");
        IO.println("=".repeat(60));
    }

    /**
     * Busca arquivo/pasta por nome
     */
    public void searchAndDisplay(String searchTerm) {
        IO.println("\n🔍 Buscando por: '" + searchTerm + "'");
        FileSystemComponent result = root.search(searchTerm);

        if (result != null) {
            IO.println("✅ Encontrado: " + result);
            if (result.isFolder()) {
                IO.println("\n📁 Conteúdo da pasta:");
                result.display("  ");
            }
        } else {
            IO.println("❌ Não encontrado");
        }
    }
}