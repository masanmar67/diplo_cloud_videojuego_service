# diplo_cloud_videojuego_service
Repositorio para el microservicio de videojuegos

# Funcionalidad

Esta aplicación expone una api rest, con las funciones necesarias para registrar y actualizar la información en una base de datos de MongoDB.

# Descarga de la aplicacoón

Para obtener el código fuente, es necessrio obtenerlo de github usando la siguiente instrucción en la línea de comandos

https://github.com/masanmar67/diplo_cloud_videojuego_service.git

# Construir la aplicación

Dentro la carpeta [resources/manifest](https://github.com/masanmar67/diplo_cloud_videojuego_service/tree/main/resources/manifest) se encuentran los archivos necesarios para desplegar la aplicación en un cluster de kubernetes y exponer el servicio. Para ello se deben ejecutar las siguentes instrcciones en el orden dado

Creación del ConfigMap con valores necesarios para usar la aplicación

`kubectl apply -f videojuego_service_cm.yaml`

Creación del deployment para generar el pod de kubernetes el cual alberga tanto la aplicación como el servidor de base de datos MongoDB

`kubectl apply -f videojuego_service_dep.yaml`

Creación del service para el acceso al pod

`kubectl apply -f videojuego_service_srv.yaml`

Creación del recurso Ingress para poder usar el servicio desde equipos externos

`kubectl apply -f videojuego_service_ingress.yaml`

Para el despliegue de la aplicación, en la carpeta [resources/tekton](https://github.com/masanmar67/diplo_cloud_videojuego_service/tree/main/resources/tekton) se encuentran los archivos necesarios para crear un event listener. Con la ayuda de este listener, y por medio de un trigger template, se ejecuta el pipeline el cual se encarga de descargar el código fuente, compilar, crear la imagen de docker y desplegar el pod que alberga la aplicación.

En GitHub se crea un recurso Webhook, con el fin de ejecutar el pipeline cuando se registre un cambio en los archivos del repositorio. Este webhook puede hacer una petición al event listener ya que este se encuentra expuesto al exterior por medio de un recurso router de openshift. La url donde se hacen las peticiones al event listener es http://el-tekton-event-listener-user17.apps.ocp-poc-singlenode.nuup.rocks

Las siguientes instrucciones se encargan de crear estos recursos.

Creación del pipeline

`kubectl apply -f pipeline-vjs.yaml`

Creación del trigger template, el cual define el pipelinerun a ejecutar.

`kubectl apply -f trigger_template.yaml`

Creación del trigger binding, con unos parámetros a usar en el pipeline run, algunos de ellos provenientes de la petición del webhook de GitHub

`kubectl apply -f trigger_binding.yaml`

Creación del event listener

`kubectl apply -f event_listener.yaml`

Y esta instrucción expone el event listener como un recurso route de openshift, para realizar peticiones desde un recurso externo, que para este caso es el webhook definido en GitHub

`oc expose svc/el-tekton-event-listener`

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

> cafaray: project validation; second-validation
