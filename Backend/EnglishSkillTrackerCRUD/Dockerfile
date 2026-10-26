# ==========================================
# Etapa 1: Desarrollo con Hot Reload (Dev)
# ==========================================
FROM maven:3.9-eclipse-temurin-21-alpine AS dev
WORKDIR /app

# Herramienta para detectar cambios en archivos (File Watching) en tiempo real
RUN apk add --no-cache inotify-tools

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

EXPOSE 8080 35729

# Observa cambios en /app/src y recompila automáticamente para que Spring Boot DevTools recargue
CMD ["sh", "-c", "(while inotifywait -r -e modify,create,delete,move /app/src; do mvn compile -DskipTests; done) & mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dspring.devtools.restart.enabled=true'"]

# ==========================================
# Etapa 2: Construcción (Build)
# ==========================================
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias para aprovechar la caché de Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y empaquetar la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# Etapa 3: Ejecución en Producción (Prod)
# ==========================================
FROM eclipse-temurin:21-jre-alpine AS prod
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
