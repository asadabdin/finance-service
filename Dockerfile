# Dockerfile

FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build jacocoTestReport --no-daemon

FROM openjdk:8-jre-slim

EXPOSE 8081

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/finance-service-application.jar

ENTRYPOINT ["java","-jar","/app/finance-service-application.jar"]
