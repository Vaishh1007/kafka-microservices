# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the JAR
COPY target/*.jar app.jar

# Create a non-root user
RUN groupadd -r spring && useradd -r -g spring spring
USER spring

# Default environment variables for the port
EXPOSE 8081

ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]