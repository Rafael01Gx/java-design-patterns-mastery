package com.rafaelmoraes.structural.proxy.example02.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Proxy que adiciona cache para melhorar performance
 * Armazena resultados de queries e evita chamadas repetidas
 */
public class CachingProxyDatabase implements DatabaseService {

    private RealDatabaseService realService;
    private String serverAddress;
    private Map<String, List<String>> queryCache;
    private Map<String, Long> cacheTimestamps;
    private long cacheTTL = 5000; // 5 segundos

    public CachingProxyDatabase(String serverAddress) {
        this.serverAddress = serverAddress;
        this.queryCache = new HashMap<>();
        this.cacheTimestamps = new HashMap<>();
        IO.println("💾 Caching Proxy criado");
    }

    private RealDatabaseService getRealService() {
        if (realService == null) {
            IO.println("\n🔄 Proxy: Inicializando serviço real...");
            realService = new RealDatabaseService(serverAddress);
        }
        return realService;
    }

    private boolean isCacheValid(String key) {
        if (!queryCache.containsKey(key)) {
            return false;
        }

        long timestamp = cacheTimestamps.get(key);
        long now = System.currentTimeMillis();
        return (now - timestamp) < cacheTTL;
    }

    @Override
    public void connect() {
        getRealService().connect();
    }

    @Override
    public List<String> executeQuery(String query) {
        // Verifica cache primeiro
        if (isCacheValid(query)) {
            IO.println("\n💾 Proxy: CACHE HIT! Retornando do cache");
            IO.println("   Query: " + query);
            return new ArrayList<>(queryCache.get(query));
        }

        // Cache miss - executa query real
        IO.println("\n❌ Proxy: CACHE MISS - Executando query real");
        List<String> results = getRealService().executeQuery(query);

        // Armazena no cache
        queryCache.put(query, new ArrayList<>(results));
        cacheTimestamps.put(query, System.currentTimeMillis());
        IO.println("   💾 Resultado armazenado no cache");

        return results;
    }

    @Override
    public boolean insert(String table, String data) {
        // Invalida cache da tabela
        invalidateCache(table);
        return getRealService().insert(table, data);
    }

    @Override
    public boolean update(String table, String id, String data) {
        invalidateCache(table);
        return getRealService().update(table, id, data);
    }

    @Override
    public boolean delete(String table, String id) {
        invalidateCache(table);
        return getRealService().delete(table, id);
    }

    @Override
    public void disconnect() {
        if (realService != null) {
            realService.disconnect();
        }
    }

    private void invalidateCache(String table) {
        queryCache.keySet().removeIf(key -> key.toLowerCase().contains(table.toLowerCase()));
        IO.println("   🗑️  Cache invalidado para tabela: " + table);
    }

    public void showCacheStats() {
        IO.println("\n📊 ESTATÍSTICAS DO CACHE:");
        IO.println("   Queries em cache: " + queryCache.size());
        IO.println("   TTL: " + cacheTTL + "ms");
    }
}
