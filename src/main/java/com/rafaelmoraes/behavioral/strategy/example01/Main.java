package com.rafaelmoraes.behavioral.strategy.example01;

public class Main {
    static void main() {

        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("Monitor X", 890.9);
        Item item2 = new Item("Mouse Y", 109.1);
        cart.addItem(item1);
        cart.addItem(item2);

        cart.setPaymentStrategy(
                new CreditCardPayment("João",
                "0123456789",
                "01",
                "2026",
                "123"));

        cart.checkout();


        Item item3 = new Item("Teclado x", 600.0);
        cart.addItem(item3);

        cart.setPaymentStrategy(
                new PaypalPayment("example@example.com",
                        "12345678910",
                        "***********")
                );

        cart.checkout();
    }
}
