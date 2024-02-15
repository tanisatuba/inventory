#
# Build stage
#
FROM maven:3.6.0-jdk-11 AS build
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre
COPY --from=build /home/app/target/flagship-1.0-SNAPSHOT.jar /usr/local/lib/flagship-1.0-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/usr/local/lib/flagship-1.0-SNAPSHOT.jar"]