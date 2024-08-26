1. Introdução

- Este documento descreve o processo de migração de uma aplicação monolítica para uma arquitetura baseada em microsserviços. A migração tem como objetivo desacoplar as entidades principais (livros e clientes) em microsserviços independentes, visando facilitar a manutenção, escalabilidade e performance 

2. Padrões Utilizados

- Padrão de Microsserviços: Cada entidade principal do sistema (Livro, Cliente, Pedido) foi transformada em um micro serviço independente, com sua própria base de dados e lógica de negócio.

- Feign Client: Empregado para comunicação entre microsserviços via HTTP, simplificando chamadas RESTful e a integração com APIs externas.

3. Decisões de Design

- Desacoplamento de Entidades: As entidades Book e Customer foram separadas da aplicação monolítica para se tornarem microsserviços independentes. O BookOrderService continua a ser responsável pela criação de pedidos, mas agora comunica-se com os serviços de livros e clientes para validar dados e calcular preços.

- Banco de Dados Independente: Cada micro serviço possui seu próprio banco de dados para garantir a independência e modularidade dos dados. 

- Validação de Entidades Externas: O BookOrderService foi ajustado para verificar a existência de livros e clientes antes de criar um pedido, utilizando Feign Clients para acessar os serviços Book e Customer.

4. Comunicação entre Microsserviços
   
- Chamada HTTP via Feign Client: A comunicação entre os microsserviços é realizada principalmente por chamadas HTTP, utilizando o Feign Client. Isso permite que o BookOrderService consulte os microsserviços Book e Customer para validar dados e obter informações.

5. Benefícios Alcançados

- Manutenção Simplificada: Separação de responsabilidades permite atualizações e correções sem impactar outras partes do sistema.

- Escalabilidade Seletiva: Microsserviços podem ser escalados individualmente de acordo com a demanda.

- Resiliência Aumentada: A aplicação torna-se mais robusta contra falhas em serviços individuais.
  
6. Conclusão
   
- A migração para microsserviços visa transformar o projeto em uma arquitetura moderna e eficiente, suportando melhor o crescimento e as necessidades futuras da aplicação. O uso de padrões bem estabelecidos garante que o sistema seja mais fácil de manter, escalar e adaptar conforme as demandas do negócio evoluem.
