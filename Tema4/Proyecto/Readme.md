# Proyecto Microservicios - Reserva y Comentarios

Breve resumen de los microservicios implementados y ejemplos para usar en Postman y GraphiQL.

---

## Resumen general

Este repositorio contiene varios microservicios basados en Spring Boot que forman una aplicación de reservas de hoteles con comentarios:

- `eureka_server` — servidor de descubrimiento (Eureka).
- `gateway` — API Gateway (Spring Cloud Gateway) que enruta a los servicios registrados.
- `usuarios` — servicio REST para gestionar usuarios (MySQL).
- `reservas` — servicio REST para gestionar hoteles, habitaciones y reservas (MySQL).
- `comentarios` — servicio GraphQL para gestionar comentarios y puntuaciones (MongoDB).

Cada servicio corre en su propio puerto y se registra en Eureka para permitir enrutamiento dinámico desde el `gateway`.

---

## `eureka_server` (puerto 8500)

Descripción:
- Servidor de descubrimiento basado en Netflix Eureka. Permite que los microservicios se registren y sean localizables por nombre.

Uso:
- Arrancar antes que el resto para que los servicios puedan registrarse.

---

## `gateway` (puerto 8080)

Descripción:
- API Gateway basado en Spring Cloud Gateway con `spring.cloud.gateway.discovery.locator.enabled=true`.
- Hace discovery de servicios registrados en Eureka y enruta peticiones según `serviceId` y la ruta del request.

Ejemplos de enrutamiento:
- Petición a usuarios vía gateway:

```bash
curl -X POST http://localhost:8080/usuarios/registrar \
	-H "Content-Type: application/json" \
	-d '{"nombre":"Ana","correo_electronico":"ana@example.com","direccion":"Calle","contrasena":"abc"}'
```

- Acceso a GraphiQL de `comentarios` vía gateway (UI):

Abre en el navegador: `http://localhost:8080/comentarios/comentarios`

Nota:
- El gateway permite exponer un único punto de entrada para los clientes; también gestiona rutas dinámicas si los microservicios cambian de instancia.

---

## `usuarios` (puerto 8502)

Descripción:
- Servicio REST para gestión de usuarios. Usa MySQL (importar `usuariosProyecto.sql`).
- Funciones: crear, actualizar, eliminar usuarios; validar credenciales; obtener info/id por nombre.

Endpoints principales (directo):

**1. Crear usuario**: `POST /usuarios/registrar`

```bash
curl -X POST http://localhost:8502/usuarios/registrar \
	-H "Content-Type: application/json" \
	-d '{
		"nombre":"Juan Pérez",
		"correo_electronico":"juan@example.com",
		"direccion":"Calle Principal 123",
		"contrasena":"clave123"
	}'
```

**2. Actualizar usuario**: `PUT /usuarios/registrar`

Body: incluye `usuario_id` + datos a actualizar.

```bash
curl -X PUT http://localhost:8502/usuarios/registrar \
	-H "Content-Type: application/json" \
	-d '{
		"usuario_id": 1,
		"nombre":"Juan Pérez Updated",
		"correo_electronico":"juan.new@example.com",
		"direccion":"Nueva Calle 456",
		"contrasena":"nuevaclave456"
	}'
```

**3. Eliminar usuario**: `DELETE /usuarios`

```bash
curl -X DELETE http://localhost:8502/usuarios \
	-H "Content-Type: application/json" \
	-d '{
		"nombre":"Juan Pérez",
		"contrasena":"clave123"
	}'
```

**4. Validar credenciales (login)**: `POST /usuarios/validar`

Devuelve `true` si es válido, `false` en caso contrario.

```bash
curl -X POST http://localhost:8502/usuarios/validar \
	-H "Content-Type: application/json" \
	-d '{
		"nombre":"Juan Pérez",
		"contrasena":"clave123"
	}'
```

**5. Obtener nombre por id**: `GET /usuarios/info/id/{id}`

```bash
curl -X GET http://localhost:8502/usuarios/info/id/1
```

**6. Obtener id por nombre**: `GET /usuarios/info/nombre/{nombre}`

```bash
curl -X GET http://localhost:8502/usuarios/info/nombre/Juan%20Pérez
```

**7. Comprobar existencia**: `GET /usuarios/checkIfExist/{id}`

Devuelve `true` si existe, `false` si no.

```bash
curl -X GET http://localhost:8502/usuarios/checkIfExist/1
```

Ejemplo (vía gateway): reemplazar `http://localhost:8502` por `http://localhost:8080/usuarios`.

Dependencias:
- MySQL / base de datos `usuariosProyecto`.

---

## `reservas` (puerto 8501)

Descripción:
- Servicio REST para gestionar hoteles, habitaciones y reservas.
- Funciones: crear reserva (valida usuario contra `usuarios`), cambiar estado, listar reservas por usuario o por estado, verificar existencia.

Endpoints principales (directo):

**1. Crear reserva**: `POST /reservas`

Body: `ReservaDTO` (incluye `nombre`, `contrasena`, `habitacion_id`, `fecha_inicio`, `fecha_fin`).

