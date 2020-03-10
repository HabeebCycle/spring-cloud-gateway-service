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