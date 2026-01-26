# ---------- build stage ----------
    # Use an image that already has:
    # - Gradle 8.5
    # - JDK 21
    # This stage is ONLY for building the jar, not running it.
FROM gradle:8.5-jdk21-alpine AS build

# Set the working directory inside the container
# All following commands run relative to /app
WORKDIR /app

# Copy only build configuration files first
# This allows Docker to cache dependency downloads
COPY build.gradle.kts settings.gradle.kts gradle.properties* /app/

# Copy Gradle wrapper files
# Ensures the build uses YOUR Gradle version, not whatever is in the image
COPY gradle /app/gradle
COPY gradlew /app/gradlew

# Download deps
RUN ./gradlew --no-daemon dependencies

# Now copy the actual application source code
COPY src /app/src

# Build the Spring Boot executable jar
RUN ./gradlew --no-daemon clean bootJar


# ---------- runtime stage ----------
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Spring Boot jar ends up in build/libs/*.jar
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Document that the container listens on port 8080
EXPOSE 8080

# Start the application when the container starts
ENTRYPOINT ["java","-jar","/app/app.jar"]
