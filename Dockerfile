FROM eclipse-temurin:11-jdk-alpine as build

EXPOSE 8089

ADD target/achat-1.0.jar /achat-1.0.jar

ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
