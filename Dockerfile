FROM openjdk:17
LABEL maintainer="pokeapi"
EXPOSE 8080
ADD target/pokeapi-0.0.1-SNAPSHOT.jar pokeapi.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} pokeapi.jar
ENTRYPOINT  ["java", "-jar", "pokeapi.jar"]