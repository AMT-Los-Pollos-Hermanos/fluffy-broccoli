FROM openjdk:11

ARG VERSION=0.0.1
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Los Pollos Hermanos" \
  org.opencontainers.image.vendor="HEIG" \
  org.opencontainers.image.source="https://github.com/AMT-Los-Pollos-Hermanos/fluffy-broccoli" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]