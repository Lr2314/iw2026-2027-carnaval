package com.iwcarnaval.views;

import com.iwcarnaval.actuacion.TipoPieza;
import com.iwcarnaval.agrupacion.Categoria;
import com.iwcarnaval.agrupacion.EstadoInscripcion;
import com.iwcarnaval.agrupacion.Modalidad;
import com.iwcarnaval.common.Nota;
import com.iwcarnaval.edicion.Fase;
import com.iwcarnaval.usuario.Rol;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Etiquetas {

    private static final Locale ES = Locale.of("es", "ES");
    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'de' yyyy", ES);

    private Etiquetas() {
    }

    public static String modalidad(Modalidad m) {
        return m == null ? "" : switch (m) {
            case COMPARSA -> "Comparsa";
            case CHIRIGOTA -> "Chirigota";
            case CORO -> "Coro";
            case CUARTETO -> "Cuarteto";
        };
    }

    public static String categoria(Categoria c) {
        return c == null ? "" : switch (c) {
            case ADULTOS -> "Adultos";
            case JUVENIL -> "Juvenil";
            case INFANTIL -> "Infantil";
        };
    }

    public static String fase(Fase f) {
        return f == null ? "" : switch (f) {
            case PRELIMINAR -> "Preliminar";
            case CUARTOS -> "Cuartos de final";
            case SEMIFINAL -> "Semifinal";
            case FINAL -> "Final";
        };
    }

    public static String estado(EstadoInscripcion e) {
        return e == null ? "" : switch (e) {
            case PENDIENTE_FIANZA -> "Pendiente de fianza";
            case CONFIRMADA -> "Confirmada";
            case RETIRADA -> "Retirada";
        };
    }

    public static String tipoPieza(TipoPieza t) {
        return t == null ? "" : switch (t) {
            case PRESENTACION -> "Presentación";
            case PASODOBLE -> "Pasodoble";
            case CUPLE -> "Cuplé";
            case ESTRIBILLO -> "Estribillo";
            case POPURRI -> "Popurrí";
        };
    }

    public static String rol(Rol r) {
        return r == null ? "" : switch (r) {
            case MUNICIPAL -> "Personal municipal";
            case JURADO -> "Jurado";
            case AGRUPACION -> "Agrupación";
        };
    }

    public static String fecha(LocalDate fecha) {
        return fecha == null ? "" : FECHA.format(fecha);
    }

    public static String nota(Nota nota) {
        return nota == null ? "—" : String.format(ES, "%.2f", nota.valor());
    }

    public static String euros(BigDecimal importe) {
        return importe == null ? "" : String.format(ES, "%.2f €", importe);
    }

    public static String si(boolean valor) {
        return valor ? "Sí" : "No";
    }
}
