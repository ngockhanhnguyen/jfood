FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/discovery-service
ADD discovery-service-0.0.1-SNAPSHOT.jar /usr/local/discovery-service/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
