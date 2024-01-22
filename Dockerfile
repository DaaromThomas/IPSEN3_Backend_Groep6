# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

# Stage 2: Create a minimal JRE image and copy the JAR file
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/vdLelie-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "vdLelie-0.0.1-SNAPSHOT.jar"]
