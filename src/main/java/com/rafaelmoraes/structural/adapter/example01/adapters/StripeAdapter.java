package com.rafaelmoraes.structural.adapter.example01.adapters;

import com.rafaelmoraes.structural.adapter.example01.processor.PaymentProcessor;
import com.rafaelmoraes.structural.adapter.example01.gateways.StripeGateway;

import java.util.HashMap;
import java.util.Map;

public class StripeAdapter implements PaymentProcessor {

    private final StripeGateway stripeGateway;
    private final Map<String, Integer> transactionAmounts; // Armazena valores para refund

    public StripeAdapter() {
        this.stripeGateway = new StripeGateway();
        this.transactionAmounts = new HashMap<>();
        System.out.println("🔌 Stripe Adapter inicializado");
    }

    @Override
    public String processPayment(double amount, String customerEmail) {
        // Converte reais para centavos (Stripe trabalha com centavos)
        int amountInCents = (int) (amount * 100);

        // Chama API do Stripe
        String chargeId = stripeGateway.charge(amountInCents, customerEmail, "BRL");

        // Armazena valor para possível refund futuro
        transactionAmounts.put(chargeId, amountInCents);

        return chargeId;
    }

    @Override
    public String checkStatus(String transactionId) {
        String stripeStatus = stripeGateway.retrieveChargeStatus(transactionId);

        // Converte status do Stripe para padrão unificado
        return switch (stripeStatus) {
            case "succeeded" -> "completed";
            case "pending" -> "processing";
            case "failed" -> "failed";
            default -> "unknown";
        };
    }

    @Override
    public boolean refund(String transactionId) {
        Integer amount = transactionAmounts.get(transactionId);
        if (amount != null) {
            return stripeGateway.refundCharge(transactionId, amount);
        }
        return false;
    }
}