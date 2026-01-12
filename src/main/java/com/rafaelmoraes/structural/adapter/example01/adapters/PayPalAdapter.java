package com.rafaelmoraes.structural.adapter.example01.adapters;

import com.rafaelmoraes.structural.adapter.example01.processor.PaymentProcessor;
import com.rafaelmoraes.structural.adapter.example01.gateways.PayPalAPI;

import java.util.Map;

public class PayPalAdapter implements PaymentProcessor {

    private final PayPalAPI paypalAPI;

    public PayPalAdapter() {
        this.paypalAPI = new PayPalAPI();
        System.out.println("🔌 PayPal Adapter inicializado");
    }

    @Override
    public String processPayment(double amount, String customerEmail) {
        // Converte de reais para dólares (simulado: taxa 1:5)
        double amountInDollars = amount / 5.0;

        // Chama API do PayPal com interface adaptada
        Map<String, Object> response = paypalAPI.makePayment(customerEmail, amountInDollars);

        // Converte resposta do PayPal para formato esperado
        if ((boolean) response.get("success")) {
            return (String) response.get("paypal_transaction_id");
        }
        return null;
    }

    @Override
    public String checkStatus(String transactionId) {
        Map<String, Object> response = paypalAPI.getTransactionStatus(transactionId);
        return (String) response.get("status");
    }

    @Override
    public boolean refund(String transactionId) {
        return paypalAPI.cancelTransaction(transactionId);
    }
}