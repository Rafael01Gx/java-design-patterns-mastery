package com.rafaelmoraes.creational.abstractFactory.example02.concreteProducts.mac;

import com.rafaelmoraes.creational.abstractFactory.example02.abstractProducts.Menu;
import com.rafaelmoraes.creational.abstractFactory.example02.dto.MenuItem;
import com.rafaelmoraes.creational.abstractFactory.example02.enums.Theme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MacMenu implements Menu {

    private final Theme theme;
    private final List<MenuItem> items = new ArrayList<>();

    public MacMenu(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void render() {
        IO.println("  🍎 [macOS Menu] Menu Bar style | Theme: " + theme.getName());
        items.forEach(item -> IO.println("     ‣ " + item.label()));
    }

    @Override
    public void addItem(String label, String action) {
        items.add(new MenuItem(label, action));
    }

    @Override
    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public String getStyle() {
        return "macOS native menu bar with translucency";
    }
}