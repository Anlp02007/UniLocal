package co.edu.uniquindio.uniLocal.dto.ComentarioDTO;

import java.time.LocalDate;

public record ComentarioDTO(
        String foto,
        LocalDate fecha,

        String codigoCliente,

        String codigoNegocio,

        String codigoComentario,

        String mensaje,
        int calificaion


) {
}
