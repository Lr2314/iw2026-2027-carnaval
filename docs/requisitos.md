# Análisis de requisitos

## 1. Contexto y objetivos
El Ayuntamiento de Cádiz organiza anualmente el concurso oficial de agrupaciones del Carnaval(comparsas, chirigotas, coros y cuartetos). Las inscripciones, sorteos, fases y fallos del jurado se tramitan con formularios, hojas de cálculo y correo; las letras, autorías, componentes y premios previos
están dispersos en papel, archivos personales y webs de aficionados.

Por lo que el sitema debe:

| Objetivo | Descripción |
|---|---|
| **Gestión del concurso** | Inscripción, sorteo y fases de cada edición sin papel; resultados publicados con transparencia; menos carga para agrupaciones y personal municipal. |
| **Memoria del Carnaval** | Archivo de agrupaciones, autorías, letras y premios; cada edición cerrada se conserva íntegra; patrimonio abierto a aficionados e investigadores. |

Es un servicio público **de largo plazo**: debe seguir en servicio dentro de 15 años, mantenido por personas
que hoy no conocemos.

## 2. Actores

| Actor | Descripción | Acceso |
|---|---|---|
| Aficionado / investigador | Consulta el archivo y la edición en curso | Público, sin cuenta |
| Agrupación (responsable) | Tramita la inscripción, la fianza y los componentes de su agrupación | Rol `AGRUPACION` |
| Jurado | Registra las puntuaciones de las actuaciones | Rol `JURADO` |
| Personal municipal | Administra ediciones, sorteos, jurado, archivo y usuarios | Rol `MUNICIPAL` |
| Pasarela de pago *(sistema externo)* | Procesa la fianza | — |
| Sistema de notificaciones *(sistema externo)* | Envía avisos por correo | — |

## Requisitos funcionales
1. Consultar datos:
   - Agrupaciones
   - Piezas
   - Autores
   - Letras
   - Fechas
   - Resultados
2. Inscribir Agrupaciones
3. Sorteo de fechas
4. Gestión del jurado
5. Catalogación del archivo
6. Control de acceso

## Requisitos no funcionales
1. Uso de las tecnologías acordadas
2. Arquitectura modular
3. Mantenible por al menos 15 años
4. Software libre
5. Sostenibilidad documental
6. Cumplimiento de normativa:
   - Accesibildiad del sector público
   - LGPD

```mermaid
---
title: Diagrama de clases
---
classDiagram
    direction RL
    Agrupación *-- Participante
    Agrupación --> Actuación : lleva a cabo
    Actuación <-- Jornada : incluye
    Actuación "*"o--"*" Pieza
    Edición *-- Jornada
    Usuario <|-- Participante
    Usuario <|-- Jurado
    Jurado --> Actuación : puntúa
    Jurado .. puntuaciónJurado
    puntuaciónJurado .. Actuación
    class Agrupación{
        nombre
        tipo
        categoría
    }
    class Participante {
        nombre
    }
    class Jornada {
        fecha
        ronda
    }
    class Pieza {
        título
        tipo
        letra
        autor
    }
    class Edición {
        año
    }
    class Actuación {
        /puntuaciónTotal
    }
```
## Propuesta de diagrama
```mermaid
---
title: Mejor? Diagrama de clases
---
classDiagram
    class Edicion {
        +int ano
        +String bases
        +boolean cerrada
    }

    class Jornada {
        +Date fecha
        +String fase
        +String ronda
    }

    class Actuacion {
        +int ordenActuacion
        +double puntuacionTotal
    }

    class Agrupacion {
        +String nombre
        +String modalidad
        +String categoria
        +String localidad
    }

    class Persona {
        +String nombre
        +String apellidos
        +String dni
    }

    class Componente {
        +String aniosParticipacion
    }

    class PiezaMusical {
        +String titulo
        +String tipo
        +String letra
        +String musica
    }

    class Jurado {
        +String tipoJurado
    }

    class PuntuacionJurado {
        +double nota
        +String observaciones
    }

    class Usuario {
        +String username
        +String passwordHash
        +String rol
    }

    %% Relaciones principales
    Edicion "1" --> "*" Jornada : convoca
    Jornada "1" --> "*" Actuacion : contiene
    Agrupacion "1" --> "*" Actuacion : realiza
    Actuacion "1" --> "*" PiezaMusical : interpreta
    
    %% Relación con el archivo histórico y ediciones
    Edicion "1" --> "*" Agrupacion : participa_en (inscripción)

    %% Personas y roles
    Persona "1" --> "*" Componente : es_componente
    Agrupacion "1" --> "*" Componente : agrupa_a
    Persona "1" --> "*" PiezaMusical : autoria
    
    %% Jurado y puntuaciones
    Jurado --|> Usuario : hereda_de
    Persona "1" --> "0..1" Jurado : ejerce_como
    Jurado "*" --> "*" PuntuacionJurado : emite
    Actuacion "1" --> "*" PuntuacionJurado : recibe
```

```mermaid
---
title: Diagrama de casos de uso
---
usecase-beta
    direction TD
    systemBoundary "Gestión de agrupación"
        crearAgrupacion("Crear Agrupación")
    end
    systemBoundary "Gestión de usuario"
        validarUsuario("Validar Usuario")
        crearUsuario("Crear Usuario")
        mostrarUsuario("Mostrar Usuario")
        modificarUsuario("Modificar Usuario")
        eliminarUsuario("Eliminar Usuario")
    end
    actor Usuario --> crearAgrupacion
    Usuario --> validarUsuario
    Usuario --> crearUsuario
    Usuario --> mostrarUsuario
    Usuario --> modificarUsuario
    Usuario --> eliminarUsuario
    actor Jurado --> puntua("Crear Puntuación")
```
