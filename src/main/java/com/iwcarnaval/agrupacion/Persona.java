package com.iwcarnaval.agrupacion;

import com.iwcarnaval.common.EntidadBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import static java.util.Objects.requireNonNull;

/**
 * Entidad: persona física (componente de una agrupación, autor de una pieza o miembro del jurado).
 *
 * <p>Una misma persona puede ser a la vez componente,
 * autora y jurado, así que esos papeles no son subclases sino asociaciones.</p>
 *
 * <p><b>Datos personales</b> (LOPD/RGPD): el DNI es dato sensible y nunca se muestra en la parte
 * pública. Qué datos pueden publicarse es una pregunta abierta al cliente.</p>
 */
@Entity
@Table(name = "personas")
public class Persona extends EntidadBase {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true)
    private String dni;

    /** Constructor para JPA. */
    protected Persona() {
    }

    /**
     * @param nombre    nombre de pila
     * @param apellidos apellidos
     */
    public Persona(String nombre, String apellidos) {
        setNombre(nombre);
        setApellidos(apellidos);
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = noVacio(nombre, "nombre"); }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = noVacio(apellidos, "apellidos"); }

    public String getDni() { return dni; }

    /**
     * @param dni DNI o NIE (se valida su letra de control), o {@code null} para quitarlo
     * @throws com.iwcarnaval.common.Nif.NifInvalidoException si no es válido
     */
    public void setDni(String dni) {
        this.dni = (dni == null || dni.isBlank()) ? null : new com.iwcarnaval.common.Nif(dni).valor();
    }

    /** @return «nombre apellidos» */
    public String nombreCompleto() {
        return nombre + " " + apellidos;
    }

    private static String noVacio(String valor, String campo) {
        requireNonNull(valor, campo);
        if (valor.isBlank()) {
            throw new IllegalArgumentException("El campo «" + campo + "» no puede estar vacío");
        }
        return valor.trim();
    }
}
