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

public class PixPayment implements Payment {
    private final String pixKey;
    private final String pixKeyType; // CPF, Email, Telefone, Aleatória

    public PixPayment(String pixKey, String pixKeyType) {
        this.pixKey = pixKey;
        this.pixKeyType = pixKeyType;
    }

    @Override
    public CompletableFuture<PaymentResult> process(Transaction transaction) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                IO.println("\n⚡ Processando PIX...");
                IO.println("   Chave: " + pixKey + " (" + pixKeyType + ")");
                IO.println("   Valor: R$ " + transaction.amount());

                // Simula tempo de processamento PIX (rápido)
                Thread.sleep(500);

                String txId = "PIX-" + UUID.randomUUID().toString().substring(0, 8);
                String qrCode = generateQRCode();

                IO.println("   📱 QR Code gerado: " + qrCode);
                IO.println("   ⏳ Aguardando pagamento...");

                // Simula espera de confirmação
                Thread.sleep(3000);

                IO.println("   ✅ PIX recebido! ID: " + txId);

                return PaymentResult.success(txId, transaction.amount());

            } catch (InterruptedException e) {
                return PaymentResult.rejected("Erro no processamento PIX");
            }
        });
    }

    @Override
    public boolean validate() {
        return pixKey != null && !pixKey.isEmpty();
    }

    @Override
    public CompletableFuture<PaymentResult> refund(String transactionId) {
        return CompletableFuture.supplyAsync(() -> {
            IO.println("💸 Devolvendo PIX: " + transactionId);
            return new PaymentResult(
                    transactionId,
                    PaymentStatus.REFUNDED,
                    BigDecimal.ZERO,
                    LocalDateTime.now(),
                    "Devolução PIX processada",
                    Map.of()
            );
        });
    }

    @Override
    public PaymentType getType() {
        return PaymentType.PIX;
    }

    @Override
    public String getDetails() {
        return pixKeyType + ": " + maskPixKey(pixKey);
    }

    private String generateQRCode() {
        return "00020126" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }

    private String maskPixKey(String key) {
        if (key.contains("@")) {
            String[] parts = key.split("@");
            return parts[0].charAt(0) + "***@" + parts[1];
        }
        return key.substring(0, 3) + "***" + key.substring(key.length() - 3);
    }
}
