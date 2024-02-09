FROM openjdk:21
EXPOSE 8091
ADD target/spring-security.jar spring-security.jar
ENTRYPOINT ["java","-jar","/spring-security.jar"]