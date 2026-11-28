package com.iwcarnaval;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Guarda de calidad: la aplicación no debe contener emoticonos ni referencias a material docente
 * (apuntes, temas o prácticas), ni en el código, ni en los textos de la interfaz, ni en la documentación.
 */
class LimpiezaTest {

    private static final Pattern EMOJI = Pattern.compile("[\\x{1F000}-\\x{1FAFF}\\x{2600}-\\x{27BF}\\x{2B00}-\\x{2BFF}\\x{FE0F}]");
    private static final Pattern TEMARIO = Pattern.compile(
            "(?i)\\b(tema|pr[aá]ctica|pauta|bloque)\\s+[0-9]|diapositiva|temario|apuntes|gu[ií]a de estudio");

    private static final List<String> EXTENSIONES = List.of(".java", ".css", ".properties", ".md", ".yml", ".xml");

    private List<Path> ficheros() throws IOException {
        try (Stream<Path> main = Files.walk(Path.of("src"));
             Stream<Path> docs = Files.walk(Path.of("docs"));
             Stream<Path> raiz = Stream.of(Path.of("README.md"), Path.of("AGENTS.md"), Path.of("pom.xml"))) {
            return Stream.of(main, docs, raiz).flatMap(s -> s)
                    .filter(Files::isRegularFile)
                    .filter(p -> EXTENSIONES.stream().anyMatch(p.toString()::endsWith))
                    .filter(p -> !p.endsWith("LimpiezaTest.java"))
                    .filter(p -> !p.toString().contains("doxygen"))
                    .toList();
        }
    }

    @Test
    void noHayEmoticonos() throws IOException {
        for (Path fichero : ficheros()) {
            assertThat(EMOJI.matcher(Files.readString(fichero)).find())
                    .as("emoticono en %s", fichero).isFalse();
        }
    }

    @Test
    void noHayReferenciasAlTemario() throws IOException {
        for (Path fichero : ficheros()) {
            var m = TEMARIO.matcher(Files.readString(fichero));
            assertThat(m.find()).as("referencia al temario en %s%s", fichero, m.find(0) ? ": " + m.group() : "")
                    .isFalse();
        }
    }
}
