package com.example.carnaval.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {

    public HomeView() {
        H1 titulo = new H1("¡Bienvenido al COAC - Carnaval de Cádiz!");
        Paragraph subtitulo = new Paragraph("Plataforma web oficial y archivo histórico en marcha.");

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        add(titulo, subtitulo);
    }
}