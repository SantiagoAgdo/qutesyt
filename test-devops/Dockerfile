FROM amazoncorretto:21
WORKDIR /app

RUN yum update -y && \
    yum install -y shadow-utils curl && \
    yum clean all

RUN groupadd -g 1001 appuser && \
    useradd -r -u 1001 -g appuser appuser

COPY ../JAR/*.jar compiled.jar


RUN chown -R appuser:appuser /app

USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail http://localhost:8080 || exit 1

ENTRYPOINT ["java", "-jar", "/app/compiled.jar"]
