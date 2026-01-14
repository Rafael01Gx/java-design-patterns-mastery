package com.rafaelmoraes.structural.composite.example01.leaf;

import com.rafaelmoraes.structural.composite.example01.component.FileSystemComponent;

public class File extends FileSystemComponent {

    private String extension;
    private String content;

    public File(String name, String extension, int sizeKB) {
        super(name + "." + extension, sizeKB);
        this.extension = extension;
        this.content = "";
    }

    public File(String name, String extension, int sizeKB, String content) {
        super(name + "." + extension, sizeKB);
        this.extension = extension;
        this.content = content;
    }

    public String getExtension() {
        return extension;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void display(String indent) {
        String icon = getIconByExtension();
        IO.println(indent + icon + " " + name + " (" + size + " KB)");
    }

    @Override
    public int calculateTotalSize() {
        return size; // Arquivo tem tamanho fixo
    }

    @Override
    public FileSystemComponent search(String searchName) {
        if (this.name.toLowerCase().contains(searchName.toLowerCase())) {
            return this;
        }
        return null;
    }

    /**
     * Retorna ícone baseado na extensão
     */
    private String getIconByExtension() {
        return switch (extension.toLowerCase()) {
            case "txt" -> "📄";
            case "java" -> "☕";
            case "pdf" -> "📕";
            case "jpg", "png", "gif" -> "🖼️";
            case "mp3", "wav" -> "🎵";
            case "mp4", "avi" -> "🎬";
            case "zip", "rar" -> "📦";
            case "exe" -> "⚙️";
            default -> "📄";
        };
    }

    @Override
    public String toString() {
        return "File{name='" + name + "', size=" + size + "KB}";
    }
}