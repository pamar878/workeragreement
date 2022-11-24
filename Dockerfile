FROM openjdk:8u252-jdk
ARG profile
ENV profileArg=$profile

WORKDIR /app
COPY /target/provisioning-as400-writter-qn-1.0.0.jar target/provisioning-as400-writter-qn-1.0.0.jar
COPY /config/* /config/

RUN apt-get update && apt-get install -y vim

VOLUME ./logs:/var/log

EXPOSE 8080

CMD java -Dspring.profiles.active=${profileArg} -jar target/provisioning-as400-writter-qn-1.0.0.jar --spring.config.location=file:///config/