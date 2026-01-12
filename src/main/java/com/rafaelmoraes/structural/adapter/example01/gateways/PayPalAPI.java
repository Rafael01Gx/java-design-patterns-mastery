package com.rafaelmoraes.structural.adapter.example01.gateways;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PayPalAPI {
    /**
     * Método específico do PayPal
     * Parâmetros e retorno diferentes do esperado
     */
    public Map<String, Object> makePayment(String email, double amountInDollars) {
        IO.println("💰 PayPal API: Processando pagamento");
        IO.println("   Email: " + email);
        IO.println("   Valor: $" + amountInDollars);

        // Simula resposta do PayPal
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("paypal_transaction_id", "PP-" + UUID.randomUUID().toString().substring(0, 8));
        response.put("status_code", 200);
        response.put("message", "Payment successful");

        IO.println("   ✅ PayPal: Pagamento aprovado");
        return response;
    }

    public Map<String, Object> getTransactionStatus(String paypalTransactionId) {
        IO.println("🔍 PayPal API: Consultando status - " + paypalTransactionId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "completed");
        response.put("transaction_id", paypalTransactionId);

        return response;
    }

    public boolean cancelTransaction(String paypalTransactionId) {
        IO.println("↩️  PayPal API: Cancelando transação - " + paypalTransactionId);
        return true;
    }
}
