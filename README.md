# Spring Boot "Insta Share" Example Project

This is a sample Java / Maven / Spring Boot (version 1.5.6) application that can be used as a starter for creating a microservice complete with built-in health check, metrics and much more. I hope it helps you.

## How to Run 

This application is packaged as a war which has Netty. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 17.0.2 and Maven 3.8.3
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by this method:
```
       
        mvn spring-boot:run 
```
* Check the stdout to make sure no exceptions are thrown

Once the application runs you should see something like this

```
Hibernate: alter table "storage" add constraint FKjj6n0ajcrpahvwheb3cfj98mc foreign key ("root_node_id") references "node"
Hibernate: alter table "storage" add constraint FKm1b95c0tls45y86nr0ulw4oy foreign key ("user_id") references "user"
Hibernate: alter table "user" add constraint FK9usot4gododq1u90duvulb92d foreign key ("role_id") references "role"
2022-05-19 03:59:11.146  INFO 12932 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-05-19 03:59:11.161  INFO 12932 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-05-19 03:59:14.445  INFO 12932 --- [           main] o.s.b.w.e.n.NettyWebServer               : Netty started on port 8080
2022-05-19 03:59:14.461  INFO 12932 --- [           main] c.c.i.InstashareApplication              : Started InstashareApplication in 10.609 seconds (JVM running for 12.444)
Hibernate: insert into "role" (created_at, modified_at, version, rolecode, rolename, id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into "role" (created_at, modified_at, version, rolecode, rolename, id) values (?, ?, ?, ?, ?, ?)
Hibernate: select user0_.id as id1_4_, user0_.created_at as created_2_4_, user0_.modified_at as modified3_4_, user0_.version as version4_4_, user0_.email as email5_4_, user0_.lastname as lastname6_4_, user0_.name as name7_4_, user0_.password as password8_4_, user0_."role_id" as role_id9_4_ from "user" user0_ where user0_.email=?
Hibernate: insert into "user" (created_at, modified_at, version, email, lastname, name, password, "role_id", id) values (?, ?, ?, ?, ?, ?, 
?, ?, ?)
Hibernate: select storage0_.id as id1_3_, storage0_.created_at as created_2_3_, storage0_.modified_at as modified3_3_, storage0_.version as version4_3_, storage0_."root_node_id" as root_nod5_3_, storage0_."user_id" as user_id6_3_ from "storage" storage0_ where storage0_."user_id"=?
Hibernate: insert into "node" (created_at, modified_at, version, firstblockid, name, parentid, type, id) values (?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into "storage" (created_at, modified_at, version, "root_node_id", "user_id", id) values (?, ?, ?, ?, ?, ?)
```


## About the Service

The service is just a simple sharing files  REST service. It uses an in-memory database (H2) to store the data.  If your database connection properties work, you can call some REST endpoints defined in ```com.drones.dronesapi.dronesapi.Controller.DroneController``` on **port 8080**. (see below)


Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.

* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern


# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:


### To view your H2 in-memory datbase

The 'dronesdb' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8080/h2-ui. Default username is 'sa' with a blank password. Make sure you disable this in your production profiles. For more, see https://goo.gl/U8m62X

# Questions and Comments: rmora900121@gmail.com