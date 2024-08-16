# Stage 1: Build the application
FROM gradle:7.6-jdk17 AS build
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Copy source code
COPY src ./src

# Build the application
RUN ./gradlew build --no-daemon


########################################################################################

# Stage 2: Run the application
FROM openjdk:17-alpine
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/demo-aws.jar ./demo-aws.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "demo-aws.jar"]
