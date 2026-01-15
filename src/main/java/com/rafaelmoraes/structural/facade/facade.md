## FACADE PATTERN

---

### 🎓 ENTENDENDO O FACADE PATTERN:

<br/>

#### O QUE É?
 - Fornece uma interface UNIFICADA e SIMPLIFICADA
 - Para um conjunto COMPLEXO de subsistemas
 - Torna o subsistema mais FÁCIL de usar
 - "Fachada" que esconde a complexidade interna
---
#### ANALOGIA DO MUNDO REAL:
 - Recepcionista de hotel (facade) vs departamentos individuais
 - Atendente de loja (facade) vs estoque/caixa/entrega separados
 - Interface gráfica (facade) vs linha de comando complexa
 - Controle remoto universal (facade) vs controles individuais
---
#### COMPONENTES:
 1. Facade (HomeTheaterFacade) - Interface simplificada
 2. Subsystems (TV, Amplifier, DVD...) - Classes complexas
 3. Client - Usa apenas a Facade
---
#### PROBLEMA QUE RESOLVE:

#### SEM Facade:
 - Cliente precisa conhecer TODOS os subsistemas
 - Código complexo e repetitivo
 - Difícil de manter
 - Fácil cometer erros
 - Alto acoplamento
---
#### COM Facade:
 - Cliente conhece APENAS a facade
 - Código simples e limpo
 - Fácil de manter
 - Menos erros
 - Baixo acoplamento
---
#### VANTAGENS:
- ✅ Simplifica interface complexa
- ✅ Desacopla cliente dos subsistemas
- ✅ Promove layering (camadas)
- ✅ Facilita uso do sistema
- ✅ Reduz dependências
- ✅ Melhora legibilidade
- ✅ Permite evolução independente
---
#### QUANDO USAR:
- ✅ Sistema complexo com muitas classes
- ✅ Quer fornecer interface simples para sistema complexo
- ✅ Muitas dependências entre cliente e implementação
- ✅ Quer estruturar sistema em camadas
- ✅ Subsistemas estão fortemente acoplados
---
#### DESVANTAGENS:
- ❌ Facade pode se tornar "god object" (objeto deus)
- ❌ Pode esconder funcionalidades úteis
- ❌ Mais uma camada de indireção
---
#### CASOS REAIS:
 - JDBC (java.sql) - Facade para drivers de banco
 - Spring Framework - Facades para configuração
 - java.net.URL - Facade para protocolos de rede
 - SLF4J - Facade para frameworks de logging
 - Frameworks MVC - Controllers são facades
 - APIs REST - Facades para serviços internos
 - DAO/Repository pattern - Facade para persistência

#### FACADE vs ADAPTER:
 - Facade: Simplifica MÚLTIPLAS interfaces
 - Adapter: Converte UMA interface em OUTRA
---
#### FACADE vs PROXY:
 - Facade: Simplifica acesso
 - Proxy: Controla acesso (mesmo interface)
---
#### FACADE vs MEDIATOR:
 - Facade: Comunicação unidirecional (cliente → subsistemas)
 - Mediator: Comunicação bidirecional (componentes ↔ mediator)
---
#### BOAS PRÁTICAS:
 1. Facade não deve impedir acesso direto aos subsistemas
 2. Mantenha facade focada em casos de uso comuns
 3. Não coloque lógica de negócio na facade
 4. Facade deve delegar, não implementar
 5. Pode ter múltiplas facades para diferentes clientes
---
#### EXEMPLO JAVA:
 - javax.faces.context.FacesContext
 - java.util.logging.Logger (facade para logging)
 - Spring's JdbcTemplate (facade para JDBC)