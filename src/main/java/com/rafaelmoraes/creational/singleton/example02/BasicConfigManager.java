package com.rafaelmoraes.creational.singleton.example02;

import java.util.HashMap;
import java.util.Map;

/**
 *  SINGLETON BÁSICO (Eager Initialization)
 * Implementação mais simples - Thread-safe por padrão
 * A instância é criada no carregamento da classe

 * ✅ Vantagens: Simples, thread-safe
 * ❌ Desvantagens: Instância criada mesmo se nunca for usada
 */

public class BasicConfigManager {
    // Instância criada imediatamente
    private static final BasicConfigManager INSTANCE = new BasicConfigManager();

    private final Map<String, String> config = new HashMap<>();

    // Construtor privado - impede new BasicConfigManager()
    private BasicConfigManager() {
        System.out.println("🔧 BasicConfigManager inicializado!");
        loadDefaultConfig();
    }

    public static BasicConfigManager getInstance() {
        return INSTANCE;
    }

    private void loadDefaultConfig() {
        config.put("app.name", "MyApp");
        config.put("app.version", "1.0.0");
        config.put("database.url", "jdbc:postgresql://localhost:5432/mydb");
    }

    public String get(String key) {
        return config.get(key);
    }

    public void set(String key, String value) {
        config.put(key, value);
    }
}
