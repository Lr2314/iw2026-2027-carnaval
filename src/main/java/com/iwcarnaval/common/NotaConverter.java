package com.iwcarnaval.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigDecimal;

/** Guarda una {@link Nota} como número decimal en la columna de la base de datos. */
@Converter(autoApply = true)
public class NotaConverter implements AttributeConverter<Nota, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(Nota nota) {
        return nota == null ? null : nota.valor();
    }

    @Override
    public Nota convertToEntityAttribute(BigDecimal valor) {
        return valor == null ? null : new Nota(valor);
    }
}
