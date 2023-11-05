FROM docker.io/eclipse-temurin:17-ubi9-minimal

RUN cp target/ticktick-to-todoist-migration.jar ticktick-to-todoist-migration.jar

CMD ["sh", "-c", "java ${JAVA_OPTS} -XX:+UseG1GC -XX:+UseStringDeduplication -XX:-UseGCOverheadLimit -XX:+DisableAttachMechanism -jar ticktick-to-todoist-migration.jar"]