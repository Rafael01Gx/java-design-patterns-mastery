package com.rafaelmoraes.creational.factory.example02;

import com.rafaelmoraes.creational.factory.example02.dto.Customer;
import com.rafaelmoraes.creational.factory.example02.dto.PaymentResult;
import com.rafaelmoraes.creational.factory.example02.dto.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    static void main() throws ExecutionException, InterruptedException {
        System.out.println("=".repeat(70));
        System.out.println("🏭 FACTORY METHOD PATTERN - SISTEMA DE PAGAMENTOS");
        System.out.println("=".repeat(70));

        Customer customer = new Customer(
                "João Silva",
                "joao@email.com",
                "123.456.789-00"
        );

        // ====================================================================
        // EXEMPLO 1: Pagamento com Cartão de Crédito
        // ====================================================================
        System.out.println("\n📌 EXEMPLO 1: Pagamento com Cartão de Crédito\n");

        PaymentFactory creditCardFactory = new CreditCardPaymentFactory(
                "4532123456789012",
                "JOAO SILVA",
                "12/2028",
                "123",
                3  // 3x
        );

        Transaction tx1 = new Transaction(customer, new BigDecimal("299.90"), "Notebook");
        PaymentResult result1 = creditCardFactory.processPayment(tx1).get();

        printResult(result1);

        // ====================================================================
        // EXEMPLO 2: Pagamento com PIX
        // ====================================================================
        System.out.println("\n📌 EXEMPLO 2: Pagamento com PIX\n");

        PaymentFactory pixFactory = new PixPaymentFactory(
                "joao@email.com",
                "Email"
        );

        Transaction tx2 = new Transaction(customer, new BigDecimal("150.00"), "Mouse Gamer");
        PaymentResult result2 = pixFactory.processPayment(tx2).get();

        printResult(result2);

        // ====================================================================
        // EXEMPLO 3: Pagamento com Boleto
        // ====================================================================
        System.out.println("\n📌 EXEMPLO 3: Pagamento com Boleto Bancário\n");

        PaymentFactory boletoFactory = new BoletoPaymentFactory(
                "237",  // Bradesco
                LocalDateTime.now().plusDays(3)
        );

        Transaction tx3 = new Transaction(customer, new BigDecimal("1200.00"), "Teclado Mecânico");
        PaymentResult result3 = boletoFactory.processPayment(tx3).get();

        printResult(result3);

        // ====================================================================
        // EXEMPLO 4: Pagamento com PayPal
        // ====================================================================
        System.out.println("\n📌 EXEMPLO 4: Pagamento com PayPal\n");

        PaymentFactory paypalFactory = new PayPalPaymentFactory(
                "joao@paypal.com",
                "TOKEN-ABC123XYZ789"
        );

        Transaction tx4 = new Transaction(customer, new BigDecimal("89.90"), "Headset");
        PaymentResult result4 = paypalFactory.processPayment(tx4).get();

        printResult(result4);

        // ====================================================================
        // EXEMPLO 5: Processamento em Lote
        // ====================================================================
        System.out.println("\n📌 EXEMPLO 5: Processamento em Lote\n");

        List<PaymentFactory> factories = List.of(
                new CreditCardPaymentFactory("4532111111111111", "MARIA SANTOS", "06/2027", "456", 1),
                new PixPaymentFactory("+5511999999999", "Telefone"),
                new PayPalPaymentFactory("maria@paypal.com", "TOKEN-XYZ")
        );

        List<Transaction> transactions = List.of(
                new Transaction(customer, new BigDecimal("50.00"), "Produto 1"),
                new Transaction(customer, new BigDecimal("75.00"), "Produto 2"),
                new Transaction(customer, new BigDecimal("100.00"), "Produto 3")
        );

        for (int i = 0; i < factories.size(); i++) {
            PaymentResult result = factories.get(i).processPayment(transactions.get(i)).get();
            System.out.println("   " + result.status().getIcon() + " " +
                    result.status().getDisplayName());
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
        System.out.println("=".repeat(70));
    }

    private static void printResult(PaymentResult result) {
        System.out.println("\n📊 RESULTADO:");
        System.out.println("   Status: " + result.status().getIcon() + " " +
                result.status().getDisplayName());
        if (result.transactionId() != null) {
            System.out.println("   ID Transação: " + result.transactionId());
        }
        System.out.println("   Mensagem: " + result.message());
        System.out.println("   Processado em: " +
                result.processedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        if (!result.metadata().isEmpty()) {
            System.out.println("   Detalhes Adicionais:");
            result.metadata().forEach((k, v) ->
                    System.out.println("      " + k + ": " + v));
        }
    }
}
