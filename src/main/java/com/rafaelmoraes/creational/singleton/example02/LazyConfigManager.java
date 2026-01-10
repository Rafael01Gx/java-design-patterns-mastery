package com.rafaelmoraes.creational.singleton.example02;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SINGLETON COM LAZY INITIALIZATION (Double-Checked Locking)
 * Implementação moderna e eficiente - Thread-safe com lazy loading
 * A instância só é criada quando realmente necessária

 * ✅ Vantagens: Thread-safe, lazy loading, eficiente
 * ❌ Desvantagens: Mais complexo
 */

public class LazyConfigManager {

    // volatile garante visibilidade entre threads
    private static volatile LazyConfigManager instance;

    private final Map<String, String> config = new ConcurrentHashMap<>();
    private final LocalDateTime createdAt;

    private LazyConfigManager() {
        System.out.println("🔧 LazyConfigManager inicializado com Double-Checked Locking!");
        this.createdAt = LocalDateTime.now();
        loadDefaultConfig();
    }

    // Double-Checked Locking Pattern
    public static LazyConfigManager getInstance() {
        // Primeira verificação (sem lock) - performance
        if (instance == null) {
            // Lock apenas se instance for null
            synchronized (LazyConfigManager.class) {
                // Segunda verificação (com lock) - thread-safety
                if (instance == null) {
                    instance = new LazyConfigManager();
                }
            }
        }
        return instance;
    }

    private void loadDefaultConfig() {
        config.put("app.name", "MyLazyApp");
        config.put("app.version", "2.0.0");
        config.put("cache.enabled", "true");
    }

    public String get(String key) {
        return config.get(key);
    }

    public void set(String key, String value) {
        config.put(key, value);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
