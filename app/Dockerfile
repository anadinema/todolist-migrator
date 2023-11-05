FROM docker.io/eclipse-temurin:17-ubi9-minimal

COPY target/ticktick-to-todoist-migration.jar ticktick-to-todoist-migration.jar

RUN ["java ${JAVA_OPTS} -jar ticktick-to-todoist-migration.jar"]