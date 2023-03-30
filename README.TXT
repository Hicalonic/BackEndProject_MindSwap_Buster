# **MindSwapBuster**

![](https://i.ibb.co/KbFvPvy/Screenshot-from-2023-02-19-23-05-22.png) 

**Rules:**
MindSwapBuster is an application that was created using Spring Boot Restful API, and has this features:
-> Git
-> README
-> Model Relationships
-> Spring Security
-> Call to another API: IMDB
-> Email sender: SpringBootMail (SMTP)
-> Docker file - REDIS e MySQL
-> Swagger doc
-> Postman collection
-> Docker Compose
-> In Memory Cache
-> JUNIT Tests

**History:**
Blockbuster, once a leader in entertainment, filed for bankruptcy in 2010 due to the rise of streaming services.
However, Mind Swap Buster offers a unique concept that could bring back the communal spirit of Blockbuster by allowing users to swap minds and connect with others who share their interests.
Mind Swap Buster has the potential to revive a Blockbuster-style service for a new generation.


**Project overview:**
This two-week Project was completed within 14 weeks of bootcamp, mixing the learned concepts of API REST

**Authors:**
- João Pedro Rodrigues - [@johnDoCodigo](https://github.com/johnDoCodigo)
- Miguel Alves - [@aguiar425](https://github.com/Aguiar425)
- Reinaldo Marques - [@hicalonic] (https://github.com/Hicalonic)
- Rui Rajão - [@ruirajao](https://github.com/ruirajao)


docker build -t blockbuster-mysql:latest -f Dockerfile-mysql.Dockerfile  .
 docker compose up
 -t blockbuster-redis:latest -f Dockerfile-redis.Dockerfile  .

// BUILD REDIS IMAGE-->   docker build -t blockbuster-redis:latest -f Dockerfile-redis.Dockerfile
//BUILD MYSQL IMAGE --->   docker build -t blockbuster-mysql:latest -f Dockerfile-mysql.Dockerfile  .
RUN COMMAND FOR DOCKER COMPOSE --->    docker compose up
