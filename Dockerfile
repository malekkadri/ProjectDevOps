FROM eclipse-temurin:11-jdk-alpine

COPY /target/achat.jar /achat.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/achat.jar"]