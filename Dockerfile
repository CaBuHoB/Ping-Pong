FROM openjdk:8-jdk-alpine
MAINTAINER Maxim Savinov <cabuhob@gmail.com>
WORKDIR /usr/src/myapp
COPY target/ping-pong*.jar app.jar
CMD java -jar app.jar