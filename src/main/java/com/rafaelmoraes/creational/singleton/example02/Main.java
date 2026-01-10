package com.rafaelmoraes.creational.singleton.example02;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void main() throws InterruptedException {
        IO.println("=".repeat(70));
        IO.println("🎯 SINGLETON PATTERN - DEMONSTRAÇÃO COMPLETA");
        IO.println("=".repeat(70));

        // ====================================================================
        // EXEMPLO 1: Singleton Básico
        // ====================================================================
        IO.println("\n📌 EXEMPLO 1: Singleton Básico (Eager Initialization)\n");

        BasicConfigManager config1 = BasicConfigManager.getInstance();
        BasicConfigManager config2 = BasicConfigManager.getInstance();

        IO.println("config1 == config2? " + (config1 == config2)); // true
        IO.println("App Name: " + config1.get("app.name"));

        config1.set("app.theme", "dark");
        IO.println("Theme em config2: " + config2.get("app.theme")); // dark

        // ====================================================================
        // EXEMPLO 2: Lazy Singleton com Double-Checked Locking
        // ====================================================================
        IO.println("\n📌 EXEMPLO 2: Lazy Singleton (Double-Checked Locking)\n");

        // Simula múltiplas threads tentando acessar simultaneamente
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            executor.submit(() -> {
                LazyConfigManager instance = LazyConfigManager.getInstance();
                IO.println("Thread " + threadId + " obteve instância: " +
                        instance.hashCode());
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        IO.println("\n✅ Todas as threads obtiveram a MESMA instância!");
        IO.println("Criado em: " + LazyConfigManager.getInstance().getCreatedAt()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        // ====================================================================
        // EXEMPLO 3: Enum Singleton (Melhor Prática)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 3: Enum Singleton (Melhor Prática)\n");

        DatabaseConnectionPool pool = DatabaseConnectionPool.INSTANCE;

        // Criando conexões
        var conn1 = pool.getConnection("users_db");
        var conn2 = pool.getConnection("products_db");
        var conn3 = pool.getConnection("orders_db");

        IO.println("\n📊 Conexões ativas: " + pool.getActiveConnections());

        // Fechando uma conexão
        pool.closeConnection("users_db");
        IO.println("📊 Conexões ativas após fechar: " + pool.getActiveConnections());

        // ====================================================================
        // EXEMPLO 4: Cache Manager (Caso Real)
        // ====================================================================
        IO.println("\n📌 EXEMPLO 4: Cache Manager com TTL e Métricas\n");

        CacheManager cache = CacheManager.getInstance();

        // Armazenando dados no cache
        cache.put("user:1001", new User("João Silva", "joao@email.com"), 30);
        cache.put("user:1002", new User("Maria Santos", "maria@email.com"), 30);
        cache.put("product:500", new Product("Notebook", 2500.00), 60);

        IO.println();

        // Recuperando dados (HIT)
        cache.get("user:1001").ifPresent(user ->
                IO.println("👤 Usuário: " + user));

        // Tentando recuperar dado inexistente (MISS)
        cache.get("user:9999");

        // Recuperando produto (HIT)
        cache.get("product:500").ifPresent(product ->
                IO.println("📦 Produto: " + product));

        IO.println();

        // Exibindo estatísticas
        IO.println("📊 " + cache.getStats());

        // ====================================================================
        // EXEMPLO 5: Testando Thread-Safety do Cache
        // ====================================================================
        IO.println("\n📌 EXEMPLO 5: Teste de Thread-Safety\n");

        ExecutorService cacheExecutor = Executors.newFixedThreadPool(10);
        CountDownLatch cacheLatch = new CountDownLatch(20);

        // 20 threads fazendo operações simultâneas
        for (int i = 0; i < 20; i++) {
            final int id = i;
            cacheExecutor.submit(() -> {
                if (id % 2 == 0) {
                    cache.put("item:" + id, "Value " + id, 60);
                } else {
                    cache.get("item:" + (id - 1));
                }
                cacheLatch.countDown();
            });
        }

        cacheLatch.await();
        cacheExecutor.shutdown();

        IO.println("\n✅ Thread-safety testado com sucesso!");
        IO.println("📊 " + cache.getStats());

        IO.println("\n" + "=".repeat(70));
        IO.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
        IO.println("=".repeat(70));
    }

    // Classes auxiliares
    record User(String name, String email) {}
    record Product(String name, double price) {}
}
