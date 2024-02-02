#!/bin/bash

docker run -d \
-p 8095:8080 \
    -e SPRING_MAIL_USERNAME=\
    -e SPRING_MAIL_PASSWORD= \
    -e JWT_AUTH_CONVERTER_RESOURCEID=shopping-list-app \
    -e SPRING_DATASOURCE_URL= \
    -e SPRING_DATASOURCE_USERNAME=\
    -e SPRING_DATASOURCE_PASSWORD=\
repo_url:5000/shopping-list/server:1.0