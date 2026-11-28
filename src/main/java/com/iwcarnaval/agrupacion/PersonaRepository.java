package com.iwcarnaval.agrupacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Acceso a datos de {@link Persona}. */
public interface PersonaRepository extends JpaRepository<Persona, UUID> {

    Optional<Persona> findByDni(String dni);

    Optional<Persona> findFirstByNombreIgnoreCaseAndApellidosIgnoreCase(String nombre, String apellidos);

    List<Persona> findAllByOrderByApellidosAscNombreAsc();
}
