# 💡 EcoWatt

## Integrantes
- Angélica Ferreira de Matos Melo - RM550776
- Ricardo Yuri Gonçalves Domingues - RM551808
- Eduardo Foncesca Finardi - RM98624
- Matheus Roberto Aparecido de M.C.P de Souza - RM98581
- Lucca Rinaldi Valladão de Freitas - RM98207

## Vídeo explicativo no YouTube
Link vídeo do youtube

## Descrição
O projeto EcoWatt consiste no desenvolvimento de um aplicativo de gerenciamento e controle de gasto energético doméstico, tendo como algumas funcionalidades: Registro de eletrodomésticos e seus respectivos consumos (em Watts) e previsões de consumo mensal.
Nossos objetivos consistem em: Conscientizar a população quanto ao seu consumo energético e entender o impacto disso no meio ambiente, entender a previsão de gastos (financeiros e energéticos) e poder fazer o devido controle, além do domínio do gerenciamento da sua própria casa, que para muitos é algo complicado devido à falta de recursos como esse que a EcoWatt está oferecendo.


## Tecnologias
- *Java 17*
- *Spring Boot*
- *Thymeleaf*
- *Banco de dados H2*

## Executar o projeto
### Pré-requisitos
- Java 17 instalado
- Maven
- Docker

### Passo-a-passo para execução
1. Clone o repositório:

   ```bash
   git clone https://github.com/AngelMelo12/EcoWatt.git

2. Acesse o diretório do projeto via terminal:
    ```bash
    cd EcoWatt

3. Instale as dependências:
    ```bash
    mvn clean install

4. Inicie a aplicação:
    ```bash
    mvn spring-boot:run

5. Acesse a aplicação e navegue entre as páginas no seu navegador em:
    ```bash
    http://localhost:8080

6. Caso queria acessar o console de banco de dados, utilize a seguinte URL:
   ```bash
   http://localhost:8080/h2-console
   