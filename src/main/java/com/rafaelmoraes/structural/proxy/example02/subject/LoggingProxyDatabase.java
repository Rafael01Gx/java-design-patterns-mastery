package com.rafaelmoraes.structural.proxy.example02.subject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoggingProxyDatabase implements DatabaseService {

    private RealDatabaseService realService;
    private String serverAddress;
    private List<String> operationLog;
    private long operationCount = 0;

    public LoggingProxyDatabase(String serverAddress) {
        this.serverAddress = serverAddress;
        this.operationLog = new ArrayList<>();
        IO.println("📝 Logging Proxy criado");
    }

    private RealDatabaseService getRealService() {
        if (realService == null) {
            realService = new RealDatabaseService(serverAddress);
        }
        return realService;
    }

    private void log(String operation, String details) {
        operationCount++;
        String timestamp = new Date().toString();
        String logEntry = String.format("[%d] %s - %s: %s",
                operationCount, timestamp, operation, details);
        operationLog.add(logEntry);
        IO.println("📝 LOG: " + logEntry);
    }

    @Override
    public void connect() {
        log("CONNECT", serverAddress);
        getRealService().connect();
    }

    @Override
    public List<String> executeQuery(String query) {
        log("QUERY", query);
        List<String> results = getRealService().executeQuery(query);
        log("QUERY_RESULT", results.size() + " rows returned");
        return results;
    }

    @Override
    public boolean insert(String table, String data) {
        log("INSERT", "table=" + table + ", data=" + data);
        boolean result = getRealService().insert(table, data);
        log("INSERT_RESULT", result ? "SUCCESS" : "FAILED");
        return result;
    }

    @Override
    public boolean update(String table, String id, String data) {
        log("UPDATE", "table=" + table + ", id=" + id);
        boolean result = getRealService().update(table, id, data);
        log("UPDATE_RESULT", result ? "SUCCESS" : "FAILED");
        return result;
    }

    @Override
    public boolean delete(String table, String id) {
        log("DELETE", "table=" + table + ", id=" + id);
        boolean result = getRealService().delete(table, id);
        log("DELETE_RESULT", result ? "SUCCESS" : "FAILED");
        return result;
    }

    @Override
    public void disconnect() {
        log("DISCONNECT", serverAddress);
        if (realService != null) {
            realService.disconnect();
        }
    }

    public void showLogs() {
        IO.println("\n📋 HISTÓRICO DE OPERAÇÕES:");
        IO.println("─".repeat(60));
        operationLog.forEach(IO::println);
        IO.println("─".repeat(60));
        IO.println("Total de operações: " + operationCount);
    }
}