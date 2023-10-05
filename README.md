# diplo_cloud_videojuego_service
Repositorio para el microservicio de videojuegos

# Configuración del ambiente

Se deben definir las siguientes variables de ambiente

|Propiedad             | Valor                                                           |
|----------------------|-----------------------------------------------------------------|
|MONGO_HOSTNAME        | IP del la abse de datos de mongodb                              |
|MONGO_PORT		       | Puerto de la base de datos mongodb                              |
|MONGO_AUTHDB          | Base de datos administrativa de mongodb                         |
|MONGO_DB              | Nombre de la base de datos de la aplicación                     |
|MONGO_USR             | Usuario para realizar la conexión a mongodb                     |
|MONGO_PWD             | Contraseña del usuario para realizar la conexión a momgo DB     |
|TOMCAT_PORT           | 8084                                                            |

# Dependencias

Esta aplicación requiere para su funcionamiento de una conexión a la base de datos MongoDB, versión 4.2.21

# REST API

Estos son los servicios para la administración de videojuegos de la aplicación PIXUP

## Obtener lista de videojuegos

### Request

`GET /api/videojuego`

    curl -i -H 'Accept: application/json' http://localhost:8084/api/videojuego

### Response

HTTP/1.1 200 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 05 Oct 2023 17:43:50 GMT

[{"id":"651db33851ac6e4182aef187","nombre":"videojuego1","genero":"aventura","clasificación":"para todo publico"}]

## Registrar nuevo videojuego

### Request

`POST /api/videojuego`

curl --header "Content-Type: application/json" \
	 --request POST \
	 --data '{"nombre":"videojuego1","genero":"aventura","clasificación":"para todo publico"}' \
	 http://localhost:8084/api/videojuego	

### Response

    {"id":"651db33851ac6e4182aef187","nombre":"videojuego1","genero":"aventura","clasificación":"para todo publico"}

## Actualizar un videojuego por ID

### Request

`PUT /api/videojuego/{id}`

	curl -X PUT -H "Content-Type: application/json" -d '{"key1":"value"}' http://localhost:8084/api/videojuego/{id}

### Response

    {"id":"651db33851ac6e4182aef187","nombre":"videojuego1","genero":"aventura","clasificación":"para todo publico"}


## Eliminar un videojuego por ID

### Request

`DELETE /api/videojuego/{id}`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:8084/api/videojuego/{id}

### Response

HTTP/1.1 204 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Date: Thu, 05 Oct 2023 18:00:47 GMT


### YAML
```yaml
openapi: 3.0.1
info:
  title: OpenAPI definition
  version: v0
servers:
- url: http://localhost:8084
  description: Generated server url
paths:
  /api/videojuego/{id}:
    put:
      tags:
      - video-juego-controller
      summary: Actualizar un videojuego por ID
      operationId: actualizarVideoJuego
      parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/VideoJuego'
        required: true
      responses:
        "409":
          description: Conflict
          content:
            '*/*':
              schema:
                type: string
        "400":
          description: Bad Request
          content:
            '*/*':
              schema:
                type: string
        "404":
          description: No existe videojuego con el ID proporcionado
        "200":
          description: Videojuego actualizado
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/VideoJuego'
    delete:
      tags:
      - video-juego-controller
      summary: Eliminar un videojuego por ID
      operationId: eliminarVideoJuego
      parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
      responses:
        "409":
          description: Conflict
          content:
            '*/*':
              schema:
                type: string
        "400":
          description: Bad Request
          content:
            '*/*':
              schema:
                type: string
        "204":
          description: Videojuego eliminado
  /api/videojuego:
    get:
      tags:
      - video-juego-controller
      summary: Obtener una lista de todos los videojuegos
      operationId: obtenerVideoJuegos
      responses:
        "409":
          description: Conflict
          content:
            '*/*':
              schema:
                type: string
        "400":
          description: Bad Request
          content:
            '*/*':
              schema:
                type: string
        "200":
          description: Lista de videojuegos registrados
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/VideoJuego'
    post:
      tags:
      - video-juego-controller
      summary: Registrar Videojuego
      operationId: registrarVideoJuego
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/VideoJuego'
        required: true
      responses:
        "409":
          description: Ya existe un videojuego con el nombre especificado
          content: {}
        "400":
          description: Bad Request
          content:
            '*/*':
              schema:
                type: string
        "201":
          description: Videojuego Registrado Exitosamente
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/VideoJuego'
components:
  schemas:
    VideoJuego:
      required:
      - nombre
      type: object
      properties:
        id:
          type: string
        nombre:
          type: string
        genero:
          type: string
        clasificación:
          type: string
```