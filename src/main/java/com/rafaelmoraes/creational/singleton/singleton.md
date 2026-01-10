## SINGLETON PATTERN
* O padrão Singleton assegura que apenas uma instância de uma classe seja criada durante a execução da aplicação, evitando múltiplas instâncias que possam causar inconsistências no estado compartilhado.
* 
* 🎓 COMPARAÇÃO DAS IMPLEMENTAÇÕES:
*
* 1. EAGER INITIALIZATION (BasicConfigManager)
*    ✅ Simples e thread-safe
*    ❌ Instância criada mesmo se não for usada
*    📌 Use quando: sempre precisar da instância
*
* 2. LAZY + DOUBLE-CHECKED LOCKING (LazyConfigManager)
*    ✅ Thread-safe e eficiente
*    ✅ Lazy loading (criada só quando necessária)
*    ❌ Mais complexo
*    📌 Use quando: instância pesada e pode não ser necessária
*
* 3. ENUM SINGLETON (DatabaseConnectionPool)
*    ✅ Mais simples e seguro
*    ✅ Protegido contra reflexão e serialização
*    ✅ Recomendado por Joshua Bloch (Effective Java)
*    📌 Use quando: SEMPRE QUE POSSÍVEL (melhor prática)
*
* 🏢 CASOS DE USO REAIS:
* - ✅ Gerenciadores de configuração
* - ✅ Connection pools de banco de dados
* - ✅ Sistemas de cache
* - ✅ Loggers
* - ✅ Gerenciadores de sessão
* - ✅ Factories compartilhadas
*
* ⚠️ QUANDO NÃO USAR SINGLETON:
* - ❌ Quando precisar de múltiplas instâncias no futuro
* - ❌ Em classes que precisam de injeção de dependência
* - ❌ Quando dificultar testes unitários
* - ❌ Em sistemas distribuídos (precisa de singletons distribuídos)
*
* 💡 DICAS IMPORTANTES:
* - Use ENUM sempre que possível (mais seguro)
* - volatile é essencial no Double-Checked Locking
* - Construtor SEMPRE privado
* - Considere usar Dependency Injection ao invés de Singleton
    