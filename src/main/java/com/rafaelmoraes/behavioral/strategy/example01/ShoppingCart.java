package com.rafaelmoraes.behavioral.strategy.example01;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<Item> items ;
    private PaymentStrategy paymentStrategy;

    public ShoppingCart(){
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotal(){
        double total = 0;
        for(Item item : items){
            total += item.getPrice();
        }
        return total;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(){
        double amount = calculateTotal();
        paymentStrategy.pay(amount);
        items.clear();
    }
}
