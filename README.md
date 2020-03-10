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
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_3
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
 
FROM openjdk:8-jdk-alpine
LABEL maintainer="habeebcycle@gmail.com"
EXPOSE 8081
COPY --from=MAVEN_3 /tmp/target/*.jar /service-1-api.jar
ENTRYPOINT ["java","-jar","/service-1-api.jar"]
```

### Service-2-api
```
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_3
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
 
FROM openjdk:8-jdk-alpine
LABEL maintainer="habeebcycle@gmail.com"
EXPOSE 8082
COPY --from=MAVEN_3 /tmp/target/*.jar /service-2-api.jar
ENTRYPOINT ["java","-jar","/service-2-api.jar"]
```

### service-api-gateway
```
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_3
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
 
FROM openjdk:8-jdk-alpine
LABEL maintainer="habeebcycle@gmail.com"
EXPOSE 8080
COPY --from=MAVEN_3 /tmp/target/*.jar /service-api-gateway.jar
ENTRYPOINT ["java","-jar","/service-api-gateway.jar"]
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