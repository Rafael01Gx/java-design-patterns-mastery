package com.rafaelmoraes.structural.composite.component;

import java.util.List;

public abstract class FileSystemComponent {
    protected String name;
    protected int size; // em KB

    public FileSystemComponent(String name, int size) {
        this.name = name;
        this.size = size;
    }

    // Operações comuns
    public String getName() { return name; }
    public int getSize() { return size; }

    /**
     * Exibe a estrutura (implementado por ambos)
     */
    public abstract void display(String indent);

    /**
     * Calcula tamanho total (implementado por ambos)
     */
    public abstract int calculateTotalSize();

    /**
     * Busca por nome (implementado por ambos)
     */
    public abstract FileSystemComponent search(String searchName);

    // Operações de composição (apenas para Folders)
    // Fornece implementação padrão que lança exceção
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public List<FileSystemComponent> getChildren() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public boolean isFolder() {
        return false; // Sobrescrito por Folder
    }
}
