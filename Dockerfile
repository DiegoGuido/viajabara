# Etapa de compilación
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa de ejecución
FROM openjdk:17.0.2-jdk
COPY --from=builder /app/target/viajabara-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080

# Agrega el script wait-for-it.sh al contenedor
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Espera a que la base de datos esté disponible antes de ejecutar la aplicación
CMD /wait-for-it.sh -t 120 db:3306 -- java -jar /app.jar