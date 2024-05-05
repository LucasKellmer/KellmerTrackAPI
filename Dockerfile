FROM ubuntu:latest AS BUILD

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build /target/kellmertrack-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=kellmer

ENTRYPOINT [ "java", "-jar", "app.jar" ]
