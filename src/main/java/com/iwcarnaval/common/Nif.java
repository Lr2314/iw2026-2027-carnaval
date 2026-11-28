package com.iwcarnaval.common;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Objeto valor: identificación fiscal española (DNI, NIE o CIF) con su dígito o letra de control comprobados.
 *
 * <p>Un documento con formato o control incorrecto no llega a existir. Se normaliza a mayúsculas y sin
 * espacios ni guiones.</p>
 *
 * @param valor documento normalizado
 */
public record Nif(String valor) {

    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String LETRAS_CIF = "JABCDEFGHI";
    private static final Pattern DNI = Pattern.compile("\\d{8}[A-Z]");
    private static final Pattern NIE = Pattern.compile("[XYZ]\\d{7}[A-Z]");
    private static final Pattern CIF = Pattern.compile("[ABCDEFGHJNPQRSUVW]\\d{7}[0-9A-J]");

    /** Constructor compacto: normaliza y valida. */
    public Nif {
        Objects.requireNonNull(valor, "documento de identificación");
        valor = valor.replaceAll("[\\s-]", "").toUpperCase();
        if (!(esDni(valor) || esNie(valor) || esCif(valor))) {
            throw new NifInvalidoException(valor);
        }
    }
