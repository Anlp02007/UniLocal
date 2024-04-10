package co.edu.uniquindio.uniLocal.dto.ComentarioDTO;

import java.time.LocalDate;

public record ComentarioDTOGet(

        String nombre,
        String foto,
        LocalDate fecha,
        String mensaje,
        int calificaion

) {
}
