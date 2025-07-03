# ⚖️ Sistema de Gestión de Abogados y Clientes

Este repositorio contiene un proyecto backend basado en **microservicios** utilizando **Spring Boot 3.3.12-SNAPSHOT**, conectándose a una base de datos **NoSQL Firebase Firestore**. Se desarrollaron operaciones **CRUD** completas para las entidades `Abogado` y `Cliente`, aplicando buenas prácticas, documentación Swagger y pruebas unitarias con **JUnit 5** y **Mockito**.

---

## 📂 Estructura del Proyecto

```
src/
 └── main/
     ├── java/
     │   └── com.proyect.abogados/
     │       ├── assemblers/         # Lógica HATEOAS (ensamblado de recursos)
     │       ├── config/             # Configuración de Firebase
     │       ├── controller/         # Controladores REST
     │       ├── model/              # Entidades de dominio (Abogado, Cliente)
     │       ├── service/            # Servicios (lógica de negocio)
     │       ├── SwaggerConfig/      # Configuración de Swagger/OpenAPI
     │       ├── testDataFaker/      # Carga de datos de prueba con DataFaker
     │       └── AbogadosApplication.java  # Clase principal (Spring Boot App)
     └── resources/
         └── application.properties  # Configuración general del proyecto
 └── test/                           # Pruebas unitarias con JUnit y Mockito

Otros archivos importantes:
- `pom.xml` - Configuración de dependencias y plugins con Maven
- `README.md` - Documentación del proyecto
- `.gitignore` - Exclusiones del repositorio
- `mvnw`, `mvnw.cmd` - Wrapper de Maven para ejecutar sin instalación previa
```

---

## 🚀 Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.3.12-SNAPSHOT**
- **Firebase Admin SDK (Firestore)**
- **Springdoc OpenAPI (Swagger UI)**
- **Spring HATEOAS**
- **JUnit 5**
- **Mockito**
- **DataFaker**
- **Lombok**
- **Jakarta Validation + Hibernate Validator**
- **Maven**

---

## ⚙️ Dependencias Principales (`pom.xml`)

- `spring-boot-starter-web` - API REST
- `spring-boot-starter-actuator` - Métricas y monitoreo
- `spring-boot-starter-hateoas` - Navegación HATEOAS en endpoints
- `firebase-admin` - SDK para conectar a Firestore
- `springdoc-openapi-starter-webmvc-ui` - Documentación Swagger en `/swagger-ui.html`
- `lombok` - Reducción de código repetitivo
- `jakarta.validation-api` + `hibernate-validator` - Validaciones en entidades
- `junit-jupiter`, `mockito-core`, `mockito-junit-jupiter` - Pruebas unitarias
- `datafaker` - Datos de prueba aleatorios para desarrollo y Postman

---

## 🧪 Pruebas

Las pruebas del sistema se desarrollaron en **dos niveles** principales:

### 🔸 1. Pruebas Unitarias

- Herramientas utilizadas: **JUnit 5** y **Mockito**
- Las pruebas se enfocaron en la capa de servicios (`@Service`), simulando interacciones con Firestore mediante mocks.
- Se probaron funcionalidades como:
  - Listar abogados/clientes
  - Crear entidades
  - Manejo de errores

### 🔸 2. Pruebas Manuales con Postman + Firebase

- Se utilizaron colecciones en **Postman** para probar manualmente los endpoints CRUD.
- Para facilitar la carga de datos de prueba en la base de datos, se utilizó la biblioteca **DataFaker**, generando registros aleatorios realistas de abogados y clientes.
- Esto permitió verificar correctamente la estructura de los documentos creados en **Firestore** y probar múltiples escenarios.

---

## 📘 Documentación con Swagger

El proyecto incorpora **Swagger UI** para facilitar el consumo y exploración de los endpoints RESTful:

- Accede a la documentación: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Se autogenera a partir de anotaciones `@Operation`, `@ApiResponse`, etc.

---

## 🔗 Navegación HATEOAS

Se utilizó **Spring HATEOAS** para enriquecer las respuestas con hipervínculos, permitiendo:

- Mayor navegabilidad entre recursos.
- Acceso directo a acciones relacionadas (self, update, delete).
- Estilo RESTful más robusto y completo.

Ejemplo de respuesta:

```json
{
  "id": "123",
  "nombre": "Abogado Pérez",
  "_links": {
    "self": { "href": "/api/abogados/123" },
    "update": { "href": "/api/abogados/123" },
    "delete": { "href": "/api/abogados/123" }
  }
}
```

---

## 🔐 Seguridad del Proyecto

- 🔒 Las **credenciales de Firebase** están excluidas del repositorio con `.gitignore`.
- Se debe configurar la variable de entorno `GOOGLE_APPLICATION_CREDENTIALS` apuntando al archivo `firebase-config.json` local.

---

## 🔌 Cómo ejecutar el proyecto

### ✅ Requisitos previos

- Java 21
- Maven 3.x
- Cuenta de Firebase con Firestore habilitado
- Archivo `firebase-config.json`

### ▶️ Pasos

1. Clona el repositorio:

   ```bash
   git clone https://github.com/usuario/proyecto-microservicios-firebase.git
   cd proyecto-microservicios-firebase
   ```

2. Configura la variable de entorno:

   ```bash
   export GOOGLE_APPLICATION_CREDENTIALS=./firebase-config.json
   ```

3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🔁 Endpoints Principales

### Abogados

| Método | Endpoint             | Descripción               |
| ------ | -------------------- | ------------------------- |
| GET    | `/api/abogados`      | Listar todos los abogados |
| GET    | `/api/abogados/{id}` | Obtener abogado por ID    |
| POST   | `/api/abogados`      | Crear nuevo abogado       |
| PUT    | `/api/abogados/{id}` | Actualizar abogado        |
| DELETE | `/api/abogados/{id}` | Eliminar abogado          |

### Clientes

| Método | Endpoint             | Descripción               |
| ------ | -------------------- | ------------------------- |
| GET    | `/api/clientes`      | Listar todos los clientes |
| GET    | `/api/clientes/{id}` | Obtener cliente por ID    |
| POST   | `/api/clientes`      | Crear nuevo cliente       |
| PUT    | `/api/clientes/{id}` | Actualizar cliente        |
| DELETE | `/api/clientes/{id}` | Eliminar cliente          |

---

## 🎯 Objetivo del Proyecto

Desarrollar un sistema backend escalable con arquitectura basada en **microservicios**, conectando a **Firestore** como base de datos NoSQL, aplicando buenas prácticas de ingeniería de software, documentación, testing y navegación avanzada (HATEOAS).

---

## 👥 Autores

- **Alexsander Rosales**
- **Nicolás Estafanía**
- **Dairys Sánchez**
- **Diego Tapia**
- **Ariel Espinoza**

---

## 📌 Estado del Proyecto

✅ CRUD funcional  
✅ Firestore integrado  
✅ Pruebas unitarias con Mockito + JUnit 5  
✅ Documentación Swagger  
✅ Navegación HATEOAS  
✅ Buenas prácticas y seguridad aplicadas
