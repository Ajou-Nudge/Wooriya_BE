FROM ubuntu:latest as base
WORKDIR /app

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk-headless gradle && \
    apt-get clean

FROM base as dependencies
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew && ./gradlew --no-daemon dependencies

FROM dependencies as builder
COPY src src

RUN ./gradlew --no-daemon build

FROM openjdk:17
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
