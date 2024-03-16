package co.edu.uniquindio.uniLocal.dto;

import java.time.LocalDate;

public record ResponderComDTO(
        LocalDate fecha,

        String codigoCliente,
        String codigoComentario,
        String codigoNegocio,
        String respuesta


) {
}
