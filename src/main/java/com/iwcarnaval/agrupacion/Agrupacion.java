package com.iwcarnaval.agrupacion;

import com.iwcarnaval.common.EntidadBase;
import com.iwcarnaval.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import static java.util.Objects.requireNonNull;

/**
 * Entidad: una agrupación del Carnaval (comparsa, chirigota, coro o cuarteto).
 *
 * <p>Es la <b>identidad estable</b> a lo largo de los años. Lo que cambia de un año a otro
 * (nombre con el que concursa, componentes, fianza) vive en {@link Inscripcion}.</p>
 */
@Entity
@Table(name = "agrupaciones")
public class Agrupacion extends EntidadBase {

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modalidad modalidad;

    private String localidad;

    /** Dirección de una imagen de la agrupación para las tarjetas del portal (opcional). */
    @Column(length = 500)
    private String imagenUrl;

    /** Usuario responsable que gestiona la inscripción de la agrupación (opcional). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id")
    private Usuario responsable;

    /** Constructor para JPA. */
    protected Agrupacion() {
    }

    /**
     * @param nombre    nombre actual de la agrupación
     * @param modalidad modalidad
         * @param localidad localidad de origen (puede ser {@code null})
     */
    public Agrupacion(String nombre, Modalidad modalidad, String localidad) {
        setNombre(nombre);
        setModalidad(modalidad);
        setLocalidad(localidad);
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        requireNonNull(nombre, "nombre");
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la agrupación no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public Modalidad getModalidad() { return modalidad; }

    public void setModalidad(Modalidad modalidad) { this.modalidad = requireNonNull(modalidad, "modalidad"); }

    public String getLocalidad() { return localidad; }

    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getImagenUrl() { return imagenUrl; }

    public void setImagenUrl(String imagenUrl) {
        String limpia = (imagenUrl == null || imagenUrl.isBlank()) ? null : imagenUrl.trim();
        if (limpia != null && !limpia.matches("(?i)^https?://\\S+$")) {
            throw new IllegalArgumentException("La dirección de la imagen debe empezar por http:// o https://");
        }
        this.imagenUrl = limpia;
    }

    public Usuario getResponsable() { return responsable; }

    public void setResponsable(Usuario responsable) { this.responsable = responsable; }
}
