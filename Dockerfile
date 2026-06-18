FROM eclipse-temurin:21-jdk
LABEL authors="fabiofariascoutinho"

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active"]