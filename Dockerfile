FROM maven:3.8.4-openjdk-17-slim

LABEL maintainer="Mykyta Kyselov (zeusmobilenick@gmail.com)"

# Copy the project files
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src

# Set the working directory
WORKDIR /usr/local/service

# Build the project with Maven
CMD ["mvn", "-Dmaven.multiModuleProjectDirectory=/usr/local/service", "-Djansi.passthrough=true", "install"]
