# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the JAR
COPY target/*.jar app.jar

# Create a non-root user
# RUN groupadd -r spring && useradd -r -g spring spring

# Due to alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Default environment variables for the port
EXPOSE 8083

ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]
