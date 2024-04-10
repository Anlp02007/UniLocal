package co.edu.uniquindio.uniLocal.dto;

import java.time.LocalDate;

public record ComentarioDTO(
        String nombre,
        String foto,
        LocalDate fecha,

        String mensaje,
        int calificaion


) {
}
