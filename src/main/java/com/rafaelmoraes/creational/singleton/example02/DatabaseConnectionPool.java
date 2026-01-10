package com.rafaelmoraes.creational.singleton.example02;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SINGLETON COM ENUM (Recomendado por Joshua Bloch)
 * Melhor implementação segundo "Effective Java"
 * Protege contra reflexão e serialização

 * ✅ Vantagens: Simples, thread-safe, protegido contra reflexão/serialização
 * ✅ Esta é a MELHOR PRÁTICA segundo especialistas
 */

public enum DatabaseConnectionPool {

    INSTANCE;


    private final Map<String, Connection> connections = new ConcurrentHashMap<>();
    private final AtomicInteger connectionCount = new AtomicInteger(0);
    private final int MAX_CONNECTIONS = 10;

    DatabaseConnectionPool() {
        System.out.println("🔧 DatabaseConnectionPool inicializado (ENUM Singleton)!");
    }

    public Connection getConnection(String database) {
        return connections.computeIfAbsent(database, db -> {
            if (connectionCount.get() >= MAX_CONNECTIONS) {
                throw new RuntimeException("Pool de conexões esgotado!");
            }
            connectionCount.incrementAndGet();
            return new Connection(db);
        });
    }

    public int getActiveConnections() {
        return connectionCount.get();
    }

    public void closeConnection(String database) {
        Connection conn = connections.remove(database);
        if (conn != null) {
            conn.close();
            connectionCount.decrementAndGet();
        }
    }

    // Classe interna representando uma conexão
    static class Connection {
        private final String database;
        private final String id;
        private boolean closed = false;

        Connection(String database) {
            this.database = database;
            this.id = "CONN-" + UUID.randomUUID().toString().substring(0, 8);
            System.out.println("  ✅ Conexão criada: " + id + " -> " + database);
        }

        void close() {
            this.closed = true;
            System.out.println("  ❌ Conexão fechada: " + id);
        }

        boolean isClosed() {
            return closed;
        }

        String getId() {
            return id;
        }
    }
}
