package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PushNotificationStrategy implements NotificationStrategy {
    @Override
    public CompletableFuture<NotificationResult> send(NotificationMessage message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(NotificationChannel.PUSH.getEstimatedDeliveryMs());

                IO.println("🔔 PUSH enviado para device: " + message.recipient());
                IO.println("   Título: " + message.subject());
                IO.println("   Corpo: " + message.content());

                String messageId = "PUSH-" + UUID.randomUUID().toString().substring(0, 8);
                return NotificationResult.success(NotificationChannel.PUSH, messageId);

            } catch (InterruptedException e) {
                return NotificationResult.failure(NotificationChannel.PUSH, e.getMessage());
            }
        });
    }

    @Override
    public boolean canSend(NotificationMessage message) {
        return message.recipient().startsWith("device-");
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.PUSH;
    }
}
