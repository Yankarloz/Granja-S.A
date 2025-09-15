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

```mermaid
classDiagram

class Cliente {
  +cedula: VARCHAR(20) <<PK>>
  +nombres: VARCHAR(50)
  +apellidos: VARCHAR(50)
  +direccion: VARCHAR(100)
  +telefono: VARCHAR(20)
}

class Porcino {
  +identificacion: VARCHAR(20) <<PK>>
  +raza: VARCHAR(20)  // 1-york, 2-hamp, 3-duroc
  +edad: INT
  +peso: NUMERIC(6,2)
  +cliente_cedula: VARCHAR(20) <<FK>>
}

class Alimentacion {
  +id: SERIAL <<PK>>
  +descripcion: VARCHAR(100)
  +dosis: VARCHAR(50)
}

Cliente "1" --> "0..*" Porcino : tiene
Porcino "0..*" --> "0..*" Alimentacion : recibe
