FROM openjdk
MAINTAINER Kallol Das <kallol_das@optum.com>
ADD target/discovery-service.jar discovery-service.jar
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]
EXPOSE 8761