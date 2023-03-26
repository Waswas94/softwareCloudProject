# Use the official Java base image with the specified version of JDK
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file (built with `mvn package`) into the container
COPY target/inventoryMicroservice-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the microservice port
EXPOSE 8080

# Start the microservice
ENTRYPOINT ["java", "-jar", "/app/app.jar"]