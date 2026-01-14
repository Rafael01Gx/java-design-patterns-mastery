## FLYWEIGHT PATTERN

---
### 🎓 ENTENDENDO O FLYWEIGHT PATTERN:

<br/>

#### O QUE É?
 - Usa compartilhamento para suportar grandes quantidades de objetos
 - Separa estado INTRÍNSECO (compartilhado) de EXTRÍNSECO (único)
 - Reduz drasticamente o uso de memória
---
####  CONCEITOS CHAVE:

 1. **ESTADO INTRÍNSECO (Intrinsic State):**

    <br/>
    
    - Armazenado NO flyweight
    - Compartilhado entre múltiplas instâncias
    - IMUTÁVEL
    - Exemplo: caractere 'A', fonte 'Arial'
---
 2. **ESTADO EXTRÍNSECO (Extrinsic State):**

    <br/>

     - Passado PARA o flyweight
    - Único para cada uso
    - Armazenado FORA do flyweight
    - Exemplo: posição, cor, tamanho
---
#### COMPONENTES:
 1. Flyweight (CharacterFlyweight) - Interface
 2. ConcreteFlyweight (ConcreteCharacter) - Implementação compartilhável
 3. FlyweightFactory (CharacterFactory) - Gerencia pool de flyweights
 4. Client (TextDocument) - Mantém referências a flyweights + estado extrínseco
---
#### ECONOMIA DE MEMÓRIA:
<br/>

 **SEM Flyweight:**
 - 10.000 caracteres = 10.000 objetos completos
 - Cada objeto: ~120 bytes
 - Total: ~1.200.000 bytes (1.2 MB)

<br/>

 **COM Flyweight:**
 - 10.000 caracteres = 10 flyweights + 10.000 contextos
 - 10 flyweights: ~1.000 bytes
 - 10.000 contextos: ~240.000 bytes
 - Total: ~241.000 bytes (241 KB)
 - ECONOMIA: ~80-95% de memória!
---
#### VANTAGENS:
- ✅ Redução MASSIVA de memória (80-95%)
- ✅ Melhora performance (menos objetos = menos GC)
- ✅ Compartilhamento eficiente
- ✅ Escalável para milhões de objetos
---
#### QUANDO USAR:
- ✅ Aplicação usa MUITOS objetos similares
- ✅ Custo de armazenamento é alto
- ✅ Estado pode ser dividido em intrínseco/extrínseco
- ✅ Identidade de objeto não é importante
- ✅ Objetos podem ser compartilhados
---
#### DESVANTAGENS:
- ❌ Complexidade aumenta (separação de estados)
- ❌ Código mais difícil de entender
- ❌ Pode impactar performance (lookup no pool)
- ❌ Thread-safety precisa ser considerada
---
### CASOS REAIS:
 - Editores de texto (Word, Google Docs) - caracteres
 - Jogos - partículas, árvores, terreno
 - Java String Pool - Strings são flyweights!
 - Integer.valueOf() - Cache de -128 a 127
 - Renderizadores gráficos - sprites, texturas
 - Sistemas de UI - widgets, ícones
---
#### EXEMPLO JAVA BUILT-IN:
``` JAVA
 String s1 = "Hello";
 String s2 = "Hello";
 System.out.println(s1 == s2); // true! (mesmo objeto - flyweight!)

 Integer i1 = Integer.valueOf(100);
 Integer i2 = Integer.valueOf(100);
 System.out.println(i1 == i2); // true! (flyweight para -128 a 127)
```
---
#### FLYWEIGHT vs SINGLETON:
 - Singleton: UMA única instância
 - Flyweight: MÚLTIPLAS instâncias compartilhadas

#### FLYWEIGHT vs PROTOTYPE:
 - Flyweight: Compartilha objetos
 - Prototype: Clona objetos (cópias independentes)

#### DICA IMPORTANTE:
 - Flyweight é útil quando você tem MUITOS objetos (milhares/milhões)
 - Para poucos objetos, a complexidade não vale a pena
 - Sempre meça a economia real de memória