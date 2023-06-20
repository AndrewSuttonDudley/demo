# syntax=docker/dockerfile:1

FROM gradle:8.0.1-jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src/
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM amazoncorretto:17-alpine-full

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]
