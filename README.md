# Evaluación Formativa - Microservicios con Spring Boot y Firebase

Este repositorio contiene el desarrollo de un proyecto de microservicios backend utilizando **Spring Boot**, con operaciones **CRUD**, conexión a **Firebase Firestore** como base de datos NoSQL y pruebas realizadas mediante **Postman** y **JUnit**.

##  Estructura del Proyecto

- `controller/`: Controladores REST para abogados y clientes.
- `service/`: Lógica de negocio conectada a Firestore.
- `model/`: Entidades de dominio `Abogado` y `Cliente`.
- `config/`: Configuración de Firebase Admin SDK.
- `resources/`: Configuraciones generales (`application.properties`).

##  Tecnologías utilizadas

- Spring Boot 3.x
- Firebase Admin SDK (Firestore)
- Maven
- Git + GitHub
- Postman
- JUnit
- Mockito

##  Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/api/abogados`         | Listar abogados |
| GET    | `/api/abogados/{id}`    | Obtener abogado por ID |
| POST   | `/api/abogados`         | Crear abogado |
| PUT    | `/api/abogados/{id}`    | Actualizar abogado |
| DELETE | `/api/abogados/{id}`    | Eliminar abogado |
| GET    | `/api/clientes`         | Listar clientes |
| ...    | ...                      | (Igual para clientes) |

##  Pruebas

- **Postman**: Se verificaron todos los endpoints CRUD.
- **JUnit y Mockito**: Se Utilizaron para realizar pruebas unitarias al proyecto a traves de estas herramientas.

##  Seguridad

>  Las credenciales de conexión a Firebase **NO están incluidas en este repositorio**, siguiendo buenas prácticas de seguridad. Se usan variables locales y `.gitignore` para proteger datos sensibles.

## Resultado

El proyecto cumple con los objetivos planteados:
- CRUD funcionando con Firestore.
- Buenas prácticas aplicadas.
- Documentación completa.
- Repositorio limpio y seguro.

##  Autor

- **Estudiante:** Alexsander Rosales,Nicolas Estafania, Dairys Sanchez, Diego Tapia y Ariel Espinoza


