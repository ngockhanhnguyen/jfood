#!/bin/sh
echo "********************************************************"
echo "Starting Eureka Server"
echo "********************************************************"
java -Dspring.profiles.active=dev -Xmx256m -jar /usr/local/discovery-service/discovery-service-0.0.1-SNAPSHOT.jar
