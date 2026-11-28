package com.iwcarnaval.agrupacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Acceso a datos de {@link Agrupacion}. */
public interface AgrupacionRepository extends JpaRepository<Agrupacion, UUID> {

    List<Agrupacion> findAllByOrderByNombreAsc();

    List<Agrupacion> findByNombreContainingIgnoreCaseOrderByNombreAsc(String texto);

    Optional<Agrupacion> findByResponsableUsername(String username);
}
