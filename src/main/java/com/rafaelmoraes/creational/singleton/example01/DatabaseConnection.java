package com.rafaelmoraes.creational.singleton.example01;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private Connection connection;

    private DatabaseConnection(){
        // ...
    }


    public static synchronized DatabaseConnection getInstance(){
        if(instance==null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

}
