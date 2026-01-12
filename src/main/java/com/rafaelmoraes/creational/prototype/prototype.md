### PROTOTYPE PATTERN

---
🎓 ENTENDENDO O PROTOTYPE PATTERN:

#### O QUE É?
 - Permite copiar/clonar objetos existentes sem depender de suas classes
 - Usa clonagem ao invés de instanciação via 'new'
 - Delega a criação de cópias para os próprios objetos
---
#### COMPONENTES:
 1. Prototype (interface Cloneable) - Define o método clone()
 2. ConcretePrototype (Document) - Implementa a clonagem
 3. Client - Usa clone() ao invés de new
 4. PrototypeRegistry (opcional) - Armazena protótipos prontos
---

### SHALLOW COPY vs DEEP COPY:

<br/>

 SHALLOW COPY (clone() padrão):

 - Copia valores primitivos
 - Copia REFERÊNCIAS de objetos
 - ❌ Objetos aninhados são compartilhados
 - Rápido mas perigoso para objetos complexos

---
 DEEP COPY (deepClone() customizado):

- Copia valores primitivos
- Cria NOVOS objetos para aninhados
- Cada clone é totalmente independente
- Mais lento mas seguro

---

#### VANTAGENS:
- ✅ Performance - Clone é mais rápido que new + configuração
- ✅ Reduz código repetitivo
- ✅ Esconde complexidade de criação
- ✅ Permite criar objetos em runtime
- ✅ Alternativa à herança

---
#### QUANDO USAR:

- ✅ Criação de objetos é cara (muitos atributos, I/O, etc)
- ✅ Sistema precisa de muitas variações de um objeto
- ✅ Objetos são criados a partir de templates
- ✅ Quer evitar factory hierarquias complexas
- 
---
#### DESVANTAGENS:

- ❌ Clonar objetos com referências circulares é complexo
- ❌ Precisa implementar clone() corretamente
- ❌ Deep copy pode ser custoso

---

#### CASOS REAIS:
 - Templates de documentos (Google Docs, Word)
 - Configurações de aplicação
 - Objetos de jogos (personagens, items)
 - Email marketing (templates de email)
 - Formulários web (pre-preenchidos)
 - Cache de objetos complexos

---
#### DICA IMPORTANTE:
 - Sempre use DEEP COPY quando objetos têm Collections ou objetos aninhados
 - Shallow copy só é seguro para objetos com apenas primitivos e Strings