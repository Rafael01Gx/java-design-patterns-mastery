package com.rafaelmoraes.structural.proxy.example02.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Este é o objeto que realmente executa as operações
 * Criar instância é CARO (conexão pesada, recursos, etc)
 */

public class RealDatabaseService implements DatabaseService {

    private String serverAddress;
    private boolean connected = false;
    private Map<String, List<String>> database; // Simula banco de dados

    /**
     * Construtor pesado - simula criação cara de conexão
     */
    public RealDatabaseService(String serverAddress) {
        this.serverAddress = serverAddress;
        this.database = new HashMap<>();

        // Simula operação CARA de criação
        IO.println("\n⚙️  Criando conexão real com banco de dados...");
        IO.println("   Servidor: " + serverAddress);
        IO.println("   Carregando drivers...");
        simulateHeavyOperation(2000);
        IO.println("   Alocando recursos...");
        simulateHeavyOperation(1000);
        IO.println("   ✅ Serviço real criado (operação cara!)");

        // Popula banco com dados de exemplo
        populateSampleData();
    }

    @Override
    public void connect() {
        if (!connected) {
            IO.println("\n🔌 Conectando ao servidor: " + serverAddress);
            simulateHeavyOperation(1500);
            connected = true;
            IO.println("   ✅ Conectado!");
        } else {
            IO.println("   ⚠️  Já está conectado");
        }
    }

    @Override
    public List<String> executeQuery(String query) {
        if (!connected) {
            IO.println("   ❌ Erro: Não está conectado");
            return List.of();
        }

        IO.println("\n📊 Executando query real: " + query);
        simulateHeavyOperation(500);

        // Simula execução de query
        if (query.toLowerCase().startsWith("select")) {
            String table = extractTableName(query);
            List<String> results = database.getOrDefault(table, List.of());
            IO.println("   ✅ Query executada - " + results.size() + " resultados");
            return new ArrayList<>(results);
        }

        return List.of();
    }

    @Override
    public boolean insert(String table, String data) {
        if (!connected) {
            IO.println("   ❌ Erro: Não está conectado");
            return false;
        }

        IO.println("\n➕ Inserindo dados reais em " + table);
        simulateHeavyOperation(300);

        database.computeIfAbsent(table, k -> new ArrayList<>()).add(data);
        IO.println("   ✅ Dados inseridos");
        return true;
    }

    @Override
    public boolean update(String table, String id, String data) {
        if (!connected) {
            IO.println("   ❌ Erro: Não está conectado");
            return false;
        }

        IO.println("\n🔄 Atualizando dados reais em " + table);
        simulateHeavyOperation(300);
        IO.println("   ✅ Dados atualizados");
        return true;
    }

    @Override
    public boolean delete(String table, String id) {
        if (!connected) {
            IO.println("   ❌ Erro: Não está conectado");
            return false;
        }

        IO.println("\n🗑️  Deletando dados reais de " + table);
        simulateHeavyOperation(300);
        IO.println("   ✅ Dados deletados");
        return true;
    }

    @Override
    public void disconnect() {
        if (connected) {
            IO.println("\n🔌 Desconectando de: " + serverAddress);
            simulateHeavyOperation(500);
            connected = false;
            IO.println("   ✅ Desconectado!");
        }
    }

    private void populateSampleData() {
        database.put("users", new ArrayList<>(List.of(
                "User{id=1, name='João Silva', role='admin'}",
                "User{id=2, name='Maria Santos', role='user'}",
                "User{id=3, name='Pedro Costa', role='user'}"
        )));

        database.put("products", new ArrayList<>(List.of(
                "Product{id=101, name='Notebook', price=2500.00}",
                "Product{id=102, name='Mouse', price=50.00}",
                "Product{id=103, name='Teclado', price=150.00}"
        )));
    }

    private String extractTableName(String query) {
        String[] parts = query.toLowerCase().split(" ");
        for (int i = 0; i < parts.length - 1; i++) {
            if (parts[i].equals("from")) {
                return parts[i + 1].replace(";", "");
            }
        }
        return "unknown";
    }

    private void simulateHeavyOperation(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
