## Pasos para ejecutar el proyecto:

Ejecutar en local
1. Ejecutar el comando mvn install en el módulo padre (micro-blubancaback).
2. Ir a la carpeta del módulo especí­fico (Ejemplo admin)
3. Definir las variables de entorno especificados en los application.yml de cada módulo
4. En el módulo a especí­fico, ejecutar el comando mvn install.
5. En el módulo a especí­fico, ejecutar el comando mvn spring-boot:run.
6. Ejecutar primero el configServer luego Eureka (ServiceDiscovery), el ZuulServer y por ultimo el micro servicio
7. Tener instalado Mysql
      - si se instala a travez de docker tener en cuenta que el micro admin en su application.yml tiene por defecto el puerto 33060 para la base de datos, si pone otro al ejecutar el docker run, cambiar ese puerto
      - si se instala mediante algun ejecutable, cambiar en el application yml del micro admin el valor del puerto a la base de datos

Ejecutar dockers

1. Eureka
   1. docker buildx build -t servicediscovery --build-arg DOCKER_BUILDKIT=1 --build-arg BUILDKIT_STEP_LOG_MAX_SIZE=10000000 --build-arg BUILDKIT_STEP_LOG_MAX_SPEED=10000000 .
2. docker run -p 8761:8761 servicediscovery
	En los application.properties actualizar la ip de la variable
	eureka.client.serviceUrl.defaultZone por la ip de la red donde se ejecuta el eureka
2. Zuul Server
      1. docker buildx build -t zuulserver --build-arg DOCKER_BUILDKIT=1 --build-arg BUILDKIT_STEP_LOG_MAX_SIZE=10000000 --build-arg BUILDKIT_STEP_LOG_MAX_SPEED=10000000 .
      2. docker run -p 8111:8111 zuulserver 
3. Users module
      3. docker buildx build -t users --build-arg DOCKER_BUILDKIT=1 --build-arg BUILDKIT_STEP_LOG_MAX_SIZE=10000000 --build-arg BUILDKIT_STEP_LOG_MAX_SPEED=10000000 .
      4. docker run -p 8081:8081 users

## Variables de Entorno usadas
###### admin
- spring.datasource.url=${DB_CONNECTION} (URL de conexión de BD)
- spring.datasource.username=${DB_USER} (Usuario a autenticar en BD)
- spring.datasource.password=${DB_PWD} (Password de autenticación BD)
###### Config Provider
- spring.cloud.config.server.git.uri=${GIT_URI} (URL de servidor de 
configuración)
- spring.cloud.config.server.git.username=${GIT_USERNAME} (Username de
usuario con acceso al repositorio)
- spring.cloud.config.server.git.password=${GIT_PASSWORD} (Password de 
acceso a repositorio [En BitBucket es necesario crear un App Password])
- spring.cloud.config.server.git.default-label=${GIT_BRANCH} (Branch o Rama a 
ser usada del repo)
###### Service Discovery
- #server.port = ${PORT} (Puerto donde correrá el servidor)
###### Zuul Server
- zuul.routes.admin.path = ${ROUTE_URL_admin}
- zuul.routes.admin.url = ${ROUTE_PATH_admin}
- eureka.client.serviceUrl.defaultZone = ${EUREKA_SERVER}
