FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8081
ADD back-end/target/back-end.jar back-end.jar
ENTRYPOINT ["java","-jar","/back-end.jar"]