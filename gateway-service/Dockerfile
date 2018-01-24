FROM openjdk
MAINTAINER Kallol Das <kallol_das@optum.com>
ADD target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]
EXPOSE 8765