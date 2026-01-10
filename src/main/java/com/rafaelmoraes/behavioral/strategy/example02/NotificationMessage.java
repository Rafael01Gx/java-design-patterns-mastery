package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.Map;
import java.util.Objects;

public record NotificationMessage(
        String recipient,
        String subject,
        String content,
        Priority priority,
        Map<String, String> metadata
) {

    public NotificationMessage {
        Objects.requireNonNull(recipient, "Recipient cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
    }

    public NotificationMessage(String recipient, String subject, String content) {
        this(recipient, subject, content, Priority.HIGH, Map.of());
    }
}
