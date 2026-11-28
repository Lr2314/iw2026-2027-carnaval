package com.iwcarnaval.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** Implementación simulada de {@link Notificador}: registra el aviso en el log. */
@Component
public class NotificadorLog implements Notificador {

    private static final Logger log = LoggerFactory.getLogger(NotificadorLog.class);

    @Override
    public void enviar(String destinatario, String asunto, String mensaje) {
        log.info("Aviso para {}: [{}] {}", destinatario, asunto, mensaje);
    }
}
