FROM postgres:14.1-alpine3.14
#ENV POSTGRES_USER=postgres
#ENV POSTGRES_PASSWORD=postgres
#ENV POSTGRES_DB=postgres

ENV TZ=Europe/Moscow

RUN apk add --no-cache bash

COPY ./database/init.sh /docker-entrypoint-initdb.d/
