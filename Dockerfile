# Stage 1: Build with Maven (Java 25)
FROM maven:3.9.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run with Java 25
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Copy generated jar from build stage
COPY --from=build /app/target/fitness-monolith-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM eclipse-temurin:25-jdk
#
#WORKDIR /app
#
#COPY target/fitness-monolith-0.0.1-SNAPSHOT.jar app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "app.jar"]
