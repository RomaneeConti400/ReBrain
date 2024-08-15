FROM openjdk:22-jdk
EXPOSE 8081
ARG JAR_FILE=target/*.jar
COPY ./target/ReBrain-0.0.1-SNAPSHOT.jar /app/lib/app.jar
ENTRYPOINT ["java", "-jar", "/app/lib/app.jar"]