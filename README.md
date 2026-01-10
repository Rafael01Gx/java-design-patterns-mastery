# 🎯 Java Design Patterns Mastery

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-23-blueviolet?style=for-the-badge)
![SOLID](https://img.shields.io/badge/SOLID-Principles-success?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**Implementações práticas e detalhadas dos principais padrões de projeto GoF (Gang of Four) em Java, com foco em código limpo, boas práticas e princípios SOLID.**

[📚 Documentação](#padrões-implementados) • [🚀 Começar](#como-usar) • [💡 Exemplos](#exemplos-práticos) • [🤝 Contribuir](#contribuição)

---

</div>

## 📖 Sobre o Projeto

Este repositório contém uma coleção completa de **padrões de projeto** (Design Patterns) implementados em Java, desde conceitos fundamentais até técnicas avançadas como **Double Checked Locking** e **Thread-Safety**. Cada padrão inclui explicações teóricas, exemplos do mundo real e código funcional pronto para uso.

### 🎓 O que você vai aprender

- ✅ Implementação profissional de 12+ padrões de projeto
- ✅ Boas práticas de programação orientada a objetos
- ✅ Princípios SOLID aplicados na prática
- ✅ Técnicas de thread-safety e concorrência
- ✅ Testes unitários para padrões de projeto
- ✅ Cenários reais de aplicação

## 🏗️ Padrões Implementados

### Padrões Criacionais

<table>
<tr>
<td width="50%">

#### 🔸 Singleton
- Implementação básica
- Double Checked Locking
- Thread-safe com Lazy Initialization
- Exemplos multithreading

</td>
<td width="50%">

#### 🔸 Factory Method
- Criação de objetos flexível
- Conexões de banco de dados
- MySQL e SQLite factories
- Exemplos práticos

</td>
</tr>
<tr>
<td>

#### 🔸 Abstract Factory
- Famílias de objetos relacionados
- Sistema de carros (simples, robusto, luxuoso)
- Criação sem acoplamento
- Código modular

</td>
<td>

#### 🔸 Builder
- Construção de objetos complexos
- Thread-safety integrada
- Fluent Interface
- Validações e imutabilidade

</td>
</tr>
<tr>
<td>

#### 🔸 Prototype
- Clonagem eficiente de objetos
- Deep vs Shallow copy
- Performance otimizada
- Casos de uso reais

</td>
<td>

</td>
</tr>
</table>

### Padrões Estruturais

<table>
<tr>
<td width="50%">

#### 🔹 Adapter
- Integração de interfaces incompatíveis
- Sistemas de pagamento
- Conversão de dados
- Exemplos práticos

</td>
<td width="50%">

#### 🔹 Composite
- Estruturas hierárquicas
- Árvores de objetos
- Tratamento uniforme
- Componente/Folha/Composto

</td>
</tr>
<tr>
<td>

#### 🔹 Proxy
- Proxy Remoto, Virtual e de Proteção
- Lazy Loading
- Controle de acesso
- Logging e segurança

</td>
<td>

#### 🔹 Flyweight
- Otimização de memória
- Compartilhamento de objetos
- Performance em larga escala
- Cache inteligente

</td>
</tr>
<tr>
<td>

#### 🔹 Facade
- Simplificação de subsistemas
- Interface unificada
- Redução de complexidade
- APIs elegantes

</td>
<td>

</td>
</tr>
</table>

### Padrões Comportamentais

<table>
<tr>
<td width="100%">

#### 🔶 Strategy Pattern
- Algoritmos intercambiáveis
- Sistema de pagamento (Cartão, PayPal)
- Estados de vistoria (Aprovado, Pendente, Reprovado)
- Código modular e testável
- **Testes unitários simplificados**
- Comparação: Strategy vs No Strategy

</td>
</tr>
</table>

## 🎯 Princípios SOLID

### Single Responsibility Principle (SRP)
- Explicação detalhada com Java
- Exemplos do mundo real
- Código limpo e manutenível
- Impacto no design de software

## 🚀 Como Usar

### Pré-requisitos

```bash
Java 25 instalado
IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)
```

### Clonando o Repositório

```bash
git clone https://github.com/seu-usuario/java-design-patterns-mastery.git
cd java-design-patterns-mastery
```

### Estrutura do Projeto

```
📦 java-design-patterns-mastery
├── 📂 creational/
│   ├── 📂 singleton/
│   ├── 📂 factory/
│   ├── 📂 abstract-factory/
│   ├── 📂 builder/
│   └── 📂 prototype/
├── 📂 structural/
│   ├── 📂 adapter/
│   ├── 📂 composite/
│   ├── 📂 proxy/
│   ├── 📂 flyweight/
│   └── 📂 facade/
├── 📂 behavioral/
│   └── 📂 strategy/
├── 📂 solid/
│   └── 📂 srp/
└── 📂 tests/
```

## 💡 Exemplos Práticos

### Singleton com Double Checked Locking

```java
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}
```

### Strategy Pattern para Pagamentos

```java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Pagando R$" + amount + " com cartão");
    }
}

class PaymentProcessor {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(double amount) {
        strategy.pay(amount);
    }
}
```

## 🧪 Testes

Cada padrão inclui testes unitários demonstrando:
- ✅ Funcionamento correto
- ✅ Thread-safety quando aplicável
- ✅ Casos de uso reais
- ✅ Tratamento de exceções

```bash
# Executar todos os testes
mvn test
```

## 📚 Recursos Adicionais

- 📖 [Design Patterns: Elements of Reusable Object-Oriented Software](https://en.wikipedia.org/wiki/Design_Patterns) - GoF
- 🎓 [Refactoring Guru - Design Patterns](https://refactoring.guru/design-patterns)
- 💻 [Source Making - Design Patterns](https://sourcemaking.com/design_patterns)

## 🤝 Contribuição

Contribuições são muito bem-vindas! Se você tem sugestões de melhorias ou novos padrões:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovoPadrao`)
3. Commit suas mudanças (`git commit -m 'Adiciona novo padrão'`)
4. Push para a branch (`git push origin feature/NovoPadrao`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Rafael Moraes**  
[![GitHub](https://img.shields.io/badge/GitHub-Rafael01Gx-181717?style=for-the-badge&logo=github)](https://github.com/Rafael01Gx)

Desenvolvido com ☕ e muita dedicação para a comunidade Java

## 🙏 Créditos

Conteúdo baseado nos excelentes tutoriais de **Nataniel Paiva**  
[![YouTube](https://img.shields.io/badge/YouTube-NatanielTech-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://www.youtube.com/@NatanielTech)

---

<div align="center">

### ⭐ Se este repositório foi útil, deixe uma estrela!

**[⬆ Voltar ao topo](#-java-design-patterns-mastery)**

</div>