# Stage 1
FROM node:18.7.0-alpine as build-step

RUN mkdir -p /app
WORKDIR /app
COPY package.json /app

RUN npm install -g npm@8.17.0
RUN npm install --legacy-peer-deps

COPY . /app

ARG configuration=production

RUN npm run build -- --output-path=./dist/out --configuration $configuration

# Stage 2
FROM nginx:1.23.1-alpine

COPY --from=build-step /app/dist/out /usr/share/nginx/html
#COPY --from=build-stage /nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80