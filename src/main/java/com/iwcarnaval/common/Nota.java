package com.iwcarnaval.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Objeto valor: puntuación que un miembro del jurado da a una actuación.
 *
 * <p><b>Supuesto pendiente de confirmar con el cliente</b> (ver {@code docs/requisitos.md}):
 * escala de 0 a 10 con dos decimales. Los importes y notas se guardan en {@link BigDecimal},
 * nunca en {@code double}: en binario {@code 0.1 + 0.2} no da exactamente {@code 0.3}.</p>
 *
 * @param valor nota con dos decimales, entre {@link #MINIMA} y {@link #MAXIMA}
 */
public record Nota(BigDecimal valor) implements Comparable<Nota> {

    /** Nota mínima admitida. */
    public static final BigDecimal MINIMA = BigDecimal.ZERO;
    /** Nota máxima admitida. */
    public static final BigDecimal MAXIMA = BigDecimal.TEN;

    /** Constructor compacto: fija dos decimales y rechaza notas fuera de rango. */
    public Nota {
        Objects.requireNonNull(valor, "nota");
        valor = valor.setScale(2, RoundingMode.HALF_UP);
        if (valor.compareTo(MINIMA) < 0 || valor.compareTo(MAXIMA) > 0) {
            throw new NotaInvalidaException(valor);
        }
    }

    /**
     * Factoría con nombre: se lee mejor que el constructor.
     *
     * @param texto nota como texto, p. ej. {@code "7.5"}
     * @return la nota
     */
    public static Nota de(String texto) {
        return new Nota(new BigDecimal(texto));
    }

    @Override
    public int compareTo(Nota otra) {
        return valor.compareTo(otra.valor);
    }

    /** La nota está fuera del rango permitido. */
    public static class NotaInvalidaException extends BusinessException {
        public NotaInvalidaException(BigDecimal valor) {
            super("La nota debe estar entre 0 y 10 (recibida: " + valor + ")");
        }
    }
}
