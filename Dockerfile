# Etapa 1: Construcción del proyecto con Gradle y Java 17
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Copiar todo el código del proyecto
COPY . .

# Construir el proyecto (sin ejecutar los tests para ahorrar tiempo)
RUN gradle clean build -x test

# Etapa 2: Imagen ligera solo para ejecutar la app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto (ajústalo si tu app usa otro)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
