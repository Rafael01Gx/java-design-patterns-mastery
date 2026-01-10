package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class WhatsAppNotificationStrategy implements NotificationStrategy {

    @Override
    public CompletableFuture<NotificationResult> send(NotificationMessage message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(NotificationChannel.WHATSAPP.getEstimatedDeliveryMs());

                IO.println("💬 WhatsApp enviado para: " + message.recipient());
                IO.println("   Mensagem: " + message.content());

                String messageId = "WPP-" + UUID.randomUUID().toString().substring(0, 8);
                return NotificationResult.success(NotificationChannel.WHATSAPP, messageId);

            } catch (InterruptedException e) {
                return NotificationResult.failure(NotificationChannel.WHATSAPP, e.getMessage());
            }
        });
    }

    @Override
    public boolean canSend(NotificationMessage message) {
        return message.recipient().matches("\\+?\\d{10,15}");
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.WHATSAPP;
    }
}
