package co.edu.uniquindio.uniLocal.dto;

import java.time.LocalDate;

public record ComentarioDTO(
        LocalDate fecha,
        String codigoCliente,
        String codigoComentario,
        String codigoNegocio,
        String mensaje,
        int calificaion


) {
}
