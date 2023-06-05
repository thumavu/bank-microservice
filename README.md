# bank-microservice


## To run the service execute the below commands
 - mvn clean install
 - java -jar target/Bank-0.0.1-SNAPSHOT.war
 - curl -X POST -F "fileName=file.txt" -F "fileContent=@/path/to/file.txt" http://localhost:8080/hub/send-file/2