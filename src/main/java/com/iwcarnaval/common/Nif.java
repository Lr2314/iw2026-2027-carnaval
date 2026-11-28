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

    /**
     * @param texto documento a comprobar
     * @return {@code true} si es un DNI, NIE o CIF válido
     */
    public static boolean esValido(String texto) {
        try {
            new Nif(texto);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static boolean esDni(String v) {
        return DNI.matcher(v).matches()
                && LETRAS_DNI.charAt(Integer.parseInt(v.substring(0, 8)) % 23) == v.charAt(8);
    }

    private static boolean esNie(String v) {
        if (!NIE.matcher(v).matches()) {
            return false;
        }
        int prefijo = "XYZ".indexOf(v.charAt(0));
        int numero = Integer.parseInt(prefijo + v.substring(1, 8));
        return LETRAS_DNI.charAt(numero % 23) == v.charAt(8);
    }

    private static boolean esCif(String v) {
        if (!CIF.matcher(v).matches()) {
            return false;
        }
        int suma = 0;
        for (int i = 1; i <= 7; i++) {
            int digito = v.charAt(i) - '0';
            if (i % 2 == 1) {                       // posiciones impares: se duplica y se suman las cifras
                digito *= 2;
                digito = digito / 10 + digito % 10;
            }
            suma += digito;
        }
        int control = (10 - suma % 10) % 10;
        char esperadoLetra = LETRAS_CIF.charAt(control);
        char esperadoDigito = (char) ('0' + control);
        char real = v.charAt(8);
        boolean exigeLetra = "PQRSNW".indexOf(v.charAt(0)) >= 0;
        boolean exigeDigito = "ABEH".indexOf(v.charAt(0)) >= 0;
        if (exigeLetra) {
            return real == esperadoLetra;
        }
        if (exigeDigito) {
            return real == esperadoDigito;
        }
        return real == esperadoLetra || real == esperadoDigito;
    }

    /** El documento de identificación no es válido. */
    public static class NifInvalidoException extends BusinessException {
        public NifInvalidoException(String valor) {
            super("Documento de identificación no válido (DNI, NIE o CIF): " + valor);
        }
    }
}
