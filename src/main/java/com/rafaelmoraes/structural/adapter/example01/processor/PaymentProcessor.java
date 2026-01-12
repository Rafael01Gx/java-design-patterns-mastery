package com.rafaelmoraes.structural.adapter.example01.processor;

public interface PaymentProcessor {
    /**
     * Processa um pagamento
     * @param amount Valor em reais
     * @param customerEmail Email do cliente
     * @return ID da transação se sucesso, null se falhar
     */
    String processPayment(double amount, String customerEmail);

    /**
     * Verifica status de um pagamento
     */
    String checkStatus(String transactionId);

    /**
     * Cancela/estorna um pagamento
     */
    boolean refund(String transactionId);
}
