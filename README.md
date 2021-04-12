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
 
## Informações de como o projeto pode ser executado
Primeiro passo e criar um clone deste repositorio com o comando:
```bash
git clone https://github.com/maurigre/labs-communication-api.git
```
Segundo passo e acessar o diretorio do projeto, com o comando: 
```bash
cd ./labs-communication-api/
```
Terceiro passo e realizar o build do projeto com o comando:
```bash
./mvnw clean compile package
```
Quarto passo e subir os containers de docker do MySql, RabbitMq e da API com o comando:   
```bash
docker-compose up --build -d
```




## License
[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)