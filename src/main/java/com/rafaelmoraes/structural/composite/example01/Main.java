package com.rafaelmoraes.structural.composite.example01;

import com.rafaelmoraes.structural.composite.example01.client.FileSystemManager;
import com.rafaelmoraes.structural.composite.example01.component.FileSystemComponent;
import com.rafaelmoraes.structural.composite.example01.leaf.File;
import com.rafaelmoraes.structural.composite.example01.leaf.Folder;

import java.util.List;

public class Main {
    static void main(){
        IO.println("=".repeat(70));
        IO.println("🌳 COMPOSITE PATTERN - SISTEMA DE ARQUIVOS HIERÁRQUICO");
        IO.println("=".repeat(70));

        // ====================================================================
        // EXEMPLO 1: Criando Sistema de Arquivos Básico
        // ====================================================================
        IO.println("\n📌 EXEMPLO 1: Criando Sistema de Arquivos\n");

        FileSystemManager fileSystem = new FileSystemManager("C:");
        Folder root = fileSystem.getRoot();

        // Criando pastas principais
        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder music = new Folder("Music");
        Folder projects = new Folder("Projects");

        // Adicionando à raiz
        root.add(documents);
        root.add(pictures);
        root.add(music);
        root.add(projects);

        IO.println();

        // Criando arquivos em Documents
        documents.add(new File("curriculum", "pdf", 250));
        documents.add(new File("carta", "txt", 5, "Conteúdo da carta..."));
        documents.add(new File("relatorio", "pdf", 1024));

        // Criando arquivos em Pictures
        pictures.add(new File("foto_familia", "jpg", 2048));
        pictures.add(new File("paisagem", "png", 3072));

        // Criando arquivos em Music
        music.add(new File("musica1", "mp3", 4096));
        music.add(new File("musica2", "mp3", 5120));

        // Exibindo estrutura
        fileSystem.displayFileSystem();

        // ====================================================================
        // EXEMPLO 2: Estrutura Aninhada (Pastas dentro de Pastas)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 2: Estrutura Hierárquica Aninhada\n");

        // Criando subpastas em Projects
        Folder javaProjects = new Folder("Java");
        Folder pythonProjects = new Folder("Python");
        Folder webProjects = new Folder("Web");

        projects.add(javaProjects);
        projects.add(pythonProjects);
        projects.add(webProjects);

        IO.println();

        // Criando pasta de padrões de projeto dentro de Java
        Folder designPatterns = new Folder("DesignPatterns");
        javaProjects.add(designPatterns);

        // Adicionando arquivos Java
        designPatterns.add(new File("Singleton", "java", 15));
        designPatterns.add(new File("Factory", "java", 20));
        designPatterns.add(new File("Builder", "java", 25));
        designPatterns.add(new File("Prototype", "java", 18));
        designPatterns.add(new File("Adapter", "java", 22));
        designPatterns.add(new File("Composite", "java", 30));

        // Adicionando README
        designPatterns.add(new File("README", "txt", 10, "Padrões de Projeto em Java"));

        // Criando outro projeto Java
        Folder springProject = new Folder("SpringBoot");
        javaProjects.add(springProject);
        springProject.add(new File("Application", "java", 12));
        springProject.add(new File("Controller", "java", 18));
        springProject.add(new File("Service", "java", 20));
        springProject.add(new File("Repository", "java", 15));

        // Projetos Python
        pythonProjects.add(new File("machine_learning", "py", 50));
        pythonProjects.add(new File("data_analysis", "py", 40));

        // Projetos Web
        webProjects.add(new File("index", "html", 8));
        webProjects.add(new File("styles", "css", 12));
        webProjects.add(new File("script", "js", 15));

        // Exibindo estrutura completa
        fileSystem.displayFileSystem();

        // ====================================================================
        // EXEMPLO 3: Estatísticas e Operações Recursivas
        // ====================================================================
        IO.println("\n📌 EXEMPLO 3: Estatísticas e Cálculos Recursivos\n");

        fileSystem.showStatistics();

        IO.println("\n📊 Estatísticas por Pasta:");
        IO.println("─".repeat(60));
        IO.println("Documents: " + documents.calculateTotalSize() + " KB");
        IO.println("Pictures: " + pictures.calculateTotalSize() + " KB");
        IO.println("Music: " + music.calculateTotalSize() + " KB");
        IO.println("Projects: " + projects.calculateTotalSize() + " KB");
        IO.println("  ├─ Java: " + javaProjects.calculateTotalSize() + " KB");
        IO.println("  │  ├─ DesignPatterns: " + designPatterns.calculateTotalSize() + " KB");
        IO.println("  │  └─ SpringBoot: " + springProject.calculateTotalSize() + " KB");
        IO.println("  ├─ Python: " + pythonProjects.calculateTotalSize() + " KB");
        IO.println("  └─ Web: " + webProjects.calculateTotalSize() + " KB");
        IO.println("─".repeat(60));

        // ====================================================================
        // EXEMPLO 4: Busca na Hierarquia
        // ====================================================================
        IO.println("\n📌 EXEMPLO 4: Busca em Toda a Hierarquia\n");

        fileSystem.searchAndDisplay("Composite");
        fileSystem.searchAndDisplay("DesignPatterns");
        fileSystem.searchAndDisplay("foto");
        fileSystem.searchAndDisplay("naoexiste");

        // ====================================================================
        // EXEMPLO 5: Tratamento Uniforme (Polimorfismo)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 5: Tratamento Uniforme (Chave do Composite)\n");

        IO.println("💡 Processando lista mista de arquivos e pastas:");
        IO.println("─".repeat(60));

        // Lista contém tanto Files quanto Folders
        List<FileSystemComponent> mixedList = List.of(
                new File("documento", "pdf", 100),
                new Folder("Nova Pasta"),
                new File("foto", "jpg", 500),
                designPatterns, // Pasta existente
                new File("musica", "mp3", 3000)
        );

        // IMPORTANTE: Mesmo código funciona para ambos!
        for (FileSystemComponent component : mixedList) {
            IO.println("\nProcessando: " + component.getName());
            IO.println("   Tipo: " + (component.isFolder() ? "Pasta 📁" : "Arquivo 📄"));
            IO.println("   Tamanho: " + component.calculateTotalSize() + " KB");

            // Mesma operação para File e Folder!
            component.display("   ");
        }

        IO.println("\n✅ Polimorfismo em ação: mesmo código, comportamentos diferentes!");

        // ====================================================================
        // EXEMPLO 6: Removendo Componentes
        // ====================================================================
        IO.println("\n📌 EXEMPLO 6: Removendo Componentes\n");

        IO.println("Antes da remoção:");
        IO.println("Total de arquivos: " + root.countFiles());

        // Removendo arquivo
        FileSystemComponent toRemove = documents.getChildren().get(0);
        documents.remove(toRemove);

        // Removendo pasta inteira
        root.remove(music);

        IO.println("\nDepois da remoção:");
        IO.println("Total de arquivos: " + root.countFiles());

        fileSystem.displayFileSystem();

        IO.println("\n" + "=".repeat(70));
        IO.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
        IO.println("=".repeat(70));
    }
}
