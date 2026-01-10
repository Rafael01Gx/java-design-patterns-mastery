package com.rafaelmoraes.creational.factory.example02.enums;

public enum PaymentStatus {
    PENDING("Pendente", "⏳"),
    PROCESSING("Processando", "⚙️"),
    APPROVED("Aprovado", "✅"),
    REJECTED("Rejeitado", "❌"),
    REFUNDED("Reembolsado", "💸");

    private final String displayName;
    private final String icon;

    PaymentStatus(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() { return displayName; }
    public String getIcon() { return icon; }
}
