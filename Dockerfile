FROM docker.io/openjdk:17.0.2-slim
MAINTAINER Miguel Angel Sanchez MArtinez(masanmar70@gmail.com)
# a default value
ENV MONGO_HOSTNAME localhost
ENV MONGO_PORT 27017
ENV MONGO_AUTHDB admin
ENV MONGO_DB videojuegodb
ENV MONGO_USER usuario_owner
ENV MONGO_PWD usuario_password
ENV TOMCAT_PORT 8084
EXPOSE 8084
ARG JAR_FILE=target/*.jar
COPY target/*.jar app.jar
CMD ["java", "-jar", "/app.jar"]
