package com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts;

import com.rafaelmoraes.creational.abstractFactory.example02.dto.MenuItem;

import java.util.List;

public interface Menu {
    void render();
    void addItem(String label, String action);
    List<MenuItem> getItems();
    String getStyle();
}
