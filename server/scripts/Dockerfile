FROM openjdk:17
ARG JAR_FILE=/home/ubuntu/server/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=server","-jar","/app.jar"]
