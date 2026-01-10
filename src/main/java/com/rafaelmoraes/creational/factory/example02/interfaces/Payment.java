package com.rafaelmoraes.creational.factory.example02.interfaces;

import com.rafaelmoraes.creational.factory.example02.dto.PaymentResult;
import com.rafaelmoraes.creational.factory.example02.dto.Transaction;
import com.rafaelmoraes.creational.factory.example02.enums.PaymentType;

import java.util.concurrent.CompletableFuture;

public interface Payment {

    /**
     * Processa o pagamento de forma assíncrona
     */
    CompletableFuture<PaymentResult> process(Transaction transaction);

    /**
     * Valida se o pagamento pode ser processado
     */
    boolean validate();

    /**
     * Cancela/Estorna o pagamento
     */
    CompletableFuture<PaymentResult> refund(String transactionId);

    /**
     * Retorna o tipo de pagamento
     */
    PaymentType getType();

    /**
     * Retorna detalhes do método de pagamento
     */
    String getDetails();
}
