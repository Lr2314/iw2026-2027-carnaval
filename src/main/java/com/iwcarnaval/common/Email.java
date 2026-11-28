package com.iwcarnaval.common;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Objeto valor: dirección de correo electrónico.
 *
 * <p>Es un {@code record}: inmutable, con igualdad por valor, y <b>válido al nacer</b>:
 * un correo con formato incorrecto no llega a existir.</p>
 *
 * @param valor dirección en minúsculas y sin espacios
 */
public record Email(String valor) {

    private static final Pattern FORMATO = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    /** Constructor compacto: normaliza y valida. */
    public Email {
        Objects.requireNonNull(valor, "correo");
        valor = valor.trim().toLowerCase();
        if (!FORMATO.matcher(valor).matches()) {
            throw new EmailInvalidoException(valor);
        }
    }

