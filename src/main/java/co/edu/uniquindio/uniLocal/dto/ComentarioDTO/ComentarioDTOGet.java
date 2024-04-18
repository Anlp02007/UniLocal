package co.edu.uniquindio.uniLocal.dto.ComentarioDTO;

import java.time.LocalDate;
import java.util.List;

public record ComentarioDTOGet(

        String nombre,
        String foto,
        LocalDate fecha,
        String mensaje,
        int calificaion,
        List<String> respuesta

) {
}
