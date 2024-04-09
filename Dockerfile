# 베이스 스테이지: 필수 OS 및 도구 설치
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

# 빌드 스테이지에서 생성된 jar 파일을 실행 스테이지로 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app/app.jar"]
