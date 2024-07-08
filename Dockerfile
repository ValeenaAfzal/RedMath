FROM openjdk:21-jdk-slim

COPY target/redMath*.jar lecture-be.jar
ENTRYPOINT ["java","-jar","SecondDay.jar"]