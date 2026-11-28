package com.iwcarnaval.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Validación de DNI, NIE y CIF con su letra o dígito de control. */
class NifTest {

    @Test
    void dniValido() {
        assertThat(new Nif("12345678z").valor()).isEqualTo("12345678Z");
        assertThat(new Nif("12345678-Z").valor()).isEqualTo("12345678Z");
    }

    @Test
    void nieValido() {
        assertThat(Nif.esValido("X1234567L")).isTrue();
        assertThat(Nif.esValido("X1234567A")).isFalse();
    }

    @Test
    void cifValido() {
        assertThat(Nif.esValido("A58818501")).isTrue();
        assertThat(Nif.esValido("A58818502")).isFalse();
    }

    @Test
    void dniConLetraIncorrectaSeRechaza() {
        assertThatThrownBy(() -> new Nif("12345678A")).isInstanceOf(Nif.NifInvalidoException.class);
    }

    @Test
    void formatosIncorrectosSeRechazan() {
        assertThat(Nif.esValido("")).isFalse();
        assertThat(Nif.esValido("1234")).isFalse();
        assertThat(Nif.esValido("ABCDEFGHI")).isFalse();
    }
}
