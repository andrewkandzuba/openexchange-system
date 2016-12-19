# Central Configuration Service

The centralized configuration service on *Spring Cloud Config Server* framework.
The service must register itself to the **_Eureka Service Registry Service_** to enable *Discovery First Bootstrap* to all clients.   

## Properties 

| Name | Default value | Description | 
| --- | :---: | --- |
| spring.cloud.config.server.git.uri | | GIT repository url |
| security.user.password | | Password of a global Configuration Service | 
| eureka.instance.nonSecurePort | 8888 |  Local binding port |
| eureka.instance.metadataMap.user | | User of a global Configuration Service. To be registered into Service Registry. | 
| eureka.instance.metadataMap.password | | Password of a global Configuration Service. To be registered into Service Registry. | 

## Docker

When run with __docker__ user following environment variable:

|Name|Description|
|:-:|---|
|JAVA_OPTS|Pass properties to JVM|
|HTTP_PROXY|Provider __https.proxyHost__ and __https.proxyPort__ values to JVM (if any)|  
