package com.rafaelmoraes.structural.proxy.example02.subject;

import java.util.List;

public interface DatabaseService {
    /**
     * Conecta ao banco de dados
     */
    void connect();

    /**
     * Executa uma query
     */
    List<String> executeQuery(String query);

    /**
     * Insere dados
     */
    boolean insert(String table, String data);

    /**
     * Atualiza dados
     */
    boolean update(String table, String id, String data);

    /**
     * Deleta dados
     */
    boolean delete(String table, String id);

    /**
     * Desconecta do banco
     */
    void disconnect();
}
