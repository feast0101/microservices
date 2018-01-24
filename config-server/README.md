Spring Cloud Configuration Server
=================================

This is the Spring Cloud Config Server

####Set up Steps
1. Download & Install Erlang  - http://www.erlang.org/downloads & Window Installs - http://erlang.org/download/otp_win64_19.1.exe (ERLANG_HOME environment variable required for RabbitMQ)
2. Install RabbitMQ - https://www.rabbitmq.com/download.html
3. Configure Config Server

####Configuration Steps with Config Server -> configserver.yml
1. Add config details for RabbitMQ - host:port & security credentials (local test already configured)
2. Add centralized version/source control system URI pointing to VC repository

####Local Testing Steps
1. Start your messaging system - RabbitMQ is already configured in this Config Server POC (rabbitmq-server)
2. Start the Config Server -> run in Gradle -> config-server -> bootRun


