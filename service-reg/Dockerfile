FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/service-reg-0.0.1-SNAPSHOT.jar.original
 docker build -t service-reg .
 app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]