FROM openjdk:17-jdk

WORKDIR /relationship-center

COPY target/relationship-center-0.0.1-SNAPSHOT.jar /relationship-center/relationship-center.jar

CMD ["java", "-jar", "/relationship-center/relationship-center.jar"]