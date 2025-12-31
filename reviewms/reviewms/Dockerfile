FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/reviewms-0.0.1-SNAPSHOT.jar.original app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
