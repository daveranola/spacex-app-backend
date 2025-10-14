# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# copy the fat jar built above (adjust name if different)
COPY --from=build /app/target/*SNAPSHOT.jar app.jar

# Render sets PORT; Spring will read it via --server.port below
EXPOSE 8080

# Use prod profile and bind to Render's PORT
CMD ["java","-jar","app.jar","--spring.profiles.active=prod","--server.port=${PORT}"]