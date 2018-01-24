FROM openjdk
MAINTAINER Kallol Das <kallol_das@optum.com>
ADD target/provider-service.jar provider-service.jar
ENTRYPOINT ["java", "-jar", "/provider-service.jar"]
EXPOSE 3333