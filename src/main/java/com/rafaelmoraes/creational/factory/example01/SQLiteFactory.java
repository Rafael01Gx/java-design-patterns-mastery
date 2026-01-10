package com.rafaelmoraes.creational.factory.example01;

public class SQLiteFactory implements ConnectionFactory{
    @Override
    public Connection createConnection() {
        return new SQLiteConnection();
    }
}
