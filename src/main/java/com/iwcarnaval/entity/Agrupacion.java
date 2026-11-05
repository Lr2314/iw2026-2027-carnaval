package com.iwcarnaval.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "agrupaciones")
public class Agrupacion {

    @Id
    private final UUID id = UUID.randomUUID();

    private String nombre;
    private String modalidad;
    private String categoria;
    private String localidad;

    public Agrupacion() {
    }

    public Agrupacion(String nombre, String modalidad, String categoria, String localidad) {
        this.nombre = nombre;
        this.modalidad = modalidad;
        this.categoria = categoria;
        this.localidad = localidad;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agrupacion that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
