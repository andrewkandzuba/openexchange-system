# Central Configuration Service

The centralized configuration service on __Spring Cloud Config Server__ framework.
The service must register itself to the __Eureka Service Registry Service__ to enable __Discovery First Bootstrap__ to all clients.   

## Bootstrap modes supported:

- spring-boot
- Cloudfoundry
- Docker

## Properties 

| Name | Default value | Description | 
| --- | :---: | --- |
| **spring.cloud.config.server.git.uri** | | URL of GIT repository containing external configuretion |
| **security.user.password** | _changeit_ | Password of a global Configuration Service | 
| **eureka.client.serviceUrl.defaultZone** | _localhost:8761_ | URI of Eureka registry service |
| **eureka.instance.nonSecurePort** | _8888_ |  Local binding port |
| **eureka.instance.metadataMap.user** | _user_ | User of a global Configuration Service. To be registered into Service Registry. | 
| **eureka.instance.metadataMap.password** | _changeit_ | Password of a global Configuration Service to be registered into Service Registry. |
| **spring.rabbitmq.host** | _localhost_ | The host of Apache RabbitMQ service bus | 
| **spring.rabbitmq.port** | 5672  | The port of Apache RabbitMQ service bus | 

## Docker

When run with __docker__ user following environment variable:

|Name|Mandatory|Description|
|---|:---:|---|
|**JAVA_OPTS**|x|Contains JVM system properties. At least empty string must be provided.|
