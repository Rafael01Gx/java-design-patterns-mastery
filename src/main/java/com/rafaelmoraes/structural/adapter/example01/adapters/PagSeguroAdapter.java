package com.rafaelmoraes.structural.adapter.example01.adapters;

import com.rafaelmoraes.structural.adapter.example01.processor.PaymentProcessor;
import com.rafaelmoraes.structural.adapter.example01.gateways.PagSeguroSDK;

public class PagSeguroAdapter implements PaymentProcessor {

    private final PagSeguroSDK pagSeguroSDK;

    public PagSeguroAdapter() {
        this.pagSeguroSDK = new PagSeguroSDK();
        System.out.println("🔌 PagSeguro Adapter inicializado");
    }

    @Override
    public String processPayment(double amount, String customerEmail) {
        // PagSeguro já trabalha em reais, não precisa converter
        PagSeguroSDK.Transaction transacao = pagSeguroSDK.efetuarPagamento(amount, customerEmail);
        return transacao.codigo;
    }

    @Override
    public String checkStatus(String transactionId) {
        String status = pagSeguroSDK.consultarStatus(transactionId);

        // Converte status do PagSeguro para padrão unificado
        return switch (status) {
            case "APROVADO" -> "completed";
            case "PENDENTE" -> "processing";
            case "CANCELADO" -> "failed";
            default -> "unknown";
        };
    }

    @Override
    public boolean refund(String transactionId) {
        return pagSeguroSDK.estornar(transactionId);
    }
}
