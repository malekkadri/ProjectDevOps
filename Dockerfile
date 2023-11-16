FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/achat-1.0.0.jar achat.jar
ENTRYPOINT ["java","-jar","/achat.jar"]