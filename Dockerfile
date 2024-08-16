# Stage 1: Build the application
FROM gradle:jdk17 AS build
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./

# Copy source code
COPY src ./src

# Build the application
RUN ./gradlew build --no-daemon


########################################################################################

# Stage 2: Run the application
FROM openjdk:17-alpine
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/reposter-0.0.1-SNAPSHOT.jar ./reposter.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "reposter.jar"]
