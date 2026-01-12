 ## ADAPTER PATTERN

---
### 🎓 ENTENDENDO O ADAPTER PATTERN:

<br/>

#### O QUE É?
 - Converte a interface de uma classe em outra interface esperada pelo cliente
 - Permite que classes incompatíveis trabalhem juntas
 - Também conhecido como Wrapper (Embrulho)

#### ANALOGIA DO MUNDO REAL:
 - Adaptador de tomada: converte tomada de 3 pinos para 2 pinos
 - Tradutor: converte idiomas diferentes
 - Conversor de moedas: converte dólar para real

#### COMPONENTES:
 1. Target (PaymentProcessor) - Interface que o cliente espera
 2. Adaptee (PayPalAPI, StripeGateway) - Classe existente incompatível
 3. Adapter (PayPalAdapter, StripeAdapter) - Adapta Adaptee para Target
 4. Client (EcommerceSystem) - Usa a interface Target

---
#### TIPOS DE ADAPTER:

 1. Object Adapter (usado neste exemplo):
    - Usa COMPOSIÇÃO
    - Adapter tem uma instância do Adaptee
    - Mais flexível


 2. Class Adapter (não mostrado):
    - Usa HERANÇA múltipla
    - Não disponível em Java (sem herança múltipla)
    - Possível com interfaces
---
#### VANTAGENS:
- ✅ Separa conversão da lógica de negócio
- ✅ Single Responsibility (SOLID)
- ✅ Open/Closed (adicionar novos adapters sem modificar cliente)
- ✅ Reutiliza código existente
- ✅ Múltiplos adapters para mesma interface
- ✅ Facilita testes

#### QUANDO USAR:
- ✅ Quer usar classe existente mas interface é incompatível
- ✅ Precisa integrar bibliotecas de terceiros
- ✅ Quer criar classe reutilizável com classes não relacionadas
- ✅ Legacy code precisa trabalhar com código novo

#### DESVANTAGENS:
- ❌ Aumenta complexidade (mais classes)
- ❌ Pode impactar performance (camada extra)

---
#### CASOS REAIS:
 - Gateways de pagamento (PayPal, Stripe, PagSeguro)
 - Drivers de banco de dados (JDBC adapters)
 - APIs de terceiros (Twitter, Facebook, Google)
 - Legacy systems integration
 - Conversores de formato (JSON ↔ XML)
 - Sistemas de log (SLF4J adapters)

---
#### ADAPTER vs FACADE:
 - Adapter: Converte UMA interface em OUTRA
 - Facade: Simplifica MÚLTIPLAS interfaces em UMA
---
#### ADAPTER vs DECORATOR:
 - Adapter: Muda a INTERFACE
 - Decorator: Adiciona FUNCIONALIDADE (mesma interface)
---
#### ADAPTER vs BRIDGE:
 - Adapter: Trabalha com classes EXISTENTES (pós-design)
 - Bridge: Planejado ANTES da implementação