package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.concurrent.CompletableFuture;

public interface NotificationStrategy {

    CompletableFuture<NotificationResult> send(NotificationMessage message);

    boolean canSend(NotificationMessage message);

    NotificationChannel getChannel();

}
