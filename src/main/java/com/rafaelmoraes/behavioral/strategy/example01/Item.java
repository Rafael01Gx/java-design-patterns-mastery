package com.rafaelmoraes.behavioral.strategy.example01;

public class Item {

    private String itemName;
    private double price;

    public Item(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}
