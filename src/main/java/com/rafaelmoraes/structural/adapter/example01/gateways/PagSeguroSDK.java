package com.rafaelmoraes.structural.adapter.example01.gateways;

import java.util.UUID;

public class PagSeguroSDK {
    
    public static class Transaction {
        public String codigo;
        public String status;
        public double valor;

        Transaction(String codigo, String status, double valor) {
            this.codigo = codigo;
            this.status = status;
            this.valor = valor;
        }
    }

    /**
     * Método específico do PagSeguro
     */
    public Transaction efetuarPagamento(double valorEmReais, String emailCliente) {
        IO.println("🇧🇷 PagSeguro SDK: Efetuando pagamento");
        IO.println("   Email: " + emailCliente);
        IO.println("   Valor: R$ " + String.format("%.2f", valorEmReais));

        String codigo = "PS-" + UUID.randomUUID().toString().substring(0, 10);
        Transaction transacao = new Transaction(codigo, "APROVADO", valorEmReais);

        IO.println("   ✅ PagSeguro: Pagamento aprovado - Código: " + codigo);
        return transacao;
    }

    public String consultarStatus(String codigoTransacao) {
        IO.println("🔍 PagSeguro SDK: Consultando status - " + codigoTransacao);
        return "APROVADO";
    }

    public boolean estornar(String codigoTransacao) {
        IO.println("↩️  PagSeguro SDK: Estornando - " + codigoTransacao);
        return true;
    }
}
