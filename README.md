# Spring Boot Service with Hibernate Database Running on Docker Containers

## Functionality
This is a project to create a user service with a Postgres Database. Both the service and database are running on Docker Container.

## Architecture
![Userserv System Diagram](/userserv.png)

## APIs
Userserv supports 4 operations:
* Create new user (C)
* Get all users (R)
* Update existing user (U)
* Delete user (D)

[Postman Collection](/Customer.postman_collection.json)

## Userserv Organization
Userserv is designed as a Spring Boot service and organized in different layers:
CustomerController &rarr; CustomerService &rarr; CustomerRepository

The term user and customer are used interchangably because user seems to be reserved in database hence customer is used instead.

# Dockerization
To dockerize the whole system on to Docker, there are two tasks to be done:
1. Dockerize Postgres Database
2. Dockerize Userserv

These two configurations can be found under [docker-compose.yaml](/docker-compose.yaml)

## Dockerizing Postgres Database
To dockerize Postgres database, we configure the image used and create a named volume 
(so that data case be stored and retrieved regardless of container removal), 
exposing the ports and environment file which contains username, password and database name.

```java
services:
  postgres-db:
    container_name: "postgres-db"
    image: "postgres:17.0"
    volumes:
      - postgres-data:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    env_file:
      - ./env/postgres.env
```
Please note that this process does not create the user table as well as data.
We can create a Postgres DB separately (using docker run command) and then use the PGAdmin software to connect
and create table, fields and entries.

## Dockerizing Userserv
We created a [Dockerfile](Dockerfile) to build an image out of the generated jar. Note that the process to dockerize would require to run gradle command. 
```java
./gradlew clean build
```

This is because if we bundle building gradle into the Dockerfile, 
it would take a long process for each build since build process requires to download the Gradle.

After the jar file has been created, we can then run docker command to build or start up a container for userserv.

## Configuring the Data Source for Userserv
Since userserv and Postgres DB are running on the same Docker network
they can communicate with each other using container-name. This is the beauty
of using Docker with docker-compose. Docker-compose automatically creates a network
for docker containers to run and communicate on. Every time there is a network communication
Docker will resolve the container-name to the IP address and thus making the network communication
simple.

```java
spring:
  application:
    name: userserv
  datasource:
    url: jdbc:postgresql://postgres-db:5432/mydatabase
```
In this snippet from [application.yaml](/src/main/resources/application.yaml), "postgres-db" is the 
Postgres DB container name specified in docker-compose.yaml

# Build and Run Process
1. From main folder, run:
```java
./gradle clean build
```

2. Ensure Docker Desktop is up and running.
3. Then run docker-compose command:
```java
docker-compose up
```

Reference Tutorial: [https://www.datacamp.com/tutorial/postgresql-docker](https://www.datacamp.com/tutorial/postgresql-docker)