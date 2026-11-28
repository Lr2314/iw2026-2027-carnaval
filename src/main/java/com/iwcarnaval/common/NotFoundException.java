package com.iwcarnaval.common;

import java.util.UUID;

/**
 * Se busca un elemento por su identificador y no existe.
 */
public class NotFoundException extends BusinessException {

    /**
     * @param recurso descripción con artículo, p. ej. {@code "la agrupación"}
     * @param id      identificador buscado
     */
    public NotFoundException(String recurso, UUID id) {
        super("No existe " + recurso + " con identificador " + id);
    }
}
