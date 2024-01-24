FROM openjdk:11-jdk

WORKDIR /app

# Cartella per volume
RUN mkdir /app/data

COPY isaCalcAgri/target/isaCalcAgri-1.0-SNAPSHOT.jar /app

CMD ["java", "-cp", "/app/isaCalcAgri-1.0-SNAPSHOT.jar", "it.isa.progetto.Main"]