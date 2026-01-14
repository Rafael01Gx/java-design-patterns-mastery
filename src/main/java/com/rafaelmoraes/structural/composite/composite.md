## COMPOSITE PATTERN

---


### 🎓 ENTENDENDO O COMPOSITE PATTERN:

<br/>

#### O QUE É?
 - Compõe objetos em estruturas de ÁRVORE
 - Permite tratar objetos individuais e composições UNIFORMEMENTE
 - Cliente trata folhas e compostos da mesma maneira
---
#### ANALOGIA DO MUNDO REAL:
 - Sistema de arquivos (pastas e arquivos)
 - Organograma empresarial (gerentes e funcionários)
 - Menu de restaurante (categorias e pratos)
 - Interface gráfica (containers e widgets)
---
#### COMPONENTES:
 1. Component (FileSystemComponent) - Interface/classe abstrata comum
 2. Leaf (File) - Objeto simples, não tem filhos
 3. Composite (Folder) - Objeto composto, pode ter filhos
 4. Client - Usa a interface Component
---

 ESTRUTURA EM ÁRVORE:

``` TEXT
        Composite (Folder)
       /        |        \
   Leaf(File) Composite  Leaf(File)
               /    \
          Leaf    Leaf
```
---
 OPERAÇÕES RECURSIVAS:
 - display() - Exibe toda hierarquia recursivamente
 - calculateTotalSize() - Soma tamanhos recursivamente
 - search() - Busca em profundidade recursivamente
---
#### VANTAGENS:
- ✅ Cliente trata objetos simples e compostos uniformemente
- ✅ Fácil adicionar novos tipos de componentes
- ✅ Open/Closed Principle (SOLID)
- ✅ Operações recursivas naturais
- ✅ Estruturas hierárquicas complexas ficam simples
---
#### QUANDO USAR:
- ✅ Objetos formam hierarquia de árvore
- ✅ Cliente deve tratar objetos simples e compostos igualmente
- ✅ Precisa representar hierarquias parte-todo
- ✅ Operações recursivas são naturais
---
#### DESVANTAGENS:
- ❌ Dificultar restringir componentes no composite
- ❌ Design pode se tornar muito genérico
- ❌ Type-safety pode ser comprometido
---
#### CASOS REAIS:
 - Sistemas de arquivos (Windows Explorer, macOS Finder)
 - Menus de aplicações (Menu > Submenu > Item)
 - Organogramas empresariais
 - Estruturas HTML/XML (DOM Tree)
 - Interface gráfica (Swing, JavaFX - containers e components)
 - Expressões matemáticas (operadores e operandos)
---
#### DIFERENÇA CHAVE:
 - SEM Composite:
<br/>
   Cliente precisa diferenciar folhas e compostos
``` JAVA
   if (isFolder) {
        processFolder(); 
   } else {
        processFile(); 
   }
```
<br/>
 - COM Composite:
 <br/> 
    Cliente trata tudo igual

``` JAVA
   component.operation(); // Funciona para ambos!
```
---
 PADRÕES RELACIONADOS:
 - Chain of Responsibility: Frequentemente usado com Composite
 - Decorator: Similar estruturalmente, mas intenção diferente
 - Flyweight: Para compartilhar folhas em estruturas grandes
 - Iterator: Para percorrer estruturas Composite
 - Visitor: Para operações em elementos Composite