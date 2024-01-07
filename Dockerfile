FROM maven:3.8.4-openjdk-17-slim

LABEL maintainer="Mykyta Kyselov (zeusmobilenick@gmail.com)"

# Copy the project files
COPY pom.xml /usr/local/vocalia/pom.xml
COPY src /usr/local/vocalia/src

# Set the working directory
WORKDIR /usr/local/vocalia

# Build the project with Maven
CMD ["mvn", "-Dmaven.multiModuleProjectDirectory=/usr/local/vocalia", "-Djansi.passthrough=true", "install"]
