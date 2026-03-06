# FinEdu Tech - Gestão & Educação Financeira

O **FinEdu Tech** é um ecossistema de finanças pessoais que combina ferramentas de gestão (controle de gastos e ganhos) com módulos de inteligência educacional. Este projeto foi desenvolvido com foco em **escalabilidade**, **código limpo** e **manutenibilidade**, utilizando práticas modernas de engenharia de software.

## 🛠️ Tecnologias e Ferramentas
* **Linguagem:** Java 21
* **Framework:** Spring Boot 4.0.3
* **View Engine:** Thymeleaf (Server-side Rendering)
* **Persistência:** Spring Data JPA & PostgreSQL
* **Validação:** Bean Validation (Hibernate Validator)
* **Build Tool:** Maven

## 🏗️ Arquitetura e Boas Práticas
O projeto adota os princípios de **Domain-Driven Design (DDD)** para garantir uma separação clara de responsabilidades:

* **Domain:** O coração da aplicação, contendo as Entidades de negócio (`LancamentoFinanceiro`), Enums (`TipoLancamento`) e as interfaces de Repository.
* **Application:** Camada responsável pela exposição dos dados através de Controllers e coordenação do fluxo de informação.
* **Infrastructure:** Implementações técnicas, configurações de banco de dados e drivers.
* **Clean Code:** * Nomenclatura semântica: Substantivos para atributos e Verbos claros para métodos (ex: `registrarNovoLancamento`).
  * Validações de domínio rigorosas para garantir a integridade dos dados antes da persistência.


## 📈 Roadmap 
1. **Gestão de Lançamentos:** Cadastro e listagem de receitas e despesas com validação de data e valor.
2. **Simulador de Investimentos:** Projeções de juros compostos com visualizações gráficas.
3. **Central de Conhecimento:** API de conteúdo educativo sobre SELIC, CDI e mercado financeiro.

## ⚙️ Como Executar o Projeto
1. Certifique-se de ter o **Java 21** instalado.
2. Configure um banco de dados PostgreSQL chamado `finedu_db`.
3. No arquivo `src/main/resources/application.properties`, ajuste as credenciais do seu banco de dados.
4. Execute a aplicação através do Eclipse/STS ou via terminal utilizando o comando:
   ```bash
   ./mvnw spring-boot:run
