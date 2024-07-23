FROM node:latest
 
WORKDIR /app

COPY ./package.json .
COPY ./package-lock.json .

COPY ./src .
COPY ./public .

RUN npm install