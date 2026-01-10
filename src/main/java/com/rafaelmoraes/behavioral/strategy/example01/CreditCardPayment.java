package com.rafaelmoraes.behavioral.strategy.example01;

public class CreditCardPayment implements PaymentStrategy {

    private String name;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;

    public CreditCardPayment(String name, String cardNumber, String expiryYear, String expiryMonth, String cvv) {
        this.name = name;
        this.cvv = cvv;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        IO.println("R$"+amount+ " payment made via credit card");
    }
}
