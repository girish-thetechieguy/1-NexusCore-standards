FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/back-end.jar back-end.jar
ENTRYPOINT ["java","-jar","/back-end.jar"]