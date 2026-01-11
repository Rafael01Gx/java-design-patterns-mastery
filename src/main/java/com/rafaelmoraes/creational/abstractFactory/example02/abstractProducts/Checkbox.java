package com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts;

public interface Checkbox {
    void render();
    void setChecked(boolean checked);
    boolean isChecked();
    String getStyle();
}
