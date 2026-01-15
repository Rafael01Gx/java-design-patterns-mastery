# 🎯 SOLID - Single Responsibility Principle (SRP)

## 📋 Índice
1. [O que é SRP?](#o-que-é-srp)
2. [Definição Formal](#definição-formal)
3. [Por que é Importante?](#por-que-é-importante)
4. [Como Identificar Violações](#como-identificar-violações)
5. [Exemplos Práticos](#exemplos-práticos)
6. [Benefícios](#benefícios)
7. [Armadilhas Comuns](#armadilhas-comuns)
8. [Quando Aplicar](#quando-aplicar)
9. [Checklist de Verificação](#checklist-de-verificação)

---

## 🎯 O que é SRP?

O **Single Responsibility Principle (Princípio da Responsabilidade Única)** é o **primeiro** dos cinco princípios SOLID e afirma:

> **"Uma classe deve ter apenas UMA razão para mudar"**
>
> — Robert C. Martin (Uncle Bob)

### Traduzindo para o Português Claro:

- Uma classe deve fazer **UMA coisa** e fazer bem
- Cada classe deve ter **UM único propósito**
- Mudanças em um aspecto do sistema não devem afetar classes não relacionadas

### Analogia do Mundo Real 🏪

Imagine uma loja de eletrônicos:

❌ **VIOLANDO SRP:**
```
Funcionário João:
- Vende produtos
- Gerencia estoque
- Faz contabilidade
- Limpa a loja
- Atende telefone
- Programa sistemas
```
**Problema:** Se João sair de férias, TUDO para!

✅ **SEGUINDO SRP:**
```
Vendedor - Vende produtos
Estoquista - Gerencia estoque
Contador - Faz contabilidade
Faxineiro - Limpa a loja
Telefonista - Atende telefone
Programador - Programa sistemas
```
**Vantagem:** Cada um pode ser substituído independentemente!

---

## 📖 Definição Formal

### Versão Original (Uncle Bob)

> "A class should have only one reason to change"

### Versão Refinada (2014)

> "Gather together the things that change for the same reasons. Separate those things that change for different reasons."

### Tradução em Português

**"Uma classe deve ter apenas UMA responsabilidade"**

Onde **responsabilidade** = **razão para mudar**

---

## ❓ Por que é Importante?

### 1️⃣ Manutenibilidade
- **Fácil de entender** - Classe faz apenas uma coisa
- **Fácil de modificar** - Mudanças localizadas
- **Fácil de debugar** - Problema está em um lugar específico

### 2️⃣ Testabilidade
- **Testes focados** - Testa apenas uma responsabilidade
- **Mocks simples** - Menos dependências
- **Cobertura clara** - Sabe exatamente o que testar

### 3️⃣ Reusabilidade
- **Classes pequenas** - Fácil de reutilizar
- **Baixo acoplamento** - Independente de outras classes
- **Alta coesão** - Componentes trabalham juntos

### 4️⃣ Flexibilidade
- **Fácil de estender** - Adiciona novas funcionalidades
- **Fácil de substituir** - Troca implementações
- **Fácil de combinar** - Compõe soluções complexas

---

## 🔍 Como Identificar Violações

### ⚠️ Sinais de Alerta (Code Smells)

#### 1. Classe com Nome Genérico
```java
// ❌ MAU - Nome genérico indica múltiplas responsabilidades
class UserManager { }
class DataHandler { }
class SystemController { }
class UtilityClass { }
```

Nomes com sufixos como **Manager**, **Handler**, **Controller**, **Utility** geralmente violam SRP!

#### 2. Muitos Métodos Públicos
```java
// ❌ MAU - Classe faz muitas coisas
class User {
    public void save() { }           // Persistência
    public void sendEmail() { }      // Comunicação
    public void generateReport() { } // Relatórios
    public void validate() { }       // Validação
    public void calculateTax() { }   // Negócio
}
```

#### 3. Muitas Importações
```java
// ❌ MAU - Muitas dependências = múltiplas responsabilidades
import java.sql.*;
import javax.mail.*;
import com.pdf.*;
import org.json.*;
import org.xml.*;
// ... 20 imports mais
```

#### 4. Palavra "E" na Descrição
Se você precisa usar "**E**" para descrever a classe, provavelmente viola SRP:

- ❌ "Esta classe gerencia usuários **E** envia emails"
- ❌ "Esta classe valida dados **E** salva no banco"
- ❌ "Esta classe processa pedidos **E** gera relatórios"

#### 5. Múltiplas Razões para Mudar
Pergunte: "**Por que esta classe mudaria?**"

Se a resposta tem múltiplos motivos, viola SRP:
- ❌ "Mudaria se o formato do email mudar **OU** se o banco mudar **OU** se as regras de validação mudarem"

---

## 💻 Exemplos Práticos

### ❌ Exemplo 1: Violando SRP

```java
/**
 * ❌ PROBLEMA: Classe com MÚLTIPLAS responsabilidades
 */
class User {
    private String name;
    private String email;
    
    // Responsabilidade 1: Validação
    public boolean isValidEmail() {
        return email.contains("@");
    }
    
    // Responsabilidade 2: Persistência (Banco de dados)
    public void save() {
        Connection conn = DriverManager.getConnection("jdbc:mysql://...");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users...");
        ps.setString(1, name);
        ps.setString(2, email);
        ps.execute();
    }
    
    // Responsabilidade 3: Comunicação (Email)
    public void sendWelcomeEmail() {
        Session session = Session.getInstance(properties);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("system@example.com"));
        message.setRecipients(Message.RecipientType.TO, email);
        message.setSubject("Bem-vindo!");
        Transport.send(message);
    }
    
    // Responsabilidade 4: Relatórios (PDF)
    public void generateReport() {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("user.pdf"));
        document.open();
        document.add(new Paragraph("User: " + name));
        document.close();
    }
    
    // Responsabilidade 5: Lógica de Negócio
    public double calculateDiscount() {
        // Regras complexas de desconto
        return 0.10;
    }
}
```

**Problemas desta classe:**
- 🔴 **5 responsabilidades diferentes**
- 🔴 **Difícil de testar** (precisa de banco, email, PDF...)
- 🔴 **Alto acoplamento** (depende de JDBC, JavaMail, iText...)
- 🔴 **Difícil de manter** (mudança em qualquer área afeta a classe)
- 🔴 **Impossível reutilizar** (não posso usar só a validação)

### ✅ Exemplo 2: Seguindo SRP

```java
/**
 * ✅ SOLUÇÃO: Separar em classes com responsabilidades únicas
 */

// Responsabilidade 1: Representar Dados (Model/Entity)
class User {
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // Apenas getters e setters
    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Responsabilidade 2: Validação
class UserValidator {
    public boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public boolean isValidName(String name) {
        return name != null && name.length() >= 3;
    }
    
    public boolean validate(User user) {
        return isValidEmail(user.getEmail()) && isValidName(user.getName());
    }
}

// Responsabilidade 3: Persistência
class UserRepository {
    public void save(User user) {
        // Código de persistência no banco
        Connection conn = DriverManager.getConnection("jdbc:mysql://...");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users...");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.execute();
    }
    
    public User findById(int id) {
        // Busca usuário
        return new User("João", "joao@email.com");
    }
}

// Responsabilidade 4: Comunicação (Email)
class EmailService {
    public void sendWelcomeEmail(User user) {
        Session session = Session.getInstance(properties);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("system@example.com"));
        message.setRecipients(Message.RecipientType.TO, user.getEmail());
        message.setSubject("Bem-vindo " + user.getName() + "!");
        Transport.send(message);
    }
}

// Responsabilidade 5: Geração de Relatórios
class UserReportGenerator {
    public void generatePdfReport(User user) {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("user.pdf"));
        document.open();
        document.add(new Paragraph("Nome: " + user.getName()));
        document.add(new Paragraph("Email: " + user.getEmail()));
        document.close();
    }
}

// Responsabilidade 6: Lógica de Negócio
class DiscountCalculator {
    public double calculateUserDiscount(User user) {
        // Regras de desconto
        return 0.10;
    }
}

// Uso coordenado (pode ser um Service ou Controller)
class UserService {
    private UserValidator validator;
    private UserRepository repository;
    private EmailService emailService;
    
    public void registerUser(User user) {
        // Coordena as operações
        if (validator.validate(user)) {
            repository.save(user);
            emailService.sendWelcomeEmail(user);
        }
    }
}
```

**Vantagens desta abordagem:**
- ✅ **Cada classe tem UMA responsabilidade**
- ✅ **Fácil de testar** (testa cada classe isoladamente)
- ✅ **Baixo acoplamento** (classes independentes)
- ✅ **Fácil de manter** (mudanças localizadas)
- ✅ **Reutilizável** (posso usar EmailService em outros lugares)
- ✅ **Extensível** (fácil adicionar novo tipo de validação)

---

## 🎁 Benefícios

### 1. Código Mais Limpo
```java
// Antes: Confuso
class OrderProcessor {
    public void process() {
        // 200 linhas fazendo tudo
    }
}

// Depois: Claro
class OrderValidator { }
class OrderPersistence { }
class OrderNotification { }
class OrderCalculator { }
```

### 2. Manutenção Facilitada
```java
// ✅ Mudança em email não afeta banco de dados
class EmailService {
    // Muda aqui - apenas email afetado
}

class DatabaseService {
    // Não precisa mexer aqui!
}
```

### 3. Testes Mais Simples
```java
@Test
public void testEmailValidation() {
    UserValidator validator = new UserValidator();
    
    // Testa APENAS validação
    // Não precisa de banco, email, PDF, etc
    assertTrue(validator.isValidEmail("user@example.com"));
    assertFalse(validator.isValidEmail("invalid"));
}
```

### 4. Reuso de Código
```java
// EmailService pode ser usado em múltiplos lugares
UserService userService = new UserService(emailService);
OrderService orderService = new OrderService(emailService);
NotificationService notifService = new NotificationService(emailService);
```

---

## ⚠️ Armadilhas Comuns

### 1. Granularidade Excessiva
```java
// ❌ EXAGERO - Classes pequenas demais
class UserFirstNameGetter { }
class UserLastNameGetter { }
class UserEmailGetter { }
// Isso é ridículo! 😅
```

**Solução:** Use bom senso. SRP não significa "uma classe, um método"!

### 2. Confundir com "Fazer Uma Coisa"
```java
// ✅ CORRETO - User pode ter múltiplos métodos relacionados
class User {
    public String getFullName() { }
    public boolean isActive() { }
    public int getAge() { }
}
// Todos os métodos são sobre "representar dados de usuário"
```

### 3. Separar Prematuramente
```java
// ❌ Não separe antes de precisar
// Se a classe é pequena e simples, não há problema

class SimpleCalculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
}
// Está OK! Não precisa separar em AddCalculator e SubtractCalculator
```

---

## 📅 Quando Aplicar

### ✅ APLICAR quando:
- Classe tem mais de **200 linhas**
- Precisa de **muitos imports** (10+)
- Tem **múltiplas razões para mudar**
- **Difícil de testar** sem mocks complexos
- Nome da classe usa "**Manager**", "**Handler**", "**Util**"
- Usa "**E**" para descrever a classe

### ⏸️ NÃO aplicar prematuramente quando:
- Classe é **pequena e simples** (<100 linhas)
- Tem **coesão natural** (métodos relacionados)
- Separação tornaria o código **mais complexo**
- É uma **classe de domínio** simples (DTOs, Entities)

---

## ✅ Checklist de Verificação

### Para cada classe, pergunte:

1. **Teste do Nome**
    - [ ] O nome da classe é específico? (não genérico como "Manager")
    - [ ] Consigo descrever a classe sem usar "E"?

2. **Teste da Mudança**
    - [ ] A classe tem apenas UMA razão para mudar?
    - [ ] Se mudar o banco de dados, esta classe muda?
    - [ ] Se mudar o formato de email, esta classe muda?
    - [ ] Se mudar regra de negócio, esta classe muda?

3. **Teste da Responsabilidade**
    - [ ] Consigo descrever a responsabilidade em uma frase?
    - [ ] A classe faz apenas uma coisa?

4. **Teste de Dependências**
    - [ ] A classe tem poucas importações (<10)?
    - [ ] As dependências são relacionadas?

5. **Teste de Tamanho**
    - [ ] A classe tem menos de 200 linhas?
    - [ ] Tem menos de 10 métodos públicos?

### Se respondeu "NÃO" para qualquer pergunta: **REFATORE!**

---

## 🎯 Exemplo Completo: E-commerce

### ❌ ANTES (Violando SRP)

```java
class Order {
    private List<Item> items;
    private Customer customer;
    
    // Responsabilidade 1: Cálculos
    public double calculateTotal() { }
    public double calculateTax() { }
    public double calculateShipping() { }
    
    // Responsabilidade 2: Validação
    public boolean validate() { }
    
    // Responsabilidade 3: Persistência
    public void saveToDatabase() { }
    
    // Responsabilidade 4: Email
    public void sendConfirmationEmail() { }
    
    // Responsabilidade 5: Pagamento
    public boolean processPayment() { }
    
    // Responsabilidade 6: Estoque
    public void updateInventory() { }
    
    // Responsabilidade 7: Relatórios
    public void generateInvoice() { }
}
```

### ✅ DEPOIS (Seguindo SRP)

```java
// Responsabilidade 1: Dados
class Order {
    private List<Item> items;
    private Customer customer;
    // Apenas getters/setters
}

// Responsabilidade 2: Cálculos
class OrderCalculator {
    public double calculateTotal(Order order) { }
    public double calculateTax(Order order) { }
    public double calculateShipping(Order order) { }
}

// Responsabilidade 3: Validação
class OrderValidator {
    public boolean validate(Order order) { }
}

// Responsabilidade 4: Persistência
class OrderRepository {
    public void save(Order order) { }
    public Order findById(int id) { }
}

// Responsabilidade 5: Email
class OrderNotificationService {
    public void sendConfirmationEmail(Order order) { }
}

// Responsabilidade 6: Pagamento
class PaymentProcessor {
    public boolean processPayment(Order order) { }
}

// Responsabilidade 7: Estoque
class InventoryManager {
    public void updateInventory(Order order) { }
}

// Responsabilidade 8: Relatórios
class InvoiceGenerator {
    public void generateInvoice(Order order) { }
}

// Coordenação (Service Layer)
class OrderService {
    private OrderValidator validator;
    private OrderRepository repository;
    private PaymentProcessor paymentProcessor;
    private OrderNotificationService notificationService;
    
    public void placeOrder(Order order) {
        if (validator.validate(order)) {
            repository.save(order);
            paymentProcessor.processPayment(order);
            notificationService.sendConfirmationEmail(order);
        }
    }
}
```

---

## 🎓 Resumo

### Em Uma Frase:
> **"Uma classe deve ter apenas uma razão para mudar"**

### Regra de Ouro:
> **"Se você não consegue descrever a responsabilidade da classe em uma frase simples, ela provavelmente está violando SRP"**

### Lembre-se:
- ✅ **Coesão alta** - Métodos trabalham juntos
- ✅ **Acoplamento baixo** - Poucas dependências
- ✅ **Fácil de testar** - Uma responsabilidade = um teste
- ✅ **Fácil de manter** - Mudanças localizadas
- ✅ **Fácil de reutilizar** - Componentes independentes

---

## 📚 Referências

- **Clean Code** - Robert C. Martin
- **Agile Software Development, Principles, Patterns, and Practices** - Robert C. Martin
- **SOLID Principles** - Uncle Bob's Blog

---

## 💡 Dica Final

**SRP não é sobre ter classes com um único método!**

**SRP é sobre ter classes com uma única RESPONSABILIDADE!**

Uma responsabilidade pode requerer múltiplos métodos, desde que todos sejam **coesos** e **relacionados** a essa responsabilidade.

---

**Criado por: Rafael Moraes**  
**Para o repositório: java-design-patterns-mastery**