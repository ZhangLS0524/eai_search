#!/bin/bash

echo "Starting deployment process..."

# Check if required environment variables are set
echo "Checking environment variables..."
if [ -z "$SPRING_DATASOURCE_URL" ]; then
    echo "ERROR: SPRING_DATASOURCE_URL is not set"
    exit 1
fi

if [ -z "$SPRING_DATASOURCE_USERNAME" ]; then
    echo "ERROR: SPRING_DATASOURCE_USERNAME is not set"
    exit 1
fi

if [ -z "$SPRING_DATASOURCE_PASSWORD" ]; then
    echo "ERROR: SPRING_DATASOURCE_PASSWORD is not set"
    exit 1
fi

echo "Environment variables are set correctly"

# Build the application
echo "Building application..."
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "ERROR: Build failed"
    exit 1
fi

echo "Build successful"

# Test database connection
echo "Testing database connection..."
java -jar target/project-0.0.1-SNAPSHOT.jar --spring.profiles.active=production &
APP_PID=$!

# Wait for application to start
sleep 30

# Test health endpoint
HEALTH_RESPONSE=$(curl -s http://localhost:8080/health)
echo "Health check response: $HEALTH_RESPONSE"

# Kill the application
kill $APP_PID

echo "Deployment script completed" 