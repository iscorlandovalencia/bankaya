FROM openjdk:17
LABEL maintainer="pokeapi"
ADD target/pokeapi-0.0.1-SNAPSHOT.jar pokeapi-docker.jar
ENTRYPOINT  ["java", "-jar", "pokeapi-docker.jar"]