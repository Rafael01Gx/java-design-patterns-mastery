package com.rafaelmoraes.creational.factory.example01;

public class MySQLFactory implements ConnectionFactory{
    @Override
    public Connection createConnection() {
        return new MySQLConnection();
    }
}
