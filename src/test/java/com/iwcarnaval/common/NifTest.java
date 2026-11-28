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
