package com.rafaelmoraes.behavioral.strategy.example02;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main {

    static void main() throws ExecutionException, InterruptedException {
        NotificationService service = new NotificationService();

        IO.println("=======".repeat(10));
        IO.println("🎯 SISTEMA DE NOTIFICAÇÕES - STRATEGY PATTERN");
        IO.println("=======".repeat(10));


        IO.println("\n📌 EXEMPLO 1: Envio por Canal Específico\n");

        var emailMsg = new NotificationMessage(
                "usuario@email.com",
                "Bem-vindo!",
                "Obrigado por se cadastrar em nossa plataforma!"
        );

        NotificationResult result = service.send(NotificationChannel.EMAIL, emailMsg).get();
        IO.println("✅ Resultado: " + (result.success() ? "Sucesso" : "Falha"));
        IO.println("📝 ID da mensagem: " + result.messageId());


        IO.println("=======".repeat(10));
        IO.println("\n📌 EXEMPLO 2: Broadcasting Multi-Canal\n");

        var urgentMsg = new NotificationMessage(
                "+5511999999999",
                "Alerta de Segurança",
                "Detectamos um login suspeito em sua conta!",
                Priority.URGENT,
                Map.of("ip", "192.168.1.1", "location", "São Paulo")
        );

        List<NotificationResult> results = service.broadcast(
                List.of(NotificationChannel.SMS, NotificationChannel.EMAIL, NotificationChannel.PUSH),
                urgentMsg
        ).get();

        IO.println("\n📊 Resultados do Broadcasting:");
        results.forEach(r ->
                IO.println("   " + r.channel() + ": " +
                        (r.success() ? "✅ Enviado" : "❌ Falhou"))
        );


        IO.println("=======".repeat(10));
        IO.println("\n📌 EXEMPLO 3: Envio com Fallback Automático\n");

        var msgFallback = new NotificationMessage(
                "+5511928765483",  // Email inválido
                "Teste Fallback",
                "Esta mensagem testará o fallback automático"
        );

        NotificationResult fallbackResult = service.sendWithFallback(
                NotificationChannel.EMAIL,
                NotificationChannel.SMS,
                msgFallback
        ).get();
        var mssg = fallbackResult.success() ? "✅ Entregue via: " : "❌ Falha na tentativa de fallback via: " ;
        IO.println( mssg + fallbackResult.channel());


        IO.println("=======".repeat(10));
        IO.println("\n📌 EXEMPLO 4: Seleção Inteligente por Prioridade\n");

        var urgentTransaction = new NotificationMessage(
                "+5511988888888",
                "Transação Detectada",
                "Compra de R$ 1.500,00 no cartão final 1234",
                Priority.URGENT,
                Map.of("amount", "1500.00", "merchant", "Loja XYZ")
        );

        NotificationResult smartResult = service.sendSmart(urgentTransaction).get();
        IO.println("🧠 Canal selecionado automaticamente: " + smartResult.channel());
        IO.println("✅ Status: " + (smartResult.success() ? "Entregue" : "Falhou"));

        IO.println("\n" + "=======".repeat(10));
        IO.println("✅ DEMONSTRAÇÃO CONCLUÍDA!");
        IO.println("=======".repeat(10));
    

    }
}
