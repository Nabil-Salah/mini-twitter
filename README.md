# Mini Twitter

Mini-Twitter is a backend application crafted to foster connections among individuals, promoting the freedom of expression. It enables users to share their thoughts and facilitates the discovery of like-minded individuals to connect with. Users can create their own profile, shaping their persona within the platform.

## Features

- Very up-to-date tweets that come fast favor comes to using cassandra

- Users can customize there profiles 

- Users can follow each others updates

## Installation

Before proceeding with the installation, ensure you have all prerequests requires by spring boot on your system, you can download and install from the [spring boot guide](https://spring.io/guides/gs/spring-boot)

Also insure that you have docker installed on your machine [Guidence](https://docs.docker.com/)
### First databases pulling

This install all images required for databases used with one command.(docker compose magic)

```bash
docker compose -f databases.yaml -p mini-twitter up -d
```

or if using Intellij Idea
![the two >> beside services](https://github.com/Nabil-Salah/mini-twitter/blob/master/pic/image.png?raw=true)

### for cassandra you will need to create keyspace with name spring_cassandra to connect to

```bash
docker exec -it cassandra-tweets bash -c "cqlsh -u cassandra -p cassandra"
```
```bash
CREATE KEYSPACE spring_cassandra WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
```

### for neo4j you will need to create lable with name account to connect to

```bash
docker exec -it neo4j-connections cypher-shell -u neo4j -p admin12345
```
```bash
CALL db.labels() YIELD label
             WHERE label = 'account'
             RETURN label;
```

### Now for our massage broker kafka we need to install its image using docker

```bash
docker compose -f kafka.yaml -p mini-twitter up -d
```

or

![the two >> beside services](https://github.com/Nabil-Salah/mini-twitter/blob/master/pic/image2.png?raw=true)


## Usage

To use Mini-Twitter run src/main/java/com.minitwitter/MiniTwitterApplication

## Examples

Try all api calls using [our postman workspace ](https://www.postman.com/solar-shuttle-887798/workspace/mini-twitter/collection/26063061-111f7c72-45e3-4e2f-9f9e-cd6daeda219b?action=share&creator=26063061)