package com.rafaelmoraes.creational.factory.example02.models;

import com.rafaelmoraes.creational.factory.example02.dto.PaymentResult;
import com.rafaelmoraes.creational.factory.example02.dto.Transaction;
import com.rafaelmoraes.creational.factory.example02.enums.PaymentStatus;
import com.rafaelmoraes.creational.factory.example02.enums.PaymentType;
import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PayPalPayment implements Payment {
    private final String email;
    private final String token;

    public PayPalPayment(String email, String token) {
        this.email = email;
        this.token = token;
    }

    @Override
    public CompletableFuture<PaymentResult> process(Transaction transaction) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                IO.println("\n🅿️  Processando PayPal...");
                IO.println("   Email: " + maskEmail(email));
                IO.println("   Valor: $" + transaction.amount());

                Thread.sleep(1500);

                String txId = "PP-" + UUID.randomUUID().toString().substring(0, 8);
                IO.println("   ✅ Pagamento aprovado! ID: " + txId);

                return PaymentResult.success(txId, transaction.amount());

            } catch (InterruptedException e) {
                return PaymentResult.rejected("Erro no PayPal");
            }
        });
    }

    @Override
    public boolean validate() {
        return email.contains("@") && token != null && token.length() > 10;
    }

    @Override
    public CompletableFuture<PaymentResult> refund(String transactionId) {
        return CompletableFuture.supplyAsync(() -> {
            IO.println("💸 Estornando via PayPal: " + transactionId);
            return new PaymentResult(
                    transactionId,
                    PaymentStatus.REFUNDED,
                    BigDecimal.ZERO,
                    LocalDateTime.now(),
                    "Estorno PayPal processado",
                    Map.of()
            );
        });
    }

    @Override
    public PaymentType getType() {
        return PaymentType.PAYPAL;
    }

    @Override
    public String getDetails() {
        return maskEmail(email);
    }

    private String maskEmail(String email) {
        String[] parts = email.split("@");
        return parts[0].charAt(0) + "***@" + parts[1];
    }
}
