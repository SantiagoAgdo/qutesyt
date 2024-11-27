**Generador Base de prueba  back-end spring-boot-microservicios **

# Spring-Boot - MICROSERVICES (opinnoBase)

## Pre-requisitos
1. Tener instalado [Docker](https://docs.docker.com/engine/install/) y [Dockercompose](https://docs.docker.com/compose/install/)
2. Instalar JDK 21
3. Plugin maven

## Antecedentes
El proyecto está pensado para desplegar como un jar de forma independiente teniendo para esto el servidor de tomcat embebido, por lo que al construir se generara un **jar**.
Además contiene el docker-compose para el orquestamiento local de desarrollo. 

## Primeros pasos
1. Clonar el proyecto opinnoBase.
2. Crear una carpeta con el nombre del proyecto.
3. Abrir una venta powerSell posesionarse en la carpeta con el nombre del proyecto.
4. Ejecutar el comando yo opinno-springboot-project.
5. Responder a las preguntas yeoman configuradas.
6. Ejecutar **docker-compose up -d** para contruir contenedores en windows.

## Probar Endpoints de ejemplo, Usuario
1. http://localhost:8111/api/opinnoBase/user/
2. http://localhost:8111/api/opinnoBase/user/get/ById/__ID__
3. http://localhost:8083/user/add
   - input
    ```json
    {
        "name": "Clark Kent",
        "user": "ricardo"
    }
    ```

4. http://localhost:8111/api/opinnoBase/user/update
   - input
   ```json 
   {
	    "id":   4,
	    "name": "Arthur Curry",
	    "user": "luis",
	    "isActive": false
    }
    ```
5. http://localhost:8111/api/opinnoBase/user/delete/ById/__ID__
