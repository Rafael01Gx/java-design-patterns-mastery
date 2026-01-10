package com.rafaelmoraes.creational.factory.example02;

import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;
import com.rafaelmoraes.creational.factory.example02.models.BoletoPayment;

import java.time.LocalDateTime;

public class BoletoPaymentFactory extends PaymentFactory{
    private final String bankCode;
    private final LocalDateTime dueDate;

    public BoletoPaymentFactory(String bankCode, LocalDateTime dueDate) {
        this.bankCode = bankCode;
        this.dueDate = dueDate;
    }

    @Override
    protected Payment createPayment() {
        return new BoletoPayment(bankCode, dueDate);
    }
}
