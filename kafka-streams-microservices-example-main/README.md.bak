# Spring Kafka Streams using Spring Cloud Streams

## Endpoint
- http://localhost:8080/domain/lookup/facebook - to pull all facebook related web domain names

##
- Kafka Servers Start (local: eg. C:\kafka_2.13-3.7.0)
1) .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
2) .\bin\windows\kafka-server-start.bat .\config\server.properties
![Kafka-Local-Services](Kafka-Local-Services.png)
## 
- Accesssing Kafka through Conduktor (download it https://www.conduktor.io/get-started/desktop/ and create account free).
- Conduktor Configure the desktop connection (Cluster Name: local, Bootstrap Server: localhost:9092, Zookeeper: localhost:2123), test the connect and save
- ![Conductor-Kafa-UI](Conductor-Kafa-UI.png)

## Microservices
- 'domain-crawler' - uses Spring Kafka (http://localhost:8080/domain/lookup/facebook) as Producer
- 'domain-processor' - uses Spring Cloud Stream with Kafka Streams binder as Consumer.
- 'domain-service' - uses Spring Cloud Stream with Kafka Streams binder as Consumer. Consumer Listner added to save data in MySQL db. To cehck all domain list (http://localhost:8082/api/domains/v1).

## Architecture
![architecture](architecture.png)