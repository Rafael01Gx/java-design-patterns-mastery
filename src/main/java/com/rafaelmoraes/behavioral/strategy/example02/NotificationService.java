package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class NotificationService {
    private final Map<NotificationChannel, NotificationStrategy> strategies = new HashMap<>();
    private NotificationStrategy defaultStrategy;

    public NotificationService() {
        registerStrategy(new EmailNotificationStrategy());
        registerStrategy(new SMSNotificationStrategy());
        registerStrategy(new PushNotificationStrategy());
        registerStrategy(new WhatsAppNotificationStrategy());

        this.defaultStrategy = strategies.get(NotificationChannel.EMAIL);
    }

    public void registerStrategy(NotificationStrategy strategy) {
        strategies.put(strategy.getChannel(), strategy);
    }

    public CompletableFuture<NotificationResult> send(
            NotificationChannel channel,
            NotificationMessage message) {

        NotificationStrategy strategy = strategies.get(channel);

        if (strategy == null) {
            return CompletableFuture.completedFuture(
                    NotificationResult.failure(channel, "Canal não suportado")
            );
        }

        if (!strategy.canSend(message)) {
            return CompletableFuture.completedFuture(
                    NotificationResult.failure(channel, "Destinatário inválido para este canal")
            );
        }

        return strategy.send(message);
    }

    public CompletableFuture<NotificationResult> send(NotificationMessage message) {
        return defaultStrategy.send(message);
    }


    public CompletableFuture<List<NotificationResult>> broadcast(
            List<NotificationChannel> channels,
            NotificationMessage message) {

        List<CompletableFuture<NotificationResult>> futures = channels.stream()
                .map(channel -> send(channel, message)
                        .exceptionally(ex -> {
                            return null;
                        }))
                .toList();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .toList());


    }

    public CompletableFuture<NotificationResult> sendWithFallback(
            NotificationChannel primary,
            NotificationChannel fallback,
            NotificationMessage message) {

        return send(primary, message)
                .thenCompose(result -> {
                    if (result.success()) {
                        return CompletableFuture.completedFuture(result);
                    }
                    IO.println("⚠️  Falha no canal " + primary + ", tentando " + fallback);
                    return send(fallback, message);
                });
    }


    public CompletableFuture<NotificationResult> sendSmart(NotificationMessage message) {
        NotificationChannel channel = switch (message.priority()) {
            case URGENT -> NotificationChannel.SMS;
            case HIGH -> NotificationChannel.PUSH;
            case LOW -> NotificationChannel.EMAIL;
        };

        return send(channel, message);
    }
}
