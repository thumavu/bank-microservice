# bank-microservice

## Requirements
 - openjdk 17
 - maven
 - docker and docker-compose


## To run the service execute the below commands
 - mvn clean install
 - java -jar target/Bank-0.0.1-SNAPSHOT.war
 - curl -X POST -F "fileContent=@file.txt" http://localhost:8082/bank/send-file/2