Spring Cloud Circuit Breaker
==========================================================================

This is example code of how to use a Spring Circuit Breaker & Hystrix Dashboard.

NOTE: you must run the spring-microservce & config-server to run this demo (please review their respecive README files)

####=Steps to run Circuit Breaker Demo
1. Start the spring-microservice first -> Start -> run in Gradle -> spring-microservice -> bootRun
2. Start -> run in Gradle -> spring-circuitbreaker -> bootRun
3. http://localhost:8060/circuitbreak to test in browser -> output on browser will inform you when circuit breaker working or not
4. When testing Hystrix dynamic configuration and refreshing config from CodeHub POST - http://localhost:8060/refresh - see Hystrix Configuration Manager for more config details - https://github.com/Netflix/Hystrix/wiki/Configuration
5. Start Configuration Server to use Spring Cloud dynamic configuration (see instructions below)

####Steps to starting Configuration Server (local)
1. Start your messaging system - RabbitMQ is already configured in this Config Server POC (rabbitmq-server)
2. Start the Config Server -> run in Gradle -> config-server -> bootRun

####=Steps to run Hystrix Dashboard
1. To monitor the Hystrix Stream (Circuit Breaker) open http://localhost:8060/hystrix.html
2. Enter sream URL http://localhost:8060/hystrix.stream and click on Monitor Stream