```bash
curl -X POST http://localhost:8501/reservas \
	-H "Content-Type: application/json" \
	-d '{
		"nombre":"Juan Pérez",
		"contrasena":"clave123",
		"habitacion_id":1,
		"fecha_inicio":"2024-05-15",
		"fecha_fin":"2024-05-20"
	}'
```

**2. Cambiar estado de reserva**: `PATCH /reservas`

Body: `ReservaEstadoDTO` (incluye `nombre`, `contrasena`, `reserva_id`, `estado`).

Estados válidos: "Confirmada", "Pendiente", "Cancelada".

```bash
curl -X PATCH http://localhost:8501/reservas \
	-H "Content-Type: application/json" \
	-d '{
		"nombre":"Juan Pérez",
		"contrasena":"clave123",
		"reserva_id":1,
		"habitacion_id":1,
		"fecha_inicio":"2024-05-15",
		"fecha_fin":"2024-05-20",
		"estado":"Confirmada"
	}'
```

**3. Listar reservas de usuario**: `GET /reservas`

Requiere body con `UsuarioValidarDTO`.

```bash
curl -X GET http://localhost:8501/reservas \
	-H "Content-Type: application/json" \
	-d '{
		"nombre":"Juan Pérez",
		"contrasena":"clave123"
	}'
```

**4. Listar reservas por estado**: `GET /reservas/{estado}`

Requiere body con `UsuarioValidarDTO`.

```bash
curl -X GET http://localhost:8501/reservas/Confirmada \
	-H "Content-Type: application/json" \
	-d '{
		"nombre":"Juan Pérez",
		"contrasena":"clave123"
	}'
```

**5. Comprobar reserva específica**: `GET /reservas/check/{idUsuario}/{idHotel}/{idReserva}`

Devuelve `true` si existe, `false` si no.

```bash
curl -X GET http://localhost:8501/reservas/check/1/1/1
```

Ejemplo (vía gateway): usar `http://localhost:8080/reservas` en lugar del puerto directo.

Dependencias:
- MySQL / base de datos `reservasProyecto`.
- Llamadas internas al servicio `usuarios` para validar identidad y obtener `usuarioId`.

---

## `comentarios` (puerto 8503)

Descripción:
- Servicio GraphQL para gestionar comentarios y puntuaciones, persistidos en MongoDB (`comentariosProyecto`).
- Exposición: GraphQL HTTP (`/graphql`) y GraphiQL UI (`/comentarios`).

Esquema (resumen):
- Input `ComentarioCrearInput` — parámetros necesarios para crear un comentario.
- Queries y Mutations disponibles (abajo).

**Queries:**

**1. Listar comentarios de un hotel**: `listarComentariosHotel`

```graphql
query {
	listarComentariosHotel(nombre: "Juan Pérez", contrasena: "clave123", nombreHotel: "Hotel A") {
		nombreHotel
		reservaId
		puntuacion
		comentario
	}
}
```

**2. Listar comentarios de un usuario**: `listarComentariosUsuario`

```graphql
query {
	listarComentariosUsuario(nombre: "Juan Pérez", contrasena: "clave123") {
		nombreHotel
		reservaId
		puntuacion
		comentario
	}
}
```

**3. Obtener comentario de usuario por reserva**: `mostrarComentarioUsuarioReserva`

```graphql
query {
	mostrarComentarioUsuarioReserva(nombre: "Juan Pérez", contrasena: "clave123", reservaId: 1) {
		nombreHotel
		reservaId
		puntuacion
		comentario
	}
}
```

**4. Puntuación media de un hotel**: `puntuacionMediaHotel`

Devuelve un `Float` con la media.

```graphql
query {
	puntuacionMediaHotel(nombre: "Juan Pérez", contrasena: "clave123", nombreHotel: "Hotel A")
}
```

**5. Puntuación media de un usuario**: `puntuacionMediaUsuario`

Devuelve un `Float` con la media.

```graphql
query {
	puntuacionMediaUsuario(nombre: "Juan Pérez", contrasena: "clave123")
}
```

**Mutations:**

**1. Crear comentario**: `crearComentario`

```graphql
mutation {
	crearComentario(comentarioCrearInput: {
		nombre: "Juan Pérez",
		contrasena: "clave123",
		nombreHotel: "Hotel A",
		reservaId: 1,
		puntuacion: 4.5,
		comentario: "Excelente servicio, muy recomendado"
	}) {
		nombreHotel
		reservaId
		puntuacion
		comentario
	}
}
```

**2. Eliminar todos los comentarios**: `eliminarComentarios`

Devuelve un mensaje de confirmación.

```graphql
mutation {
	eliminarComentarios
}
```

**3. Eliminar comentario de usuario**: `eliminarComentarioDeUsuario`

Devuelve un mensaje de confirmación.

```graphql
mutation {
	eliminarComentarioDeUsuario(nombre: "Juan Pérez", contrasena: "clave123", comentarioId: "<id_del_comentario>")
}
```

