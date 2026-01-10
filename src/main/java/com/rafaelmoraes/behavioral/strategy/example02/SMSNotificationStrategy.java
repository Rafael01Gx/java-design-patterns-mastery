package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SMSNotificationStrategy implements NotificationStrategy {
    @Override
    public CompletableFuture<NotificationResult> send(NotificationMessage message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(NotificationChannel.SMS.getEstimatedDeliveryMs());

                IO.println("📱 SMS enviado para: " + message.recipient());
                IO.println("   Mensagem: " + message.content());

                String messageId = "SMS-" + UUID.randomUUID().toString().substring(0, 8);
                return NotificationResult.success(NotificationChannel.SMS, messageId);

            } catch (InterruptedException e) {
                return NotificationResult.failure(NotificationChannel.SMS, e.getMessage());
            }
        });
    }

    @Override
    public boolean canSend(NotificationMessage message) {
        return message.recipient().matches("\\+?\\d{10,15}");
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.SMS;
    }
}
