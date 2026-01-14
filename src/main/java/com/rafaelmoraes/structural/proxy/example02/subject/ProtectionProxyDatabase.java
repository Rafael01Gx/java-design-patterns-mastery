package com.rafaelmoraes.structural.proxy.example02.subject;

import java.util.List;


/**
 * Proxy que adiciona controle de acesso baseado em permissões
 * Verifica credenciais antes de permitir operações
 */
public class ProtectionProxyDatabase implements DatabaseService {

    private RealDatabaseService realService;
    private String serverAddress;
    private String currentUser;
    private String userRole; // "admin", "user", "guest"

    public ProtectionProxyDatabase(String serverAddress, String user, String role) {
        this.serverAddress = serverAddress;
        this.currentUser = user;
        this.userRole = role;
        IO.println("🛡️  Protection Proxy criado para usuário: " + user + " (role: " + role + ")");
    }

    /**
     * Lazy Initialization - só cria o objeto real quando necessário
     */
    private RealDatabaseService getRealService() {
        if (realService == null) {
            IO.println("\n🔄 Proxy: Inicializando serviço real (lazy loading)...");
            realService = new RealDatabaseService(serverAddress);
        }
        return realService;
    }

    private boolean hasPermission(String operation) {
        boolean allowed = switch (operation) {
            case "connect", "query" -> true; // Todos podem
            case "insert", "update" -> userRole.equals("admin") || userRole.equals("user");
            case "delete" -> userRole.equals("admin");
            default -> false;
        };

        if (!allowed) {
            IO.println("\n🚫 Proxy: ACESSO NEGADO!");
            IO.println("   Usuário: " + currentUser);
            IO.println("   Role: " + userRole);
            IO.println("   Operação: " + operation);
            IO.println("   ❌ Permissão insuficiente");
        }

        return allowed;
    }

    @Override
    public void connect() {
        if (hasPermission("connect")) {
            IO.println("🛡️  Proxy: Verificando permissões... ✅ OK");
            getRealService().connect();
        }
    }

    @Override
    public List<String> executeQuery(String query) {
        if (hasPermission("query")) {
            IO.println("🛡️  Proxy: Verificando permissões... ✅ OK");
            return getRealService().executeQuery(query);
        }
        return List.of();
    }

    @Override
    public boolean insert(String table, String data) {
        if (hasPermission("insert")) {
            IO.println("🛡️  Proxy: Verificando permissões... ✅ OK");
            return getRealService().insert(table, data);
        }
        return false;
    }

    @Override
    public boolean update(String table, String id, String data) {
        if (hasPermission("update")) {
            IO.println("🛡️  Proxy: Verificando permissões... ✅ OK");
            return getRealService().update(table, id, data);
        }
        return false;
    }

    @Override
    public boolean delete(String table, String id) {
        if (hasPermission("delete")) {
            IO.println("🛡️  Proxy: Verificando permissões... ✅ OK");
            return getRealService().delete(table, id);
        }
        return false;
    }

    @Override
    public void disconnect() {
        if (realService != null) {
            realService.disconnect();
        }
    }
}
