## BUILDER PATTERN

---

### 🎓 ENTENDENDO O BUILDER PATTERN:

#### PROBLEMA QUE RESOLVE:
 - Construtores com muitos parâmetros são difíceis de ler
 - Ordem dos parâmetros é confusa
 - Parâmetros opcionais requerem múltiplos construtores
---
### EXEMPLO DO PROBLEMA (SEM BUILDER):

 public Order(String id, String name, String email, String phone,
              String shipping, String billing, String payment,
              double total, double discount, double shipping,
              String coupon, String notes, boolean gift,
              String message, boolean express) {
     // 15 parâmetros! 😱
 }
``` JAVA
 Order order = new Order("001", "João", "email", "phone",
                         "address", "address", "card",
                         100.0, 10.0, 5.0,
                         null, null, false, null, false);
```
 * Impossível saber o que cada parâmetro significa! 😵

---
### SOLUÇÃO COM BUILDER:

```
 Order order = new Order.Builder("001", "João")
     .customerEmail("email")
     .totalAmount(100.0)
     .discount(10.0)
     .build();
```
 * Código auto-explicativo! 😍
---
####  COMPONENTES:
 1. Classe Product (Order) - Objeto complexo final
 2. Construtor privado - Impede criação direta
 3. Classe Builder interna estática
 4. Métodos fluentes - Retornam 'this'
 5. Método build() - Cria o objeto final
---
####  VANTAGENS:
- ✅ Código mais legível e auto-explicativo
- ✅ Parâmetros nomeados (não precisa lembrar ordem)
- ✅ Objeto final é IMUTÁVEL (thread-safe)
- ✅ Validação centralizada
- ✅ Parâmetros opcionais fáceis de gerenciar
- ✅ Interface fluente (method chaining)
---
####  QUANDO USAR:

- ✅ Classe tem 4+ parâmetros no construtor
- ✅ Muitos parâmetros são opcionais
- ✅ Quer garantir imutabilidade
- ✅ Construção do objeto é complexa
---
 THREAD-SAFETY:
 - Builder não é thread-safe (não precisa)
 - Objeto final (Order) é IMUTÁVEL = thread-safe
 - Use um Builder por thread