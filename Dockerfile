# Step 1: Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the executable JAR file from the target directory to the container
# Ensure you have run 'mvn clean package' to generate this file
COPY target/healthcare-backend-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port the app runs on (usually 8080 for Spring Boot)
EXPOSE 8080

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
