package com.iwcarnaval.agrupacion;

/**
 * Papel de una persona dentro de la agrupación, que determina cómo cuenta para los límites de las bases.
 */
public enum RolComponente {
    /** Componente que actúa: cuenta para el mínimo y el máximo de la modalidad y para el total del teatro. */
    INTERPRETE,
    /** Rondalla u orquesta de pulso y púa (coros): no cuenta como intérprete, sí para el total del teatro. */
    RONDALLA,
    /** Personal de apoyo con acceso al teatro (montaje, técnicos): solo cuenta para el total del teatro. */
    APOYO
}
