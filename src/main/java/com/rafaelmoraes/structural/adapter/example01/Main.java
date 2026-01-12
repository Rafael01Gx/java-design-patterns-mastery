package com.rafaelmoraes.structural.adapter.example01;

import com.rafaelmoraes.structural.adapter.example01.adapters.PagSeguroAdapter;
import com.rafaelmoraes.structural.adapter.example01.adapters.PayPalAdapter;
import com.rafaelmoraes.structural.adapter.example01.adapters.StripeAdapter;
import com.rafaelmoraes.structural.adapter.example01.processor.PaymentProcessor;

import java.util.List;

public class Main {
    static void main() {


            IO.println("=".repeat(70));
            IO.println("🔌 ADAPTER PATTERN - SISTEMA DE PAGAMENTOS UNIFICADO");
            IO.println("=".repeat(70));

            EcommerceSystem ecommerce = new EcommerceSystem();

            // ====================================================================
            // EXEMPLO 1: Processando com PayPal
            // ====================================================================
            IO.println("\n📌 EXEMPLO 1: Processando com PayPal\n");

            PaymentProcessor paypalAdapter = new PayPalAdapter();
            ecommerce.setPaymentProcessor(paypalAdapter);
            ecommerce.processOrder("cliente@email.com", 250.00);


            // ====================================================================
            // EXEMPLO 2: Processando com Stripe
            // ====================================================================
            IO.println("\n📌 EXEMPLO 2: Processando com Stripe\n");

            PaymentProcessor stripeAdapter = new StripeAdapter();
            ecommerce.setPaymentProcessor(stripeAdapter);
            ecommerce.processOrder("cliente@email.com", 350.00);


            // ====================================================================
            // EXEMPLO 3: Processando com PagSeguro
            // ====================================================================
            IO.println("\n📌 EXEMPLO 3: Processando com PagSeguro\n");


            PaymentProcessor pagSeguroAdapter = new PagSeguroAdapter();
            ecommerce.setPaymentProcessor(pagSeguroAdapter);
            ecommerce.processOrder("cliente@email.com", 150.00);


            // ====================================================================
            // EXEMPLO 4: Alternando entre gateways (mesmo código cliente!)
            // ====================================================================
            IO.println("\n📌 EXEMPLO 4: Alternando entre Gateways\n");

            List<PaymentProcessor> processors = List.of(
                    new PayPalAdapter(),
                    new StripeAdapter(),
                    new PagSeguroAdapter()
            );

            String[] customers = {
                    "joao@email.com",
                    "maria@email.com",
                    "carlos@email.com"
            };

            double[] amounts = {100.00, 200.00, 300.00};

            IO.println("Processando 3 pedidos com gateways diferentes:\n");

            for (int i = 0; i < processors.size(); i++) {
                ecommerce.setPaymentProcessor(processors.get(i));
                ecommerce.processOrder(customers[i], amounts[i]);

                if (i < processors.size() - 1) {
                    try { Thread.sleep(500); } catch (InterruptedException e) {}
                }
            }

            // ====================================================================
            // EXEMPLO 5: Processando Reembolso
            // ====================================================================
            IO.println("\n📌 EXEMPLO 5: Processando Reembolso com Stripe\n");

            ecommerce.setPaymentProcessor(stripeAdapter);
            String transactionId = stripeAdapter.processPayment(500.00, "cliente@email.com");

            IO.println("\nAguardando antes do reembolso...\n");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}

            ecommerce.processRefund(transactionId);


            // EXEMPLO 6: Vantagens do Adapter

            IO.println("""
                              EXEMPLO 6: Demonstrando Vantagens do Adapter
                            
                                        ✅ VANTAGENS DEMONSTRADAS:
                                        ----------------------------------------------------
                                        1. ✅ Código cliente (EcommerceSystem) permanece o mesmo
                                        2. ✅ Fácil adicionar novos gateways (ex: Mercado Pago)
                                        3. ✅ APIs incompatíveis trabalham juntas
                                        4. ✅ Sem modificar código existente (Open/Closed)
                                        5. ✅ Conversões centralizadas nos adapters
                                        6. ✅ Testável - cada adapter pode ser testado isoladamente
                                        ----------------------------------------------------
                            
                                        💡 EXEMPLO SEM ADAPTER (código ruim):
                                        ----------------------------------------------------
                                        if (gateway.equals(\\"PayPal\\")) {
                                            // código específico PayPal
                                        } else if (gateway.equals(\\"Stripe\\")) {
                                            // código específico Stripe
                                        } else if (gateway.equals(\\"PagSeguro\\")) {
                                            // código específico PagSeguro
                                        }
                                        // ❌ Difícil manter, testar e expandir!
                                        ----------------------------------------------------
                            
                                        💡 COM ADAPTER (código bom):
                                        ----------------------------------------------------
                                        ecommerce.setPaymentProcessor(adapter);
                                        ecommerce.processOrder(email, amount);
                                        ----------------------------------------------------
                                        ✅ Simples, limpo e extensível!
                            """);

            IO.println("─".repeat(60));

            IO.println("\n" + "=".repeat(70));
            IO.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
            IO.println("=".repeat(70));
        }
    }

