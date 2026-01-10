package com.rafaelmoraes.creational.singleton.example02;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SINGLETON MODERNO - SISTEMA DE CACHE COM MÉTRICAS
 * Exemplo avançado: Sistema de cache global com estatísticas
 * Usado em aplicações reais para melhorar performance
 */
public class CacheManager {

    private static volatile CacheManager instance;

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final AtomicInteger hits = new AtomicInteger(0);
    private final AtomicInteger misses = new AtomicInteger(0);
    private final long maxCacheSize = 1000;

    private CacheManager() {
        System.out.println("🔧 CacheManager inicializado!");
        startCleanupTask();
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    /**
     * Armazena valor no cache com TTL (Time To Live)
     */
    public void put(String key, Object value, long ttlSeconds) {
        if (cache.size() >= maxCacheSize) {
            evictOldest();
        }

        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(ttlSeconds);
        cache.put(key, new CacheEntry(value, expiresAt));
        System.out.println("📦 Cache armazenado: " + key + " (expira em " + ttlSeconds + "s)");
    }

    /**
     * Recupera valor do cache
     */
    public Optional<Object> get(String key) {
        CacheEntry entry = cache.get(key);

        if (entry == null) {
            misses.incrementAndGet();
            System.out.println("❌ Cache MISS: " + key);
            return Optional.empty();
        }

        if (entry.isExpired()) {
            cache.remove(key);
            misses.incrementAndGet();
            System.out.println("⏰ Cache EXPIRADO: " + key);
            return Optional.empty();
        }

        hits.incrementAndGet();
        System.out.println("✅ Cache HIT: " + key);
        return Optional.of(entry.value());
    }

    /**
     * Remove entrada mais antiga
     */
    private void evictOldest() {
        cache.entrySet().stream()
                .min(Comparator.comparing(e -> e.getValue().createdAt()))
                .ifPresent(entry -> {
                    cache.remove(entry.getKey());
                    System.out.println("🗑️  Cache removido (eviction): " + entry.getKey());
                });
    }

    /**
     * Task de limpeza automática (executada periodicamente)
     */
    private void startCleanupTask() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            cache.entrySet().removeIf(entry -> {
                if (entry.getValue().isExpired()) {
                    System.out.println("🧹 Limpeza automática: " + entry.getKey());
                    return true;
                }
                return false;
            });
        }, 10, 10, TimeUnit.SECONDS);
    }

    /**
     * Retorna estatísticas do cache
     */
    public CacheStats getStats() {
        int totalRequests = hits.get() + misses.get();
        double hitRate = totalRequests == 0 ? 0 : (hits.get() * 100.0) / totalRequests;

        return new CacheStats(
                cache.size(),
                hits.get(),
                misses.get(),
                hitRate
        );
    }

    // Record para entrada do cache
    record CacheEntry(Object value, LocalDateTime expiresAt, LocalDateTime createdAt) {
        CacheEntry(Object value, LocalDateTime expiresAt) {
            this(value, expiresAt, LocalDateTime.now());
        }

        boolean isExpired() {
            return LocalDateTime.now().isAfter(expiresAt);
        }
    }

    // Record para estatísticas
    record CacheStats(int size, int hits, int misses, double hitRate) {
        @Override
        public String toString() {
            return String.format(
                    "Cache Stats: [Size: %d | Hits: %d | Misses: %d | Hit Rate: %.2f%%]",
                    size, hits, misses, hitRate
            );
        }
    }
}
