package com.rafaelmoraes.creational.factory.example02;

import com.rafaelmoraes.creational.factory.example02.dto.PaymentResult;
import com.rafaelmoraes.creational.factory.example02.dto.Transaction;
import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;

import java.util.concurrent.CompletableFuture;

public abstract class PaymentFactory {
    /**
     * Factory Method - Método abstrato que será implementado pelas subclasses
     * Este é o CORE do padrão Factory Method!
     */

    protected abstract Payment createPayment();

    /**
     * Template Method que usa o Factory Method
     * Define o fluxo de processamento de pagamento
     */
    public CompletableFuture<PaymentResult> processPayment(Transaction transaction) {
        Payment payment = createPayment();

        if (!payment.validate()) {
            return CompletableFuture.completedFuture(
                    PaymentResult.rejected("Dados de pagamento inválidos")
            );
        }

        IO.println("\n" + "=".repeat(60));
        IO.println(payment.getType().getIcon() + " PROCESSANDO: " +
                payment.getType().getDisplayName());
        IO.println("Detalhes: " + payment.getDetails());
        IO.println("Transação: " + transaction.id());
        IO.println("Valor: R$ " + transaction.amount());
        IO.println("=".repeat(60));

        return payment.process(transaction);
    }
}
