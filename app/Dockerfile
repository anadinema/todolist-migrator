FROM maven:3.8.2-openjdk-17-slim AS build
COPY src /docker/build/src
COPY pom.xml /docker/build
RUN mvn -B -f /docker/build/pom.xml clean package

FROM docker.io/eclipse-temurin:17-ubi9-minimal
COPY --from=build /docker/build/target/ticktick-to-todoist-migration.jar /docker/ticktick-to-todoist-migration.jar
CMD ["sh", "-c", "java ${JAVA_OPTS} -XX:+UseG1GC -XX:+UseStringDeduplication -XX:-UseGCOverheadLimit -XX:+DisableAttachMechanism -jar /docker/ticktick-to-todoist-migration.jar"]
