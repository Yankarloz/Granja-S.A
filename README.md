# Granja S.A. - Instrucciones de Instalación y Uso

## Requisitos previos
- Java 17 o superior
- Node.js y npm
- PostgreSQL
- Git

## 1. Clonar el repositorio
```sh
git clone https://github.com/Yankarloz/Granja-S.A.git
```

## 2. Configurar el backend (Spring Boot)
```sh
cd backend
./mvnw clean install        # Descarga e instala todas las dependencias de Maven
./mvnw spring-boot:run      # Inicia el backend
```
> En Windows puedes usar `mvnw.cmd` en vez de `./mvnw`.

## 3. Configurar el frontend (React)
```sh
cd frontend
npm install                 # Descarga todas las dependencias de Node.js
npm run dev                 # Inicia el servidor de desarrollo de React
```

## 4. Configurar la base de datos
- Instala PostgreSQL y crea la base de datos edita el nombre usario y contraseña en el archivo `application.propertie`.
- Si es necesario, importa el esquema de tablas.

## 5. Uso
- Accede al frontend en la URL que te indique la terminal
- El backend estará en http://localhost:8080

---
