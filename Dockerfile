FROM alpine/java:21-jdk

WORKDIR /app

COPY build/libs/userserv-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]