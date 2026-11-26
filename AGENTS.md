AGENTS.md — convenciones del proyecto

Instrucciones para asistentes de IA (y resumen para el equipo). El código debe poder explicarse y modificarse en la defensa: se prefiere el código simple, explícito y bien comentado.
Contexto técnico

    Java 21, Spring Boot 4.1, Vaadin 25.3 Flow (tema Aura), Spring Data JPA, Spring Security, Maven.
    Paquetes jakarta.* (nunca javax.*). Sin XML de beans. Sin @Autowired en atributos.
    NO usar API antiguas: @Theme/frontend/themes, VaadinWebSecurity (eliminada en Vaadin 25), WebSecurityConfigurerAdapter, variables CSS --lumo-* con Aura.

Arquitectura

    Capas: presentación (Vaadin) → negocio (servicios + entidades) → datos (Spring Data JPA).
    Paquetes por funcionalidad: agrupacion, edicion, actuacion, jurado, usuario, archivo, common.
    Las vistas no contienen reglas de negocio: llaman a servicios y muestran BusinessException.
    Dos experiencias: portal público (portal/, PortalLayout) y back-office (/gestion/**, GestionLayout).
    Los servicios no importan nada de Vaadin ni de HTTP.

Convenciones de código

    Entidad: UUID id final asignado al crear, constructor con los datos obligatorios, setters que validan, equals/hashCode solo por id. Constructor sin argumentos protected para JPA.
    Objeto valor: record inmutable que valida en el constructor compacto (Nota, Email).
    Reglas lo más cerca posible de los datos: entidad > objeto valor > servicio (ver docs/diseno.md).
    Servicio de aplicación: inyección por constructor, @Service, @Transactional, hora vía Clock, devuelve DTO (record) a las vistas, lanza una BusinessException por regla incumplida (mensajes en español).
    Repositorio: interfaz JpaRepository<T, UUID>; consultas derivadas por nombre.
    Dinero en BigDecimal, nunca double.
    Dominio en español (Agrupacion, nombre), infraestructura en inglés.
    Las cifras de las bases del concurso viven solo en BasesCoac2026; las entidades las reciben como parámetro.
    Sin emoticonos en ningún texto, interfaz o documento: iconos SVG de Lucide con Iconos.de(...). Una prueba (LimpiezaTest) lo comprueba.
    Sin referencias a material de estudio en código, interfaz ni documentación: la aplicación debe leerse como un producto real.
    Solo se modela la categoría de adultos.
    Documentación: comentarios Javadoc compatibles con Doxygen en cada clase y método público.

Pruebas

    Dominio y servicios: JUnit 5 + AssertJ + Mockito, sin arrancar Spring.
    Repositorios: @DataJpaTest con H2.
    Toda regla de negocio nueva lleva su prueba. mvn verify debe pasar antes de cada commit.

Seguridad y datos

    Contraseñas con BCrypt. Credenciales y claves solo por variables de entorno; nunca en el repositorio.
    Requisitos y reglas de dominio los acuerda el equipo con el cliente: lo no confirmado se anota en docs/requisitos.md como supuesto o pregunta abierta, no se inventa.

Flujo de trabajo

    Ramas por funcionalidad, commits pequeños, revisión antes de integrar. Quien firma el commit responde de él.
    Declarar en docs/ia.md qué herramientas de IA se usan
    y para qué.
