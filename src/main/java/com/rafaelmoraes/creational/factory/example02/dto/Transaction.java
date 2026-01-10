package com.rafaelmoraes.creational.factory.example02.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record Transaction(
        String id,
        Customer customer,
        BigDecimal amount,
        String description
) {
    public Transaction(Customer customer, BigDecimal amount, String description) {
        this(UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                customer, amount, description);
    }
}