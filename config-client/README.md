Spring Cloud Configuration Client
=================================

This is the Spring Cloud Config Client -> see for code details -> Application class

####Configuration Steps with Config Client -> bootstrap.yml
1. Add config details for Config Server -> default settings localhost:8888

####Local Testing Steps
1. Start your messaging system - RabbitMQ is already configured for local test (rabbitmq-server)
2. Start the Config Server -> run in Gradle -> config-server -> bootRun
3. Start the Config Client -> run in Gradle -> config-client -> bootRun
3. Open Browser with URL -> http://localhost:8080/home



