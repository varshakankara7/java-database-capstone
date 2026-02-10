# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /build

# Copy only the pom.xml and source code from the 'app' directory
COPY app/pom.xml .
COPY app/src ./src

# Compile and package the application, skipping tests for speed
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy only the built JAR file from the 'build' stage
COPY --from=build /build/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
