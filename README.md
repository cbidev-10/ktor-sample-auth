# ktor-sample

Proyecto de ejemplo desarrollado en **Kotlin** utilizando **Ktor**, **Koin** y **MapDB**.  
Implementa un CRUD básico para la entidad **Users**, ideal para explorar arquitectura sencilla de servidor con inyección de dependencias y persistencia embebida.

---

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 21**
- **Gradle** (se recomienda usar el wrapper `./gradlew` o `gradlew.bat`)
- **Kotlin 2.x** (configurada mediante Gradle)

---

## Tecnologías utilizadas

- **Ktor** – Framework para construir aplicaciones backend en Kotlin.
- **Koin** – Inyección de dependencias ligera y moderna.
- **MapDB** – Base de datos embebida en disco o memoria.
- **Gradle** – Sistema de construcción y gestión de dependencias.

---

## Ejecución

1. Clona el repositorio:

   ```
   git clone https://github.com/cbidev-10/ktor-sample-auth.git
   cd ktor-sample-auth
   ```

2. Ejecuta la aplicación:

   ```
   ./gradlew run
   ```

3. La API estará disponible en:

   ```
   http://localhost:8081
   ```

---

## Endpoints principales

- `POST /users` – Crea un nuevo User
- `PUT /users/{id}` – Actualiza un User existente
- `POST /users/authenticate` – Verifica autenticidad del User

---

## Notas

- **Java 21** es obligatorio. Si usas una versión anterior, el proyecto podría fallar al compilar o ejecutar.
- **MapDB** almacena los datos por defecto en un archivo local (configurable).
- Incluye pruebas con **JUnit 5** y **mockk** (si aplica).
```
