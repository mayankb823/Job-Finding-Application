FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/jobms-0.0.1-SNAPSHOT.jar.original docker build -t job-service .
 app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]