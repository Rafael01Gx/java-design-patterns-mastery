package com.rafaelmoraes.structural.adapter.example01;

import com.rafaelmoraes.structural.adapter.example01.processor.PaymentProcessor;

public class EcommerceSystem {

    private PaymentProcessor paymentProcessor;

    public void setPaymentProcessor(PaymentProcessor processor) {
        this.paymentProcessor = processor;
    }

    /**
     * Processa uma compra
     * O mesmo código funciona com qualquer adapter!
     */
    public void processOrder(String customerEmail, double orderAmount) {
        IO.println("\n" + "=".repeat(60));
        IO.println("🛒 PROCESSANDO PEDIDO");
        IO.println("=".repeat(60));
        IO.println("Cliente: " + customerEmail);
        IO.println("Valor: R$ " + String.format("%.2f", orderAmount));
        IO.println();

        // Processa pagamento (interface unificada)
        String transactionId = paymentProcessor.processPayment(orderAmount, customerEmail);

        if (transactionId != null) {
            IO.println("\n✅ Pedido processado com sucesso!");
            IO.println("ID da Transação: " + transactionId);

            // Verifica status
            String status = paymentProcessor.checkStatus(transactionId);
            IO.println("Status: " + status);
        } else {
            IO.println("\n❌ Falha ao processar pagamento");
        }

        IO.println("=".repeat(60));
    }

    /**
     * Processa um reembolso
     */
    public void processRefund(String transactionId) {
        IO.println("\n" + "=".repeat(60));
        IO.println("💸 PROCESSANDO REEMBOLSO");
        IO.println("=".repeat(60));
        IO.println("Transação: " + transactionId);
        IO.println();

        boolean success = paymentProcessor.refund(transactionId);

        if (success) {
            IO.println("\n✅ Reembolso processado com sucesso!");
        } else {
            IO.println("\n❌ Falha ao processar reembolso");
        }

        IO.println("=".repeat(60));
    }
}