FROM openjdk:11-jdk

WORKDIR /app/data

COPY target/isaCalcAgri-1.0-SNAPSHOT.jar /app/data/isaCalcAgri-1.0-SNAPSHOT.jar

CMD ["java", "-jar", "/app/data/isaCalcAgri-1.0-SNAPSHOT.jar"]
