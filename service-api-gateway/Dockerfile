# FROM openjdk:8-jdk-alpine
# RUN addgroup -S habeebcycle && adduser -S habeeb -G habeebcycle
# USER habeeb:habeebcycle
# LABEL maintainer="habeebcycle@gmail.com"
# VOLUME /tmp
# EXPOSE 8080
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} service-api-gateway.jar
# ENTRYPOINT ["java","-jar","/service-api-gateway.jar"]


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