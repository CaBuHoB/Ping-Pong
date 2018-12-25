FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/myapp
COPY target/ping-pong*.jar app.jar
CMD java -jar app.jar