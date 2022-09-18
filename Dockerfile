FROM openjdk:17.0.2-jdk
ADD . /src
WORKDIR /src
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/DiscordBackend-0.0.1-SNAPSHOT.jar"]