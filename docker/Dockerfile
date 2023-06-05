FROM openjdk:17-jdk-slim

WORKDIR /app

COPY Bank-0.0.1-SNAPSHOT.war .
COPY application.properties .
COPY pom.xml .

RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

CMD ["java", "-jar", "Bank-0.0.1-SNAPSHOT.war"]