package com.rafaelmoraes.structural.composite.example01.leaf;

import com.rafaelmoraes.structural.composite.example01.component.FileSystemComponent;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemComponent {

    private List<FileSystemComponent> children;

    public Folder(String name) {
        super(name, 0); // Pasta começa com tamanho 0
        this.children = new ArrayList<>();
    }

    /**
     * Adiciona arquivo ou pasta dentro desta pasta
     */
    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
        System.out.println("   ✅ Adicionado: " + component.getName() + " em " + this.name);
    }

    /**
     * Remove arquivo ou pasta
     */
    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
        System.out.println("   🗑️  Removido: " + component.getName() + " de " + this.name);
    }

    /**
     * Retorna lista de componentes filhos
     */
    @Override
    public List<FileSystemComponent> getChildren() {
        return new ArrayList<>(children); // Retorna cópia para segurança
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Exibe a estrutura da pasta e todo seu conteúdo (recursivo)
     */
    @Override
    public void display(String indent) {
        System.out.println(indent + "📁 " + name + "/ (" + calculateTotalSize() + " KB)");

        // Exibe cada filho (recursivamente)
        for (FileSystemComponent child : children) {
            child.display(indent + "  ");
        }
    }

    /**
     * Calcula tamanho total (soma de todos os filhos - recursivo)
     */
    @Override
    public int calculateTotalSize() {
        int totalSize = 0;

        // Soma o tamanho de todos os filhos
        for (FileSystemComponent child : children) {
            totalSize += child.calculateTotalSize();
        }

        return totalSize;
    }

    /**
     * Busca por nome em toda a hierarquia (recursivo)
     */
    @Override
    public FileSystemComponent search(String searchName) {
        // Verifica se a própria pasta corresponde
        if (this.name.toLowerCase().contains(searchName.toLowerCase())) {
            return this;
        }

        // Busca nos filhos (recursivamente)
        for (FileSystemComponent child : children) {
            FileSystemComponent result = child.search(searchName);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * Conta total de arquivos na pasta e subpastas
     */
    public int countFiles() {
        int count = 0;

        for (FileSystemComponent child : children) {
            if (child.isFolder()) {
                count += ((Folder) child).countFiles();
            } else {
                count++;
            }
        }

        return count;
    }

    /**
     * Conta total de pastas
     */
    public int countFolders() {
        int count = 0;

        for (FileSystemComponent child : children) {
            if (child.isFolder()) {
                count++;
                count += ((Folder) child).countFolders();
            }
        }

        return count;
    }

    @Override
    public String toString() {
        return "Folder{name='" + name + "', files=" + countFiles() +
                ", folders=" + countFolders() + ", totalSize=" + calculateTotalSize() + "KB}";
    }
}