FROM eclipse-temurin:21-jdk AS builder
LABEL authors="fabiofariascoutinho"

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]