package com.rafaelmoraes.behavioral.strategy.example01;

public class PaypalPayment implements PaymentStrategy {

    private String email;
    private String cpf;
    private String password;

    public PaypalPayment(String email, String cpf, String password) {
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        IO.println("R$"+amount+ " payment made via Paypal");
    }
}
