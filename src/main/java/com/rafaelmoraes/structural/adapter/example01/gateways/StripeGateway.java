package com.rafaelmoraes.structural.adapter.example01.gateways;

import java.util.UUID;

public class StripeGateway {
    /**
     * Método específico do Stripe
     */
    public String charge(int amountInCents, String customerEmail, String currency) {
        IO.println("💳 Stripe Gateway: Criando cobrança");
        IO.println("   Email: " + customerEmail);
        IO.println("   Valor: " + amountInCents + " cents (" + currency + ")");

        String chargeId = "ch_" + UUID.randomUUID().toString().substring(0, 12);

        IO.println("   ✅ Stripe: Cobrança criada - ID: " + chargeId);
        return chargeId;
    }

    public String retrieveChargeStatus(String chargeId) {
        IO.println("🔍 Stripe Gateway: Recuperando status - " + chargeId);
        return "succeeded";
    }

    public boolean refundCharge(String chargeId, int amountInCents) {
        IO.println("↩️  Stripe Gateway: Reembolsando - " + chargeId);
        IO.println("   Valor: " + amountInCents + " cents");
        return true;
    }
}
