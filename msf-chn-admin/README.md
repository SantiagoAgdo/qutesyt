**Generador Base de prueba  back-end spring-boot-microservicios **

# Spring-Boot - MICROSERVICES (blubancaback)


## Pre-requisitos
1. Tener instalado [Docker](https://docs.docker.com/engine/install/) y [Dockercompose](https://docs.docker.com/compose/install/)
2. Instalar JDK 21
3. Plugin maven

## Antecedentes
El proyecto está pensado para desplegar como un jar de forma independiente teniendo para esto el servidor de tomcat embebido, por lo que al construir se generara un **jar**.
Además contiene el docker-compose para el orquestamiento local de desarrollo. 

## Primeros pasos
### Installing OpenTelemetry/Auto-instumentation:
1. minikube start
2. kubectl apply -f https://github.com/jetstack/cert-manager/releases/download/v1.7.1/cert-manager.yaml
3. kubectl get pods --namespace cert-manager
4. kubectl apply -f https://github.com/open-telemetry/opentelemetry-operator/releases/latest/download/opentelemetry-operator.yaml
5. kubectl apply -f first-collector.yaml
6. kubectl apply -f first-instrumentation.yaml
7. kubectl apply -f first-pod-sending-span.yaml
8. kubectl get pods
9. kubectl exec -it msvc-test -- curl "http://localhost:8080/test/getGreeting/Jhon"
10. kubectl logs $(kubectl get pods -l app.kubernetes.io/name=simplest-collector -o jsonpath="{.items[0].metadata.name}")
11. kubectl apply -f prometheus.yaml
12. kubectl apply -f first-collector-updated.yaml
13. minikube service prometheus --url
14. kubectl apply -f grafana.yaml
15. minikube service grafana --url