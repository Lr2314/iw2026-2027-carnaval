package com.iwcarnaval.common;

/**
 * Colaborador externo: envío de avisos a los usuarios.
 *
 * <p>Los servicios dependen de esta <b>interfaz</b>, no de una implementación. Hoy la
 * implementación solo escribe en el log ({@link NotificadorLog}); sustituirla por una de correo
 * SMTP no obliga a tocar ningún servicio.</p>
 */
public interface Notificador {

    /**
     * @param destinatario dirección de correo del destinatario
     * @param asunto       asunto del aviso
     * @param mensaje      cuerpo del mensaje
     */
    void enviar(String destinatario, String asunto, String mensaje);
}
