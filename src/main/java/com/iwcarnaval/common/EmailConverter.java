package com.iwcarnaval.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/** Guarda un {@link Email} como texto en la columna de la base de datos. */
@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email email) {
        return email == null ? null : email.valor();
    }

    @Override
    public Email convertToEntityAttribute(String texto) {
        return texto == null ? null : new Email(texto);
    }
}
