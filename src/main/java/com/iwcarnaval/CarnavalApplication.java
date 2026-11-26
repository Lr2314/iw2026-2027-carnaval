package com.iwcarnaval;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.aura.Aura;
import com.vaadin.flow.component.dependency.StyleSheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.time.Clock;
import java.util.random.RandomGenerator;
import org.springframework.context.annotation.Bean;

/**
 * Clase principal de la aplicación.
 *
 * <p>{@code @SpringBootApplication} reúne {@code @Configuration},
 * {@code @EnableAutoConfiguration} y {@code @ComponentScan}: Spring escanea
 * este paquete y todos sus subpaquetes.</p>
 *
 * <p>Implementa {@link AppShellConfigurator} para cargar el tema Aura de
 * Vaadin 25.</p>
 */
@SpringBootApplication
@StyleSheet(Aura.STYLESHEET)
@StyleSheet("styles.css")
@StyleSheet("portal.css")
@StyleSheet("gestion.css")
public class CarnavalApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(CarnavalApplication.class, args);
    }

    /**
     * Reloj del sistema como bean. Los servicios piden la hora al {@link Clock}
     * en lugar de llamar a {@code LocalDate.now()}, y así las pruebas pueden
     * inyectar un reloj fijo.
     *
     * @return reloj en la zona horaria del sistema
     */
    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

    /**
     * Generador aleatorio para el sorteo. Se inyecta para que las pruebas puedan usar uno con semilla fija.
     *
     * @return generador criptográficamente seguro
     */
    @Bean
    RandomGenerator randomGenerator() {
        return new SecureRandom();
    }
}
