# Ping-Pong game

![img](http://superfutures.co.uk/wp-content/uploads/2015/09/Superfutures_Design_Ping_Pong_Logo.jpg)

## Статус
[![Build Status](https://travis-ci.com/CaBuHoB/Ping-Pong.svg?branch=master)](https://travis-ci.com/CaBuHoB/Ping-Pong)
[![codecov](https://codecov.io/gh/CaBuHoB/Ping-Pong/branch/master/graph/badge.svg)](https://codecov.io/gh/CaBuHoB/Ping-Pong)
[![BCH compliance](https://bettercodehub.com/edge/badge/CaBuHoB/Ping-Pong?branch=master)](https://bettercodehub.com/)
[![CodeFactor](https://www.codefactor.io/repository/github/cabuhob/ping-pong/badge)](https://www.codefactor.io/repository/github/cabuhob/ping-pong)

## Запуск проекта
Для того чтобы запустить проект достаточно установить [docker](https://www.docker.com), затем прописать в консоли команды.

Если вы хотите использовать свою базу данных:
    
    $ docker pull cabuhob/ping-pong
    $ docker run -e DB_URL=jdbc:mysql://host:3306/database -e DB_USERNAME=username -e DB_PASSWORD=password cabuhob/ping-pong


Если вы хотите использовать базу данных docker:
    
    $ wget https://raw.githubusercontent.com/CaBuHoB/Ping-Pong/master/docker-compose.yml
    $ docker-compose up --build