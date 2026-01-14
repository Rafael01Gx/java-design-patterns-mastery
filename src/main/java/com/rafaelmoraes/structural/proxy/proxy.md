## PROXY PATTERN

---

### 🎓 ENTENDENDO O PROXY PATTERN:


 O QUE É?
 - Fornece um substituto (placeholder) para outro objeto
 - Controla o acesso ao objeto real
 - Adiciona funcionalidades extras sem modificar o objeto original
---
#### TIPOS DE PROXY:

 1. **PROTECTION PROXY (demonstrado):**

    <br/>

    - Controla acesso baseado em permissões
    - Valida credenciais antes de permitir operações
    - Exemplo: Sistema de segurança, firewall
    
---

 2. **VIRTUAL PROXY / LAZY LOADING (demonstrado):**

    <br/>
    
    - Adia criação de objeto caro até ser necessário
    - Economia de recursos
    - Exemplo: Carregar imagens grandes sob demanda
---
 3. **CACHING PROXY (demonstrado):**

    <br/>

     - Armazena resultados para evitar operações repetidas
    - Melhora performance
    - Exemplo: Cache de queries de banco
---
 4. **LOGGING PROXY (demonstrado):**

    <br/>

     - Registra todas as operações
    - Auditoria e debugging
    - Exemplo: Logs de acesso
---
 5. **REMOTE PROXY (não demonstrado):**

    <br/>

     - Representa objeto em espaço de endereço diferente
    - Exemplo: RMI, Web Services
---
 6. **SMART REFERENCE (não demonstrado):**

    <br/>

     - Funcionalidade adicional ao acessar objeto
    - Exemplo: Contagem de referências, thread-safety
---
#### COMPONENTES:
 1. Subject (DatabaseService) - Interface comum
 2. RealSubject (RealDatabaseService) - Objeto real que faz o trabalho
 3. Proxy (ProtectionProxy, CachingProxy...) - Controla acesso ao RealSubject
---
#### VANTAGENS:
- ✅ Controla acesso ao objeto real
- ✅ Lazy initialization (economia de recursos)
- ✅ Adiciona funcionalidades sem modificar objeto real
- ✅ Open/Closed Principle (SOLID)
- ✅ Segurança (proteção de acesso)
- ✅ Performance (cache, lazy loading)
- ✅ Logging e auditoria
---
#### QUANDO USAR:
- ✅ Objeto caro de criar (lazy loading)
- ✅ Precisa controlar acesso (segurança)
- ✅ Quer adicionar logging/cache sem modificar classe
- ✅ Objeto está em espaço de endereço diferente (remote)
- ✅ Gerenciamento de recursos (smart reference)
---
#### DESVANTAGENS:
- ❌ Aumenta complexidade (mais classes)
- ❌ Pode adicionar latência
- ❌ Response time pode aumentar (primeira chamada)
---
#### CASOS REAIS:
 - Spring AOP (Aspect Oriented Programming)
 - Hibernate Lazy Loading
 - JPA Entity Proxies
 - CDN (Content Delivery Network)
 - Nginx/Apache (reverse proxy)
 - Java RMI (Remote Method Invocation)
 - Virtual Proxies em editores de imagem
---
#### PROXY vs DECORATOR:
 - Proxy: Controla ACESSO ao objeto
 - Decorator: Adiciona RESPONSABILIDADES ao objeto
---
#### PROXY vs ADAPTER:
 - Proxy: MESMA interface do objeto real
 - Adapter: Converte uma interface em OUTRA
---
#### PROXY vs FACADE:
 - Proxy: Representa UM objeto
 - Facade: Simplifica MÚLTIPLOS objetos/subsistemas
---
#### EXEMPLO REAL JAVA:
 - Collections.unmodifiableList() - Protection Proxy
 - Hibernate proxies - Virtual Proxy
 - Spring @Transactional - Proxy para transaction management