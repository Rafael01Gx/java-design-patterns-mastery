package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class EmailNotificationStrategy implements NotificationStrategy {

    @Override
    public CompletableFuture<NotificationResult> send(NotificationMessage message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(NotificationChannel.EMAIL.getEstimatedDeliveryMs());

                IO.println("📧 EMAIL enviado para: " + message.recipient());
                IO.println("   Assunto: " + message.subject());
                IO.println("   Conteúdo: " + message.content());

                String messageId = "EMAIL-" + UUID.randomUUID().toString().substring(0, 8);
                return NotificationResult.success(NotificationChannel.EMAIL, messageId);

            } catch (InterruptedException e) {
                return NotificationResult.failure(NotificationChannel.EMAIL, e.getMessage());
            }
        });
    }

    @Override
    public boolean canSend(NotificationMessage message) {
        return message.recipient().contains("@");
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.EMAIL;
    }
}
