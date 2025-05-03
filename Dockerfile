FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/back-end-0.0.1.jar back-end-0.0.1.jar
ENTRYPOINT ["java","-jar","/back-end-0.0.1.jar"]