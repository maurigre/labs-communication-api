# Labs Communication API
E um projeto de plataforma de comunicação de mensagens. Tem como finalidade de cadastrar mensagens a serem enviadas, consultar status da mensagem cadastrada e excluir uma mensagem agendada.

## Tecnologias

 - [`Spring boot`](https://spring.io) - Framework de desenvolvimento Java.
 - [`Spring Hateoas`](https://spring.io/projects/spring-hateoas) - Hipermídia como o motor do estado do aplicativo 
 - [`Lombok`](https://projectlombok.org/) - Biblioteca java que auxilia como ferramentas de construção. 
 - [`MySQL`](https://www.mysql.com/) - Banco de dados relacional.
 - [`JPA/Hibernate`](https://hibernate.org/orm/) - Framework para persistência de dados / ORM.
 - [`Flyway`](https://flywaydb.org/) - Controle de versão para banco de dados
 - [`H2 Database Engine`](https://mvnrepository.com/artifact/com.h2database/h2) - Banco de dados executado em memória, utilizado nos testes.
 - [`RabbitMQ`](https://www.rabbitmq.com/) - RabbitMQ é o broker de mensagens de código aberto mais amplamente implantado.
 - [`Swagger`](https://swagger.io/) - Framework para projetar, construir, documentar e usar serviços da Web RESTful.
 - [`Docker`](https://www.docker.com/) - Plataforma de virtualização 
 
 ## Detalhes importantes
 É necessário ter instalado na sua máquina os seguintes programas:

 * A aplicação foi desenvolvida no java-jdk13. 
 * Motor de build da aplicação e atraves do Mavem. 
 * Aplicação de containers, neste projeto é o [Docker](https://www.docker.com), caso ele não esteja
   instalado, siga o tutorial do próprio site: [Get Docker](https://docs.docker.com/get-docker/).

 
## Informações de como o projeto pode ser executado
Primeiro passo e criar um clone deste repositorio com o comando:
```bash
git clone https://github.com/maurigre/labs-communication-api.git
```
Segundo passo e acessar o diretorio do projeto, com o comando: 
```bash
cd ./labs-communication-api/
```
Terceiro passo, criar variáveis de ambiente. Neste projeto será necessário criar as seguintes variaveis:
- `MGR_MYSQL_DATABASE - Variável destinada para conter o nome do DATABASE`
- `MGR_MYSQL_ROOT_PASSWORD - Destinada a conter o password de root do MySQL`
- `MGR_MYSQL_USER - Destinada a conter seu username do MySQL`
- `MGR_MYSQL_PASSWORD - Destinada a conter sua senha de usuário do MySQL` 

> Observações:<br/>
> -Você pode criar um arquivo <b>.env</b> no diretório raiz do projeto e incluir estas variáveis neste arquivo.<br/>

Exemplo:
```bash
 MGR_MYSQL_DATABASE=labs_communication_db
 MGR_MYSQL_ROOT_PASSWORD=123456
 MGR_MYSQL_USER=labs
 MGR_MYSQL_PASSWORD=123456
```

Quarto passo e realizar o build do projeto com o comando:
```bash
./mvnw clean compile package
```
Quinto passo e realizar o build do docker da API com o comando:
```bash
docker-compose build
```

Sexto passo e subir os containers de docker do MySql, RabbitMq e da API com o comando:   
```bash
docker-compose up -d
```

> Observações:<br/>
> O projeto possui 3 profile são eles:<br/> 
> - <b>prod</b> - Profile de produção que ira rodar na porta: `8080` e irá utilizar o banco MySQL.<br/>
> - <b>dev</b> - Profile de desenvolvimento que ira rodar na porta: `8081` e irá utilizar o banco H2 (Banco em memória).<br/>
> - <b>test</b> - Profile de test que ira rodar na porta: `8082` e irá utilizar o banco H2 (Banco em memória).<br/>

## Api documentada com Swagger

Todas as informações referente ao endpoits da API estão detalhadas no swagger. 
Atraves da URL: 
[`Producao`](http://localhost:8080/api-communications/swagger-ui.html), [`Desenvolvimento`](http://localhost:8081/api-communications/swagger-ui.html)   

## Endpoints da API
São:
- Para cadastro de agendamento:
    POST http://localhost:8080/api-communications/v1/schedules
- Para listar todos os agendamentos:
    GET http://localhost:8080/api-communications/v1/schedules
- Para consulta de agendamento:
    GET http://localhost:8080/api-communications/v1/schedules/{id}
- Para exclusão de agendamento:
    DELETE http://localhost:8080/api-communications/v1/schedules/{id}

É possível consulta-los pelo endpoint da documentação via [Swagger](http://localhost:8080/api-communications/swagger-ui.html).


## License
[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)