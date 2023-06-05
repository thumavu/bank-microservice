FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY Bank-0.0.1-SNAPSHOT.war .
COPY application.properties .
COPY pom.xml .

RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

EXPOSE 8080

CMD ["java", "-jar", "Bank-0.0.1-SNAPSHOT.war"]