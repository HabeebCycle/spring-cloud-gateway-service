# FROM openjdk:8-jdk-alpine
# RUN addgroup -S habeebcycle && adduser -S habeeb -G habeebcycle
# USER habeeb:habeebcycle
# LABEL maintainer="habeebcycle@gmail.com"
# VOLUME /tmp
# EXPOSE 8081
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} service-1-api.jar
# ENTRYPOINT ["java","-jar","/service-1-api.jar"]


FROM maven:3.5-jdk-8 AS build  
COPY src /usr/code/app/src  
COPY pom.xml /usr/code/app  
RUN mvn -f /usr/code/app/pom.xml clean package 

FROM openjdk:8-jdk-alpine
RUN addgroup -S habeebcycle && adduser -S habeeb -G habeebcycle
USER habeeb:habeebcycle
LABEL maintainer="habeebcycle@gmail.com"
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=/usr/code/app/target/*.jar
COPY ${JAR_FILE} /usr/app/service-1-api.jar
ENTRYPOINT ["java","-jar","/usr/app/service-1-api.jar"]