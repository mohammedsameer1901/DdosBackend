FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17

WORKDIR /app

# 🔥 Copy ALL jars (no name issue)
COPY --from=build /app/target /app/target

# 🔥 Run jar dynamically
CMD ["sh", "-c", "java -jar /app/target/*.jar"]