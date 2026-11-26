# iw2026-2027-carnaval

Plataforma web del **concurso de agrupaciones del Carnaval de Cádiz** y de su **archivo histórico**.
Proyecto de prácticas de Ingeniería Web (Grado en Ingeniería Informática, Universidad de Cádiz, 2026/2027).

## Equipo

| Rol | Persona |
|---|---|
| Product Owner | Manuel Díaz de la Rocha |
| Scrum Master | Luis Romero |
| Desarrollo | Todo el equipo |

## Tecnología

Java 21 · Spring Boot 4.1 · Vaadin 25.3 (Flow) · Spring Data JPA · Spring Security · PostgreSQL · Maven.

## Qué ofrece

- **Portal público** (sin sesión): portada con el orden de actuación, catálogo de agrupaciones por modalidad, repertorio, autores, premiados, calendario y archivo histórico.
- **Back-office** (con sesión): panel de gestión, ediciones, inscripciones conformes a las bases del concurso (adultos), sorteo y programación, repertorio, jurado, puntuaciones y usuarios.

## Cómo ejecutarla

Requisitos: JDK 21 y Maven.

```bash
# Desarrollo: base de datos H2 en memoria con datos de demostración
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Abrir <http://localhost:8080> (portal público). El acceso a la gestión está en <http://localhost:8080/acceso>; las cuentas de demostración (solo perfiles `dev` y `demo`) están en [`docs/despliegue.md`](docs/despliegue.md).

```bash
# Con PostgreSQL (las credenciales se pasan por variables de entorno)
export DB_URL=jdbc:postgresql://localhost:5432/carnaval_db DB_USER=postgres DB_PASSWORD=...
mvn spring-boot:run

# Pruebas
mvn verify

# JAR ejecutable de producción
mvn -Pproduction package
java -jar target/iw2026-2027-carnaval-1.0-SNAPSHOT.jar
```

## Estructura del código

El código se agrupa **por funcionalidad** (no por capa): cada paquete reúne su entidad, su repositorio, su servicio y sus vistas.

```
src/main/java/com/iwcarnaval/
├── CarnavalApplication.java   clase principal, tema y reloj
├── common/                    objetos valor (nota, correo, DNI) y excepciones de negocio
├── usuario/                   usuarios, roles y seguridad
├── edicion/                   ediciones, jornadas y bases del concurso (BasesCoac2026)
├── agrupacion/                agrupaciones, personas e inscripciones (entidad, figuras, componentes, documentación)
├── actuacion/                 actuaciones, repertorio, sorteo y puntuaciones
├── jurado/                    jurado
├── archivo/                   consultas públicas (agrupaciones, autores, premiados, resultados)
├── portal/                    portal público: layout y vistas
├── gestion/                   back-office: layout, panel y base de las pantallas
└── views/                     acceso, etiquetas, avisos e iconos
```

## Documentación

Toda en [`docs/`](docs/):

| Entregable | Fichero |
|---|---|
| E3.1 Plan de proyecto | [`plan-proyecto.md`](docs/plan-proyecto.md) |
| E3.2 Análisis de requisitos | [`requisitos.md`](docs/requisitos.md) |
| E3.3 Diseño y construcción | [`diseno.md`](docs/diseno.md) |
| E3.4 Pruebas | [`pruebas.md`](docs/pruebas.md) |
| E3.5 Despliegue y operaciones | [`despliegue.md`](docs/despliegue.md) |
| Uso de herramientas de IA | [`ia.md`](docs/ia.md) |

La documentación del código (Javadoc) se genera con Doxygen: `doxygen Doxyfile`.

Las convenciones del proyecto para asistentes de IA están en [`AGENTS.md`](AGENTS.md).
