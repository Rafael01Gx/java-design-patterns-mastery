package com.rafaelmoraes.creational.factory.example02;

import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;
import com.rafaelmoraes.creational.factory.example02.models.PayPalPayment;

public class PayPalPaymentFactory extends PaymentFactory{

    private final String email;
    private final String token;

    public PayPalPaymentFactory(String email, String token) {
        this.email = email;
        this.token = token;
    }

    @Override
    protected Payment createPayment() {
        return new PayPalPayment(email, token);
    }
}
