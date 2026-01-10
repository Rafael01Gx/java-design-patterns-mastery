package com.rafaelmoraes.creational.factory.example02;

import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;
import com.rafaelmoraes.creational.factory.example02.models.PixPayment;

public class PixPaymentFactory extends PaymentFactory {
    private final String pixKey;
    private final String pixKeyType;

    public PixPaymentFactory(String pixKey, String pixKeyType) {
        this.pixKey = pixKey;
        this.pixKeyType = pixKeyType;
    }

    @Override
    protected Payment createPayment() {
        return new PixPayment(pixKey, pixKeyType);
    }
}
