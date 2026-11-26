package com.iwcarnaval.common;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import java.util.UUID;

/**
 * Superclase de todas las entidades: aporta la <b>identidad</b>.
 *
 * <ul>
 *   <li>El identificador es un {@link UUID} <b>artificial</b>, {@code final} y asignado al crear
 *       el objeto, así que {@code equals} funciona incluso antes de guardarlo.</li>
 *   <li>{@code equals} y {@code hashCode} comparan <b>solo el identificador</b>: dos entidades son la
 *       misma si comparten id, no si coinciden todos sus atributos.</li>
 *   <li>{@code @Version} activa el bloqueo optimista de JPA. Además, al ser {@code null} en un
 *       objeto nuevo, Spring Data sabe que hay que insertarlo (persist) y no fusionarlo (merge),
 *       a pesar de que el id ya está asignado.</li>
 * </ul>
 */
@MappedSuperclass
public abstract class EntidadBase {

    @Id
    private final UUID id = UUID.randomUUID();

    @Version
    private Long version;

    /**
     * @return identificador único e inmutable de la entidad
     */
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof EntidadBase that && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
