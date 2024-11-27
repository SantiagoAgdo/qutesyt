FROM openjdk:21
WORKDIR /app

# Crear un usuario y grupo con UID y GID arbitrarios, por ejemplo, 1001
RUN groupadd -g 1001 appuser && useradd -r -u 1001 -g appuser appuser

COPY ../JAR/*.jar compiled.jar

# Asigna permisos al usuario creado para el directorio de trabajo
RUN chown -R appuser:appuser /app

# Ejecuta el contenedor como el usuario creado
USER appuser

EXPOSE 8080


HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail http://localhost:8080 || exit 1

# Instalar el agente de OpenTelemetry
RUN curl -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.27.0/opentelemetry-javaagent.jar -o opentelemetry-javaagent.jar

#ENTRYPOINT ["java", "-jar", "/app/compiled.jar"]
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-Dotel.service.name=msf-chn-admin", "-Dotel.exporter.otlp.endpoint=http://simplest-collector:4317", "-jar", "/app/compiled.jar"]