#!/bin/sh

# MySQL 준비될 때까지 대기
while ! nc -z mysql-container 3306; do
  echo "Waiting for MySQL..."
  sleep 2
done

# Spring Boot 애플리케이션 실행
java -jar /app/app.jar
