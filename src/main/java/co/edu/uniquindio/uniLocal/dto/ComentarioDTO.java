package co.edu.uniquindio.uniLocal.dto;

import java.time.LocalDate;

public record ComentarioDTO(
        //atributos clase comentario DTO K
        LocalDate fecha,

        String codigoCliente,
        String codigoComentario,
        String codigoNegocio,

        String mensaje


) {
}
