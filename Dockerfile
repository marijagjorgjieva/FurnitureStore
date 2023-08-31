# Use the official OpenJDK base image
FROM openjdk:19-jdk-alpine

ARG JAR_FILE=target/*.jar

# Copy the compiled JAR file from your target directory to the container
COPY target/OnlineShop-1.0-SNAPSHOT.jar app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]

