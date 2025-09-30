# Granja S.A. - Instrucciones de Instalación y Uso

##Uso GraphQL

-Inicia el servicio de backend 
```sh
cd backend
./mvnw clean install
./mvnw spring-boot:run
```
-En un navegador entra a la herramienta de pruebas de graphql

```sh
http://localhost:8080/graphiql
```

## Requisitos previos
- Java 24
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
./mvnw clean install
./mvnw spring-boot:run
```

## 3. Configurar el frontend (React)
```sh
cd frontend
npm install                 
npm run dev                 
```

## 4. Configurar la base de datos
- Instala PostgreSQL y crea la base de datos edita el nombre usario y contraseña en el archivo `backend/src/main/resources/application.properties`.
- Importa el esquema de tablas usando el granjaDB.txt.

## 5. Uso
- Iniciar el backend con ./mvnw spring-boot:run y el front con npm run dev.
- Abre el frontend con su link.

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