**Acceso (directo)**:
- UI GraphiQL: `http://localhost:8503/comentarios`
- Endpoint HTTP: `http://localhost:8503/graphql`

**Acceso (vía gateway)**:
- UI GraphiQL: `http://localhost:8080/comentarios/comentarios`
- Endpoint HTTP: `http://localhost:8080/comentarios/graphql`

Dependencias:
- MongoDB en `mongodb://localhost:27017/comentariosProyecto`.

---

## Bases de datos e importación

MySQL (Usuarios y Reservas):

```bash
mysql -u root -p < "C:\\Users\\super\\Desktop\\usuariosProyecto.sql"
mysql -u root -p < "C:\\Users\\super\\Desktop\\reservasProyecto.sql"
```

MongoDB (Comentarios):

Nota: el fichero `comentarios.json` proporcionado contiene `ObjectId()` — conviértelo a un array JSON válido o elimina `ObjectId()` antes de importar.

```bash
# Ajusta el JSON para ser un array válido y luego:
mongoimport --db comentariosProyecto --collection comentarios --file "C:\\Users\\super\\Desktop\\comentarios.json" --jsonArray
```

---

## Recomendaciones de arranque

**Cómo probar todo paso a paso (recomendado)**
1. Arrancar `eureka_server` (`8500`).
2. Arrancar `usuarios` (`8502`), `reservas` (`8501`) y `comentarios` (`8503`).
3. Arrancar `gateway` (`8080`).
4. Probar peticiones directas a cada servicio y luego a través del `gateway`.


**Detalles extendidos por componente**

- **`eureka_server` (8500)**: Servidor de descubrimiento. Los microservicios se registran aquí para que el `gateway` y otros clientes los localicen dinámicamente. Arranca primero para que los servicios puedan registrarse.

- **`gateway` (8080)**: API Gateway basado en Spring Cloud Gateway con `spring.cloud.gateway.discovery.locator.enabled=true`. Esto permite enrutar peticiones a servicios por su `serviceId` (nombre de la aplicación registrada en Eureka). Ejemplos:
	- Ruta directa a servicio `usuarios`: `http://localhost:8080/usuarios/registrar` → enruta a `http://<usuarios-service>/usuarios/registrar`.
	- Para GraphiQL de `comentarios`: `http://localhost:8080/comentarios/comentarios` (el primer `comentarios` es el `serviceId`, el segundo es la ruta expuesta por el servicio).

- **`usuarios` (8502)**: Servicio REST para gestionar usuarios.
	- Funciones principales: crear/actualizar/eliminar usuarios, validar credenciales, obtener id por nombre.
	- Se utiliza por otros servicios (por ejemplo `reservas`) para validar identidad y obtener `usuarioId`.

- **`reservas` (8501)**: Servicio REST para gestionar reservas y habitaciones.
	- Funciones principales: crear reservas (valida usuario contra `usuarios`), cambiar estado de reserva, listar reservas por usuario o estado, comprobar existencia.
	- Dependencias: llama a `usuarios` para validar/consultar datos de usuario.

- **`comentarios` (8503)**: Servicio GraphQL que almacena y consulta comentarios/puntuaciones en MongoDB.
	- Exposición: GraphQL HTTP en `http://localhost:8503/graphql` y GraphiQL UI en `http://localhost:8503/comentarios`.
	- Funciones principales: crear comentario (mutación), listar comentarios por hotel o usuario, mostrar comentario por reserva, calcular puntuaciones medias.


**Ejemplos vía `gateway`**
- Crear usuario (vía gateway):

```bash
curl -X POST http://localhost:8080/usuarios/registrar \
	-H "Content-Type: application/json" \
	-d '{"nombre":"Ana Martínez","correo_electronico":"ana@example.com","direccion":"Calle Parque","contrasena":"abcxyz"}'
```

- Crear reserva (vía gateway):

```bash
curl -X POST http://localhost:8080/reservas \
	-H "Content-Type: application/json" \
	-d '{"nombre":"Ana Martínez","contrasena":"abcxyz","habitacion_id":4,"fecha_inicio":"2024-05-15","fecha_fin":"2024-05-20"}'
```

- Acceder a GraphiQL de `comentarios` vía gateway:

Visita en el navegador: `http://localhost:8080/comentarios/comentarios`

**Notas sobre diferencias entre peticiones directas y vía gateway**
- Directo: `http://localhost:8502/usuarios/validar` — útil para pruebas unitarias y debugging.
- Via gateway: `http://localhost:8080/usuarios/validar` — útil para escenarios donde el cliente sólo conoce el gateway y confía en el enrutamiento dinámico.

**Ejemplos extra y aclaraciones de uso**
- Si usas Postman: importa las requests directas y duplica la colección reemplazando `http://localhost:8502` por `http://localhost:8080/usuarios` (y equivalente para `reservas` y `comentarios`) para testear vía gateway.
- Si tu cliente no permite body en `GET` (por ejemplo para listar reservas con `GET` + body), usa `POST` en tu cliente para mayor compatibilidad o adapta temporalmente el controlador para aceptar `POST`.