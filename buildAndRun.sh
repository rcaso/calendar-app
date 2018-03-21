#!/bin/sh
mvn clean package && docker build -t com.shava/calendar-app .
docker rm -f calendar-app || true && docker run -d -p 8080:8080 -p 4848:4848 --name calendar-app com.shava/calendar-app 
