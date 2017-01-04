# System workspace

The whitespace consists of infrastructural bundles and microservices. 

To spawn a "system" cluster in __Docker__:
- Set up environment variable `JAVA_OPTS` containing all required JVM properties to bootstrap a certain microservice. 
  As an alternative you can specify this variable into [`.env` file](https://docs.docker.com/compose/environment-variables/).   
- Run `docker-compose up -d` in the project root repository.

After successful bootstrup following services become available

|Service|host:port (default)|
|---|---|
|RabbitMQ|`${docker.host}:15672`|
|Eureka|`${docker.host}:8761`|
|Cloud Configuration|`${docker.host}:8888`|
|Zuul Proxy (API Gateway)|`${docker.host}:8100`|