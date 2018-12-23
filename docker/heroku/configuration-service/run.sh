#!/bin/sh

EUREKASERVER_URI=https://jfood-eureka.herokuapp.com/eureka
EUREKASERVER_HOST=jfood-eureka.herokuapp.com
EUREKASERVER_PORT=80
echo "********************************************************"
echo "Waiting for the eureka server to start  on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z $EUREKASERVER_HOST $EUREKASERVER_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Eureka Server has started"

echo "********************************************************"
echo "Starting Configuration Service with Eureka Endpoint:  $EUREKASERVER_URI";
echo "********************************************************"
java -Dspring.profiles.active=dev -Xmx256m -jar /usr/local/configuration-service/configuration-service-0.0.1-SNAPSHOT.jar
