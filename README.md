# Sistema de Gestão de ONG de Adoção de Pets

Projeto acadêmico desenvolvido para a disciplina de Orientação a Objetos com foco em boas práticas de desenvolvimento de software, Domain-Driven Design (DDD), Test Driven Development (TDD) e arquitetura em camadas.

## Objetivo

O sistema tem como objetivo auxiliar uma ONG de adoção de animais no gerenciamento do fluxo completo de resgate, cuidados médicos, lares temporários e adoções.

O projeto modela regras de negócio reais envolvendo:

* Cadastro e acompanhamento de pets resgatados
* Controle de saúde e vacinação
* Gestão de lares temporários (Foster Homes)
* Processo de entrevista e aprovação de adotantes
* Conclusão de adoções

---

## Tecnologias Utilizadas

* Java 21
* Maven
* JUnit 5
* Git
* GitHub
* GitHub Actions

---

## Arquitetura

O projeto foi desenvolvido seguindo os princípios de:

* Orientação a Objetos (OO)
* Domain-Driven Design (DDD)
* Test Driven Development (TDD)

### Estrutura de Camadas

```text
src/
├── main/java
│   ├── domain
│   │   ├── pet
│   │   ├── adoption
│   │   └── foster
│   │
│   ├── application
│   │   ├── pet
│   │   ├── adoption
│   │   └── foster
│   │
│   ├── infrastructure
│   └── presentation
│
└── test/java
```

---

## Domínios Implementados

### Pet

Responsável pela gestão do animal resgatado.

#### Principais Regras

* Vacinar pet
* Registrar histórico médico
* Marcar pet como doente
* Iniciar tratamento
* Recuperar pet
* Verificar elegibilidade para adoção
* Disponibilizar para adoção
* Finalizar adoção
* Impedir alterações após adoção

#### Fluxo de Adoção do Pet

```text
WAITING_EVALUATION
        ↓
AVAILABLE
        ↓
ADOPTED
```

---

### Adoption

Responsável pelo processo de adoção.

#### Principais Regras

* Iniciar processo de adoção
* Agendar entrevista
* Aprovar candidato
* Rejeitar candidato
* Concluir adoção

#### Fluxo do Processo

```text
PENDING
   ↓
APPROVED
   ↓
COMPLETED
```

ou

```text
PENDING
   ↓
REJECTED
```

---

### Foster

Responsável pelos lares temporários.

#### Principais Regras

* Registrar lares temporários
* Definir capacidade máxima
* Atribuir pets ao lar
* Remover pets do lar
* Impedir excesso de capacidade
* Impedir alocação de pets adotados

---

## Camada Application

A camada Application implementa os casos de uso do sistema e realiza a orquestração entre os Aggregates e os Repositories.

### Casos de Uso Implementados

#### Pet

* VaccinatePetUseCase
* MakePetAvailableForAdoptionUseCase
* AdoptPetUseCase

#### Adoption

* StartAdoptionProcessUseCase
* ScheduleInterviewUseCase
* ApproveAdoptionUseCase
* RejectAdoptionUseCase
* CompleteAdoptionUseCase

#### Foster

* AssignPetToFosterHomeUseCase
* RemovePetFromFosterHomeUseCase

---

## Testes

O projeto utiliza JUnit 5 para validação das regras de negócio.

Cobertura atual:

* Testes unitários do domínio Pet
* Testes unitários do domínio Adoption
* Testes unitários do domínio Foster
* Testes dos casos de uso da camada Application

Para executar os testes:

```bash
mvn test
```

---

## Integração Contínua

O projeto utiliza GitHub Actions para execução automática dos testes a cada push ou pull request.

---

## Equipe

Projeto desenvolvido por estudantes da disciplina de Orientação a Objetos.

* Responsável pelo domínio Pet: José Humberto Cavalcanti Gomes
* Responsável pelo domínio Foster: Gabriel Azevedo Luna
* Responsável pelo domínio Adoption: Deivid Gabriel Ramos e Silva
* Responsável pela camada Application e integração: Eduarda Rafaella Silva Melo
