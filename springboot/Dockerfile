FROM openjdk:11

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER-URI=https://keycloak.lewes.family/realms/LewesFamily/protocol/openid-connect/certs
EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]