# 빌드 스테이지
FROM ubuntu:latest as builder
WORKDIR /app

# 필수 도구 설치
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk-headless gradle && \
    apt-get clean

# 소스 코드와 Gradle wrapper를 컨테이너로 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 권한 수정 및 애플리케이션 빌드
RUN chmod +x ./gradlew && ./gradlew build

# 실행 스테이지
FROM openjdk:17
WORKDIR /app

# 빌드 스테이지에서 생성된 jar 파일을 실행 스테이지로 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app/app.jar"]
