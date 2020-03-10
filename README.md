# Spring Boot Microservices with Spring Gateway Cloud

### Services and ports
```
name: service-1-api
port: 8081

name: service-2-api
port: 8082

name: service-api-gateway
port: 8080

```

Build your project by running maven build and build th docker image

### Service-1-api
```
FROM maven:3.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package 

FROM openjdk:8-jdk-alpine
RUN addgroup -S habeebcycle && adduser -S habeeb -G habeebcycle
USER habeeb:habeebcycle
LABEL maintainer="habeebcycle@gmail.com"
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=/usr/src/app/target/*.jar
COPY ${JAR_FILE} /usr/app/service-1-api.jar
ENTRYPOINT ["java","-jar","/usr/app/service-1-api.jar"]
```

### Service-2-api
```
FROM maven:3.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package 

FROM openjdk:8-jdk-alpine
RUN addgroup -S habeebcycle && adduser -S habeeb -G habeebcycle
USER habeeb:habeebcycle
LABEL maintainer="habeebcycle@gmail.com"
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=/usr/src/app/target/*.jar
COPY ${JAR_FILE} /usr/app/service-2-api.jar
ENTRYPOINT ["java","-jar","/usr/app/service-2-api.jar"]
```

### service-api-gateway
```
FROM maven:3.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package 

FROM openjdk:8-jdk-alpine
RUN addgroup -S habeebcycle && adduser -S habeeb -G habeebcycle
USER habeeb:habeebcycle
LABEL maintainer="habeebcycle@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=/usr/src/app/target/*.jar
COPY ${JAR_FILE} /usr/app/service-api-gateway.jar
ENTRYPOINT ["java","-jar","/usr/app/service-api-gateway.jar"]
```

### Docker-compose file
```
version: '3'
services:
  service-1-api:
    build: ./service-1-api  # image: habeebcycle/gateway-service-1-api:v1
    ports:
      - 8081:8081

  service-2-api:
    build: ./service-2-api  # image: habeebcycle/gateway-service-2-api:v1
    ports:
      - 8082:8082     
  
  service-api-gateway:
    build: ./service-api-gateway  # image: habeebcycle/gateway-service-api-gateway:v1
    environment:
      - SERVER-1-API=http://service-1-api:8081/
      - SERVER-2-API=http://service-2-api:8082/
    restart: on-failure
    depends_on:
      - service-1-api
      - service-2-api
    ports:
      - 8080:8080
```