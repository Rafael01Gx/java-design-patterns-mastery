package com.rafaelmoraes.creational.factory.example02;

import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;
import com.rafaelmoraes.creational.factory.example02.models.CreditCardPayment;

public class CreditCardPaymentFactory extends PaymentFactory{
    private final String cardNumber;
    private final String cardHolder;
    private final String expiryDate;
    private final String cvv;
    private final int installments;

    public CreditCardPaymentFactory(String cardNumber, String cardHolder,
                                    String expiryDate, String cvv, int installments) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.installments = installments;
    }

    @Override
    protected Payment createPayment() {
        return new CreditCardPayment(cardNumber, cardHolder, expiryDate, cvv, installments);
    }
}
