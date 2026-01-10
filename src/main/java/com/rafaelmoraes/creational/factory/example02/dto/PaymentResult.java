package com.rafaelmoraes.creational.factory.example02.dto;

import com.rafaelmoraes.creational.factory.example02.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public record PaymentResult(
        String transactionId,
        PaymentStatus status,
        BigDecimal amount,
        LocalDateTime processedAt,
        String message,
        Map<String, String> metadata
) {
    public static PaymentResult success(String transactionId, BigDecimal amount) {
        return new PaymentResult(
                transactionId,
                PaymentStatus.APPROVED,
                amount,
                LocalDateTime.now(),
                "Pagamento aprovado com sucesso",
                Map.of()
        );
    }

    public static PaymentResult rejected(String reason) {
        return new PaymentResult(
                null,
                PaymentStatus.REJECTED,
                BigDecimal.ZERO,
                LocalDateTime.now(),
                reason,
                Map.of()
        );
    }

    public static PaymentResult pending(String transactionId, Map<String, String> details) {
        return new PaymentResult(
                transactionId,
                PaymentStatus.PENDING,
                BigDecimal.ZERO,
                LocalDateTime.now(),
                "Aguardando confirmação",
                details
        );
    }
}
