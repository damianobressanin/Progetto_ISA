FROM openjdk:11-jdk

WORKDIR /app

COPY target/isaCalcAgri-1.0-SNAPSHOT.jar /app

CMD ["java", "-jar", "/app/data/isaCalcAgri-1.0-SNAPSHOT.jar"]
