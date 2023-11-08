FROM maven:3.8.2-openjdk-17-slim AS build
COPY app/src /docker/application/src
COPY app/pom.xml /docker/application
RUN mvn -B -f /docker/application/pom.xml clean package

FROM docker.io/eclipse-temurin:17-ubi9-minimal
COPY --from=build /docker/application/target/ticktick-to-todoist-migration.jar /run/ticktick-to-todoist-migration.jar
CMD ["sh", "-c", "java ${JAVA_OPTS} -XX:+UseG1GC -XX:+UseStringDeduplication -XX:-UseGCOverheadLimit -XX:+DisableAttachMechanism -jar /run/ticktick-to-todoist-migration.jar"]
