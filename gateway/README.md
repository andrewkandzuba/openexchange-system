# Zuul Proxy API Gateway Service

The centralized API entrypoint for all microservices registered to __Spring Cloud Netflix Eureka__ framework.

## Bootstrap modes supported:

- spring-boot
- Docker

## Docker

When run with __docker__ user following environment variable:

|Name|Mandatory|Description|
|---|:---:|---|
|**JAVA_OPTS**|x|Contains JVM system properties. At least empty string must be provided.|
