package com.rafaelmoraes.behavioral.strategy.example02;

import java.time.LocalDateTime;

public record NotificationResult(
        boolean success,
        String messageId,
        NotificationChannel channel,
        LocalDateTime sentAt,
        String errorMessage
) {
    public static NotificationResult success(NotificationChannel channel, String messageId) {
        return new NotificationResult(true, messageId, channel, LocalDateTime.now(), null);
    }
    public static NotificationResult failure(NotificationChannel channel, String error) {
        return new NotificationResult(false, null, channel, LocalDateTime.now(), error);
    }
}
