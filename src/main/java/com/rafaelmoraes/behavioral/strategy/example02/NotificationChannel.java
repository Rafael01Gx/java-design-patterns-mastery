package com.rafaelmoraes.behavioral.strategy.example02;

public enum NotificationChannel {

    EMAIL("Email", 5000),
    SMS("SMS", 2000),
    PUSH("Push Notification", 1000),
    WHATSAPP("WhatsApp", 3000);


    private final String displayName;
    private final int estimatedDeliveryMs;

    NotificationChannel(String displayName, int estimatedDeliveryMs) {
        this.displayName = displayName;
        this.estimatedDeliveryMs = estimatedDeliveryMs;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getEstimatedDeliveryMs() {
        return estimatedDeliveryMs;
    }

}
