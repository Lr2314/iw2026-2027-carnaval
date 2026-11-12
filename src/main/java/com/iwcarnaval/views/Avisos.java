package com.iwcarnaval.views;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

/**
 * Avisos al usuario. Las vistas capturan
 * {@code BusinessException} y muestran su mensaje con {@link #error(String)}.
 */
public final class Avisos {

    private Avisos() {
    }

    public static void exito(String mensaje) {
        Notification n = Notification.show(mensaje, 4000, Notification.Position.BOTTOM_CENTER);
        n.addThemeVariants(NotificationVariant.SUCCESS);
    }

    public static void error(String mensaje) {
        Notification n = Notification.show(mensaje, 6000, Notification.Position.BOTTOM_CENTER);
        n.addThemeVariants(NotificationVariant.ERROR);
    }
}
