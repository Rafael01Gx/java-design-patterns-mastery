package com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts;

public interface Button {
    void render();
    void onClick(String action);
    String getStyle();
    void setEnabled(boolean enabled);
}
