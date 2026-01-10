package com.rafaelmoraes.creational.factory.example02.enums;

public enum PaymentType {
    CREDIT_CARD("Cartão de Crédito", "💳"),
    DEBIT_CARD("Cartão de Débito", "💳"),
    PIX("PIX", "⚡"),
    BOLETO("Boleto Bancário", "📄"),
    PAYPAL("PayPal", "🅿️"),
    CRYPTO("Criptomoeda", "₿");

    private final String displayName;
    private final String icon;

    PaymentType(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() { return displayName; }
    public String getIcon() { return icon; }
}
