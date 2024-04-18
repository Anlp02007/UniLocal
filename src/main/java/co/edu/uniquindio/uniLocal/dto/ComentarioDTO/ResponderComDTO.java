package co.edu.uniquindio.uniLocal.dto.ComentarioDTO;

import java.time.LocalDate;

public record ResponderComDTO(
        LocalDate fecha,
        String codigoCliente,
        String codigoComentario,
        String codigoNegocio,
        String respuesta


) {
}
