FROM eclipse-temurin:11-jdk-alpine as build

EXPOSE 8089

ADD /target/achat.jar /achat.jar

ENTRYPOINT ["java","-jar","/achat.jar"]
