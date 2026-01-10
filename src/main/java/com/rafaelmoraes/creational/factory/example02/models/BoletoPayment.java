package com.rafaelmoraes.creational.factory.example02.models;

import com.rafaelmoraes.creational.factory.example02.dto.PaymentResult;
import com.rafaelmoraes.creational.factory.example02.dto.Transaction;
import com.rafaelmoraes.creational.factory.example02.enums.PaymentStatus;
import com.rafaelmoraes.creational.factory.example02.enums.PaymentType;
import com.rafaelmoraes.creational.factory.example02.interfaces.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BoletoPayment implements Payment {
    private final String bankCode;
    private final LocalDateTime dueDate;

    public BoletoPayment(String bankCode, LocalDateTime dueDate) {
        this.bankCode = bankCode;
        this.dueDate = dueDate;
    }

    @Override
    public CompletableFuture<PaymentResult> process(Transaction transaction) {
        return CompletableFuture.supplyAsync(() -> {
            IO.println("\n📄 Gerando Boleto Bancário...");
            IO.println("   Banco: " + bankCode);
            IO.println("   Vencimento: " + dueDate.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            IO.println("   Valor: R$ " + transaction.amount());

            String boletoId = "BOL-" + UUID.randomUUID().toString().substring(0, 8);
            String barCode = generateBarCode();

            IO.println("   📊 Código de barras: " + barCode);
            IO.println("   ✅ Boleto gerado! ID: " + boletoId);
            IO.println("   ⏳ Aguardando pagamento até " +
                    dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            Map<String, String> details = Map.of(
                    "barcode", barCode,
                    "dueDate", dueDate.toString(),
                    "bankCode", bankCode
            );

            return PaymentResult.pending(boletoId, details);
        });
    }

    @Override
    public boolean validate() {
        return dueDate.isAfter(LocalDateTime.now());
    }

    @Override
    public CompletableFuture<PaymentResult> refund(String transactionId) {
        return CompletableFuture.supplyAsync(() -> {
            IO.println("💸 Cancelando boleto: " + transactionId);
            return new PaymentResult(
                    transactionId,
                    PaymentStatus.REFUNDED,
                    BigDecimal.ZERO,
                    LocalDateTime.now(),
                    "Boleto cancelado",
                    Map.of()
            );
        });
    }

    @Override
    public PaymentType getType() {
        return PaymentType.BOLETO;
    }

    @Override
    public String getDetails() {
        return "Banco " + bankCode + " - Venc: " +
                dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String generateBarCode() {
        Random random = new Random();
        StringBuilder barCode = new StringBuilder();
        for (int i = 0; i < 47; i++) {
            barCode.append(random.nextInt(10));
        }
        return barCode.toString();
    }
}
