#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start  on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z $EUREKASERVER_HOST $EUREKASERVER_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Eureka Server has started"

echo "********************************************************"
echo "Starting Zuul Service with Eureka Endpoint:  $EUREKASERVER_URI";
echo "********************************************************"
java -Dspring.profiles.active=$PROFILE -Xmx256m -jar /usr/local/zuul-service/zuul-service-0.0.1-SNAPSHOT.jar
