FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} geradornotafiscal-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/geradornotafiscal-0.0.1-SNAPSHOT.jar"]