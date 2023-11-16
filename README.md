# diplo_cloud_videojuego_service
Repositorio para el microservicio de videojuegos

# Funcionalidad

Esta aplicación expone una api rest, con las funciones necesarias para registrar y actualizar la información en una base de datos

# Descarga de la aplicacoón

Para obtener el código fuente, es necessrio obtenerlo de github usando la siguiente instrucción en la línea de comandos

https://github.com/masanmar67/diplo_cloud_videojuego_service.git

# Construir la aplicación

Dentro la carpeta resources/manifest se encuentran los archivos necesarios para desplegar la aplicación en un cluster de kubernetes y exponer el servicio. Para ello se deben ejecutar las siguentes instrcciones en el orden dado

kubectl apply -f videojuego_service_cm.yaml

kubectl apply -f videojuego_service_dep.yaml

kubectl apply -f videojuego_service_srv.yaml

kubectl apply -f videojuego_service_ingress.yaml


# REST API

Estos son los servicios para la administración de videojuegos de la aplicación PIXUP

## Obtener lista de videojuegos

### Request

`GET /api/videojuego`

    curl -i -H 'Accept: application/json' http://k8s.nuup.ninja:8084/api/videojuego

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
	 http://k8s.nuup.ninja:8084/api/videojuego	

### Response

    {"id":"651db33851ac6e4182aef187","nombre":"videojuego1","genero":"aventura","clasificación":"para todo publico"}

## Actualizar un videojuego por ID

### Request

`PUT /api/videojuego/{id}`

	curl -X PUT -H "Content-Type: application/json" -d '{"key1":"value"}' http://k8s.nuup.ninja:8084/api/videojuego/{id}

### Response

    {"id":"651db33851ac6e4182aef187","nombre":"videojuego1","genero":"aventura","clasificación":"para todo publico"}


## Eliminar un videojuego por ID

### Request

`DELETE /api/videojuego/{id}`

    curl -i -H 'Accept: application/json' -X DELETE http://k8s.nuup.ninja:8084/api/videojuego/{id}

### Response

HTTP/1.1 204 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Date: Thu, 05 Oct 2023 18:00:47 GMT
