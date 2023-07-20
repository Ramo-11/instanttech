FROM node:20 as build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY front-end .

RUN npm run build

FROM openjdk:17-oracle

WORKDIR /app

COPY build/libs/instanttech-1.0.0.jar .

EXPOSE 8080
EXPOSE 3000

ENTRYPOINT ["java", "-jar", "instanttech-1.0.0.jar"]