package com.iwcarnaval.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

// @Route("") define esta clase como la página principal (http://localhost:8080/)
@Route("")
public class HomeView extends VerticalLayout {

    public HomeView() {
        // Título y descripción
        H1 titulo = new H1("🎭 COAC - Carnaval de Cádiz");
        Paragraph descripcion = new Paragraph("Bienvenido a la plataforma oficial del concurso y archivo histórico.");

        // Un formulario interactivo sencillo para probar Vaadin
        TextField nombreAgrupacion = new TextField("Nombre de tu agrupación de prueba:");
        Button botonInscribir = new Button("Probar inscripción");

        // Evento del botón (Java puro)
        botonInscribir.addClickListener(event -> {
            if (!nombreAgrupacion.isEmpty()) {
                Notification.show("¡Inscripción de prueba registrada: " + nombreAgrupacion.getValue() + "!");
                nombreAgrupacion.clear();
            } else {
                Notification.show("Escribe un nombre de agrupación primero.");
            }
        });

        // Configuración de alineación del layout
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        // Añadir elementos a la vista
        add(titulo, descripcion, nombreAgrupacion, botonInscribir);
    }
}