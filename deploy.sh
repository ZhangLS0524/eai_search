#!/bin/bash

# Make Maven wrapper executable
chmod +x ./mvnw

# Clean and package the application
./mvnw clean package -DskipTests

# Start the application
java -jar target/project-0.0.1-SNAPSHOT.jar 