FROM openjdk:17-jdk-slim
EXPOSE 8081
WORKDIR /back-end
ADD target/back-end.jar back-end.jar
ENTRYPOINT ["java","-jar","/back-end.jar"]