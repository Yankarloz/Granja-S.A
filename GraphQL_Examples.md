# Ejemplos de Consultas GraphQL para Granja S.A.

## 🔍 CONSULTAS (Queries)

### 1. Obtener todos los clientes
```graphql
query {
  allClientes {
    cedula
    nombres
    apellidos
    direccion
    telefono
  }
}
```

### 2. Obtener cliente por cédula
```graphql
query {
  clienteById(cedula: "12345678") {
    cedula
    nombres
    apellidos
    direccion
    telefono
  }
}
```

### 3. Obtener todos los porcinos con información del cliente
```graphql
query {
  allPorcinos {
    id
    identificacion
    raza
    edad
    peso
    cliente {
      cedula
      nombres
      apellidos
    }
    alimentaciones {
      id
      descripcion
      dosis
    }
  }
}
```

### 4. Obtener porcinos por cliente
```graphql
query {
  porcinosByCliente(cedula: "12345678") {
    id
    identificacion
    raza
    edad
    peso
  }
}
```

### 5. Obtener todas las alimentaciones
```graphql
query {
  allAlimentacion {
    id
    descripcion
    dosis
  }
}
```

### 6. Obtener reporte de clientes y porcinos
```graphql
query {
  reporteClientesPorcinos {
    clienteCedula
    clienteNombres
    clienteApellidos
    porcinoIdentificacion
    raza
    edad
    peso
  }
}
```

## ✏️ MUTACIONES (Mutations)

### 7. Crear un nuevo cliente
```graphql
mutation {
  createCliente(input: {
    cedula: "12345678"
    nombres: "Juan Carlos"
    apellidos: "Pérez González"
    direccion: "Calle 123 #45-67"
    telefono: "3001234567"
  }) {
    cedula
    nombres
    apellidos
    direccion
    telefono
  }
}
```

### 8. Actualizar un cliente
```graphql
mutation {
  updateCliente(cedula: "12345678", input: {
    cedula: "12345678"
    nombres: "Juan Carlos"
    apellidos: "Pérez González"
    direccion: "Carrera 456 #78-90"
    telefono: "3007654321"
  }) {
    cedula
    nombres
    apellidos
    direccion
    telefono
  }
}
```

### 9. Crear una nueva alimentación
```graphql
mutation {
  createAlimentacion(input: {
    descripcion: "Concentrado para cerdos de engorde"
    dosis: "2kg por día"
  }) {
    id
    descripcion
    dosis
  }
}
```

### 10. Crear un nuevo porcino
```graphql
mutation {
  createPorcino(input: {
    identificacion: "POR001"
    raza: "york"
    edad: 6
    peso: 85.5
    clienteCedula: "12345678"
    alimentacionIds: [1]
  }) {
    id
    identificacion
    raza
    edad
    peso
    cliente {
      nombres
      apellidos
    }
    alimentaciones {
      descripcion
    }
  }
}
```

### 11. Actualizar un porcino
```graphql
mutation {
  updatePorcino(id: 1, input: {
    identificacion: "POR001"
    raza: "york"
    edad: 7
    peso: 95.0
    clienteCedula: "12345678"
    alimentacionIds: [1, 2]
  }) {
    id
    identificacion
    raza
    edad
    peso
  }
}
```

### 12. Eliminar un cliente
```graphql
mutation {
  deleteCliente(cedula: "12345678")
}
```

### 13. Eliminar un porcino
```graphql
mutation {
  deletePorcino(id: 1)
}
```

### 14. Eliminar una alimentación
```graphql
mutation {
  deleteAlimentacion(id: 1)
}
```

## 🚀 Instrucciones de Uso

1. **Inicia el servidor backend:**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

2. **Accede a GraphiQL:**
   Abre tu navegador en: http://localhost:8080/graphiql

3. **Endpoints disponibles:**
   - GraphQL Playground: http://localhost:8080/graphiql
   - GraphQL API: http://localhost:8080/graphql (para uso con cliente HTTP)

4. **Prueba las consultas:**
   - Copia y pega cualquiera de las consultas de arriba en GraphiQL
   - Presiona el botón "Play" para ejecutar
   - Explora el esquema usando el panel lateral

## 📝 Notas Importantes

- **Razas válidas**: "york", "hamp", "duroc"
- **Las relaciones se cargan automáticamente** (cliente en porcino, alimentaciones en porcino)
- **Los IDs de alimentación** deben existir antes de asignarlos a un porcino
- **La cédula del cliente** debe existir antes de asignar un porcino
- **GraphiQL** proporciona autocompletado y documentación interactiva del esquema