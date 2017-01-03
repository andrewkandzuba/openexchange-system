# System whitespace

The whitespace consists of infrastructural bundles and microservices. 

To spawn a "system" cluster in __Docker__:
- Set up environment variable `GIT_CONFIG_URL` leads to GIT repo with whole microservices configurations you plan to use 
- Run `docker-compose up -d` in the project root repository

After that following services becomes available:
- RabbitMQ `${docker.host}:15672`
- Eureka `${docker.host}:8761`
- Cloud Configuration `${docker.host}:8888`
- Zuul Proxy `${docker.host}:8101`