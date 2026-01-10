## FACTORY PATTERN

* O Factory Method delega a criação de objetos para subclasses,
permitindo adicionar novos métodos sem modificar código existente.
#
### 🎓 ENTENDENDO O FACTORY METHOD:
###
#### O QUE É?
* - Define uma interface para criar objetos
* - Deixa as subclasses decidirem qual classe instanciar
* - Delega a criação de objetos para métodos especializados
*
#### COMPONENTES:
* 1. Product (Payment) - Interface do produto
* 2. ConcreteProduct (CreditCardPayment, PixPayment...) - Implementações
* 3. Creator (PaymentFactory) - Classe abstrata com Factory Method
* 4. ConcreteCreator (CreditCardPaymentFactory...) - Implementa o Factory Method
*
#### VANTAGENS:
* ✅ Código desacoplado - não precisa conhecer classes concretas
* ✅ Fácil adicionar novos tipos (ex: CryptoPayment)
* ✅ Princípio Open/Closed (SOLID)
* ✅ Single Responsibility (cada factory cria um tipo)
* ✅ Facilita testes unitários
*
#### QUANDO USAR:
* ✅ Quando não sabe antecipadamente os tipos exatos de objetos
* ✅ Quando quer delegar a lógica de criação para subclasses
* ✅ Quando quer fornecer uma biblioteca de componentes extensível
*
* CASOS REAIS:
* - Sistemas de pagamento (Stripe, PagSeguro)
* - Conectores de banco de dados (MySQL, PostgreSQL)
* - Exportadores de documentos (PDF, Excel, CSV)
* - Loggers (Console, File, Cloud)
* - Notificações (Email, SMS, Push)