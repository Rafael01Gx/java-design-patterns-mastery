package com.rafaelmoraes.creational.factory.example02.models;

import com.rafaelmoraes.creational.factory.example02.dto.PaymentResult;
import com.rafaelmoraes.creational.factory.example02.dto.Transaction;
import com.rafaelmoraes.creational.factory.example02.enums.PaymentStatus;
import com.rafaelmoraes.creational.factory.example02.enums.PaymentType;
import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CreditCardPayment implements Payment {
    private final String cardNumber;
    private final String cardHolder;
    private final String expiryDate;
    private final String cvv;
    private final int installments;

    public CreditCardPayment(String cardNumber, String cardHolder,
                             String expiryDate, String cvv, int installments) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.installments = installments;
    }

    @Override
    public CompletableFuture<PaymentResult> process(Transaction transaction) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                IO.println("\n💳 Processando Cartão de Crédito...");
                IO.println("   Titular: " + maskCardHolder(cardHolder));
                IO.println("   Cartão: " + maskCardNumber(cardNumber));
                IO.println("   Parcelas: " + installments + "x");

                // Simula comunicação com gateway de pagamento
                Thread.sleep(2000);

                // Simula aprovação (90% de chance)
                if (new Random().nextInt(100) < 90) {
                    String txId = "CC-" + UUID.randomUUID().toString().substring(0, 8);
                    IO.println("   ✅ Pagamento aprovado! ID: " + txId);
                    return PaymentResult.success(txId, transaction.amount());
                } else {
                    IO.println("   ❌ Pagamento rejeitado - Saldo insuficiente");
                    return PaymentResult.rejected("Saldo insuficiente");
                }

            } catch (InterruptedException e) {
                return PaymentResult.rejected("Erro no processamento");
            }
        });
    }

    @Override
    public boolean validate() {
        return cardNumber.length() == 16 &&
                cvv.length() == 3 &&
                installments > 0 && installments <= 12;
    }

    @Override
    public CompletableFuture<PaymentResult> refund(String transactionId) {
        return CompletableFuture.supplyAsync(() -> {
            IO.println("💸 Estornando pagamento: " + transactionId);
            return new PaymentResult(
                    transactionId,
                    PaymentStatus.REFUNDED,
                    BigDecimal.ZERO,
                    LocalDateTime.now(),
                    "Estorno processado",
                    Map.of()
            );
        });
    }

    @Override
    public PaymentType getType() {
        return PaymentType.CREDIT_CARD;
    }

    @Override
    public String getDetails() {
        return maskCardNumber(cardNumber) + " - " + installments + "x";
    }

    private String maskCardNumber(String number) {
        return "**** **** **** " + number.substring(12);
    }

    private String maskCardHolder(String name) {
        String[] parts = name.split(" ");
        return parts[0] + " " + parts[parts.length - 1].charAt(0) + "***";
    }
}
