# Plan de proyecto

## Equipo y roles

- Product Owner: Manuel Díaz de la Rocha
- Scrum Master: Luis Romero
- Desarrollo: Todos

El Product Owner habla con el cliente (el profesor), mantiene y prioriza el *backlog*. El Scrum Master cuida el
proceso, facilita los eventos y elimina impedimentos. Todo el equipo analiza, diseña, programa, prueba y despliega.

## Roadmap

- *Deadline*: 8 de enero 2027
- Plan de proyecto (1ª iteración): 15 de octubre 2026
- Requisitos (1ª iteración): 22 de octubre 2026

### Propuesta de hitos (a ajustar por el equipo)

Sprints de dos semanas. Cada sprint termina con una versión del software y de la documentación, marcada como
*milestone* en la forja, con sus historias como *issues*.

| Hito | Objetivo | Entregables | Fechas orientativas |
|---|---|---|---|
| Hito 0 | Plan de proyecto | `plan-proyecto.md` | 15 oct 2026 |
| Hito 1 | Requisitos y base técnica | `requisitos.md`; proyecto Spring Boot 4 + Vaadin 25 compilando; dominio (entidades y objetos valor) | 22 oct – 20 nov 2026 |
| Hito 2 | Casos de uso del concurso | Servicios (inscripción, sorteo, puntuación); front-office público; pruebas; `diseno.md` | 23 nov – 4 dic 2026 |
| Hito 3 | Back-office y seguridad | Vistas de gestión, login y roles; datos de demostración; `pruebas.md` | 7 – 18 dic 2026 |
| Hito 4 | Despliegue | Despliegue en AWS; `despliegue.md`; correcciones; ensayo de la defensa | 21 dic 2026 – 4 ene 2027 |
| Hito final | Entrega y defensa | Release con el `jar`; documentación completa | 8 ene 2027 |

### Estado actual (noviembre 2026)

Construido en el repositorio: proyecto base, dominio, servicios, vistas públicas y de gestión, seguridad por
roles, datos de demostración y pruebas automáticas. Pendiente: validar con el cliente las preguntas abiertas
de [requisitos.md](requisitos.md#8-supuestos-y-preguntas-abiertas-para-el-cliente), despliegue en AWS, integraciones reales
(pasarela de pago y correo) y auditoría de accesibilidad.

## Eventos de Scrum

- Sesión semanal ~15 minutos
    1. ¿Qué se hizo desde el último punto de control?
    2. ¿Qué se va a hacer esta semana?
    3. ¿Hay algún bloqueo u obstáculo?
- Sprint Review & Retrospectiva: bisemanal. Evaluación del incremento funcional entregado y revisión de
  aspectos a mejorar en el flujo de trabajo.
- Planificación del sprint: al inicio de cada sprint el PO presenta el *backlog* priorizado y el equipo elige las
  historias del *sprint backlog*, que se registran como *issues* agrupados en un *milestone*.

## Modelo de trabajo

- Documentación del código con *Doxygen* (comentarios Javadoc; configuración en `Doxyfile`) y documentación del
  proyecto en Markdown dentro de `docs/`.
- Gestión de versiones con *GitHub*: ramas por funcionalidad, commits pequeños y revisión antes de integrar.
- Gestión del proyecto con *Maven*.
- Diseño guiado por el dominio: paquetes por funcionalidad, entidades con sus reglas y servicios de aplicación.

## Definición de *done*

Una historia está terminada cuando:

1. El código es funcional y está **probado** (pruebas automáticas que pasan en `mvn verify`).
2. Ha sido **revisado** por otra persona del equipo.
3. La documentación afectada en `docs/` está actualizada y el código público lleva su Javadoc.
4. Cada integrante podría **explicar y modificar** ese código en la defensa.

Para tareas no de código, por consenso del equipo.

## Acuerdos de equipo

- Comunicación por *WhatsApp*.
- Se utilizarán las siguientes herramientas:
    - Gestión de versiones con *GitHub*
    - Documentación con *Doxygen*
    - Gestión de proyectos con *Maven*
- Resolución de conflictos por mayoría.
- El trabajo debe estar equilibrado: roles, tareas, *commits* y ediciones de la documentación. Cada integrante
  contribuye a código, pruebas y documentación.

## Riesgos

| Riesgo | Impacto | Mitigación |
|---|---|---|
| Requisitos no confirmados con el cliente | Trabajo extra | Lista de preguntas abiertas y supuestos explícitos; validarlos pronto |
| Dependencias de versiones nuevas (Spring Boot 4, Vaadin 25) | Código que no funciona con ejemplos antiguos | Fijar versiones en el `pom.xml`; contrastar con la documentación de cada versión |
| Datos insuficientes en la defensa | No se pueden probar todas las funciones | Perfil `demo` con datos de ejemplo cargados al arrancar |
