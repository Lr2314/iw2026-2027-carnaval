package com.iwcarnaval.usuario;

/**
 * Roles del sistema (requisito de «Control de acceso»).
 *
 * <p>Los aficionados e investigadores no necesitan cuenta: ven solo la parte pública.</p>
 */
public enum Rol {
    /** Personal municipal: administra ediciones, sorteos, usuarios y archivo. */
    MUNICIPAL,
    /** Miembro del jurado: registra puntuaciones. */
    JURADO,
    /** Responsable de una agrupación: tramita su inscripción y sus componentes. */
    AGRUPACION
}
