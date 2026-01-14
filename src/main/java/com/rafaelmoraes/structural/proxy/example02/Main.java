package com.rafaelmoraes.structural.proxy.example02;

import com.rafaelmoraes.structural.proxy.example02.subject.*;

public class Main {
    static void main() throws InterruptedException {
        IO.println("=".repeat(70));
        IO.println("🔒 PROXY PATTERN - CONTROLE DE ACESSO A RECURSOS");
        IO.println("=".repeat(70));

        // ====================================================================
        // EXEMPLO 1: Protection Proxy - Usuário Admin
        // ====================================================================
        IO.println("\n📌 EXEMPLO 1: Protection Proxy - Usuário ADMIN\n");

        DatabaseService adminDb = new ProtectionProxyDatabase(
                "localhost:5432", "admin", "admin");

        adminDb.connect();
        adminDb.executeQuery("SELECT * FROM users");
        adminDb.insert("users", "User{id=4, name='Novo User'}");
        adminDb.delete("users", "4");
        adminDb.disconnect();

        Thread.sleep(500);

        // ====================================================================
        // EXEMPLO 2: Protection Proxy - Usuário Comum (Permissões Limitadas)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 2: Protection Proxy - Usuário COMUM\n");

        DatabaseService userDb = new ProtectionProxyDatabase(
                "localhost:5432", "joao", "user");

        userDb.connect();
        userDb.executeQuery("SELECT * FROM products");
        userDb.insert("products", "Product{id=104, name='Webcam'}");

        // Tentativa de DELETE (sem permissão)
        userDb.delete("products", "104"); // SERÁ BLOQUEADO!

        userDb.disconnect();

        Thread.sleep(500);

        // ====================================================================
        // EXEMPLO 3: Protection Proxy - Usuário Guest (Só Leitura)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 3: Protection Proxy - Usuário GUEST\n");

        DatabaseService guestDb = new ProtectionProxyDatabase(
                "localhost:5432", "visitante", "guest");

        guestDb.connect();
        guestDb.executeQuery("SELECT * FROM users");

        // Tentativas de escrita (sem permissão)
        guestDb.insert("users", "User{id=5}"); // BLOQUEADO!
        guestDb.update("users", "1", "data"); // BLOQUEADO!
        guestDb.delete("users", "1"); // BLOQUEADO!

        guestDb.disconnect();

        Thread.sleep(500);

        // ====================================================================
        // EXEMPLO 4: Caching Proxy - Melhorando Performance
        // ====================================================================
        IO.println("\n📌 EXEMPLO 4: Caching Proxy - Cache em Ação\n");

        CachingProxyDatabase cacheDb = new CachingProxyDatabase("localhost:5432");

        cacheDb.connect();

        // Primeira execução - CACHE MISS
        IO.println("\n--- Primeira Query ---");
        cacheDb.executeQuery("SELECT * FROM users");

        Thread.sleep(1000);

        // Segunda execução - CACHE HIT (mesma query)
        IO.println("\n--- Segunda Query (mesma) ---");
        cacheDb.executeQuery("SELECT * FROM users");

        Thread.sleep(1000);

        // Query diferente - CACHE MISS
        IO.println("\n--- Query Diferente ---");
        cacheDb.executeQuery("SELECT * FROM products");

        // Insert invalida cache
        IO.println("\n--- Insert (invalida cache) ---");
        cacheDb.insert("users", "User{id=6}");

        // Query novamente - CACHE MISS (foi invalidado)
        IO.println("\n--- Query Após Insert ---");
        cacheDb.executeQuery("SELECT * FROM users");

        cacheDb.showCacheStats();
        cacheDb.disconnect();

        Thread.sleep(500);

        // ====================================================================
        // EXEMPLO 5: Logging Proxy - Auditoria Completa
        // ====================================================================
        IO.println("\n📌 EXEMPLO 5: Logging Proxy - Registrando Tudo\n");

        LoggingProxyDatabase logDb = new LoggingProxyDatabase("localhost:5432");

        logDb.connect();
        logDb.executeQuery("SELECT * FROM users");
        logDb.insert("users", "User{id=7, name='Test'}");
        logDb.update("users", "7", "User{id=7, name='Updated'}");
        logDb.delete("users", "7");
        logDb.disconnect();

        logDb.showLogs();

        // ====================================================================
        // EXEMPLO 6: Comparação - Com e Sem Proxy
        // ====================================================================
        IO.println("\n📌 EXEMPLO 6: Comparação - Lazy Loading\n");

        IO.println("--- SEM Proxy (criação imediata) ---");
        long start = System.currentTimeMillis();
        RealDatabaseService directService = new RealDatabaseService("localhost:5432");
        long end = System.currentTimeMillis();
        IO.println("⏱️  Tempo de criação: " + (end - start) + "ms");

        Thread.sleep(500);

        IO.println("\n--- COM Proxy (lazy loading) ---");
        start = System.currentTimeMillis();
        DatabaseService proxyService = new ProtectionProxyDatabase(
                "localhost:5432", "user", "admin");
        end = System.currentTimeMillis();
        IO.println("⏱️  Tempo de criação do Proxy: " + (end - start) + "ms");
        IO.println("✅ Proxy criado INSTANTANEAMENTE!");
        IO.println("💡 Serviço real só será criado quando necessário");

        IO.println("\n" + "=".repeat(70));
        IO.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
        IO.println("=".repeat(70));
    }
}
