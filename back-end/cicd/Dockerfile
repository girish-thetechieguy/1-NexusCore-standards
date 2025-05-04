# Use an appropriate base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /back-end

RUN pwd && ls -l

# Copy the JAR file from the back-end target directory
COPY target/back-end.jar back-end.jar

# Expose the application port
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "back-end.jar"]