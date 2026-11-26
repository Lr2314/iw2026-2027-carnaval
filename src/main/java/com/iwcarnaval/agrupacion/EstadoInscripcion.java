package com.iwcarnaval.agrupacion;

/** Estados de una {@link Inscripcion}. */
public enum EstadoInscripcion {
    /** Se están rellenando los datos; todavía no se ha presentado. */
    BORRADOR,
    /** Presentada y con todos los requisitos de las bases cubiertos: puede actuar. */
    INSCRITA,
    /** La organización ha pedido corregir algo; vuelve a ser editable hasta que se presente de nuevo. */
    SUBSANACION_PENDIENTE,
    /** La agrupación ha comunicado su baja del concurso (estado final). */
    BAJA_COMUNICADA
}
