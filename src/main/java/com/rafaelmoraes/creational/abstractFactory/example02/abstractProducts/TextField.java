package com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts;

public interface TextField {
    void render();
    void setValue(String value);
    String getValue();
    String getStyle();
    void setPlaceholder(String placeholder);
}
