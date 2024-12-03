FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /target/user_service-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "user_service-0.0.1-SNAPSHOT.jar"]