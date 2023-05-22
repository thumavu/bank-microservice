FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/Bank-0.0.1-SNAPSHOT.war /app/Bank-0.0.1-SNAPSHOT.war
COPY application.properties /app/application.properties

EXPOSE 8080

CMD ["java", "-jar", "/app/Bank-0.0.1-SNAPSHOT.war"]