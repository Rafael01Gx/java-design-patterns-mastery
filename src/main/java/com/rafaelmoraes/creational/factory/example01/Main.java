package com.rafaelmoraes.creational.factory.example01;

public class Main {
    static void main(){
        ConnectionFactory factory = new MySQLFactory();
        Connection connection = factory.createConnection();
    }
}
