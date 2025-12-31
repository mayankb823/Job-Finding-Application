FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/reviewms-0.0.1-SNAPSHOT.jar.original docker build -t review-service .
 app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]