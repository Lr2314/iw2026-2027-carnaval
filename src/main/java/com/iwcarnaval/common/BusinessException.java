package com.iwcarnaval.common;

/**
 * Raíz de todos los errores de negocio: una regla del concurso que no se cumple.
 *
 * <p>Es una excepción <b>no comprobada</b> ({@link RuntimeException}) y su mensaje está en
 * español para mostrarse tal cual al usuario. La capa de presentación captura esta clase
 * común y muestra el mensaje.</p>
 *
 * <p>Convención del proyecto: <b>una subclase por regla</b>, con su mensaje.
 * {@link IllegalStateException} se reserva para errores de programación.</p>
 */
public abstract class BusinessException extends RuntimeException {

    /**
     * @param message mensaje legible por el usuario final
     */
    protected BusinessException(String message) {
        super(message);
    }
}
