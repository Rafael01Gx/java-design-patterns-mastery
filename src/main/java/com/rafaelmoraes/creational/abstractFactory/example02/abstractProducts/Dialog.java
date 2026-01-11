package com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts;

public interface Dialog {
    void render();
    void show(String title, String message);
    void close();
    String getStyle();
}
