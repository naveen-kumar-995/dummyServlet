# Use OpenJDK 21 as the base image
FROM eclipse-temurin:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/dummyServlet-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8480

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
