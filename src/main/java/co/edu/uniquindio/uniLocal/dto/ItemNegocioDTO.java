package co.edu.uniquindio.uniLocal.dto;

import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;

import java.util.List;

public record ItemNegocioDTO(

        String nombre,
        Ubicacion ubicacion,
        List<String> horario,
        List<String> imagen,
        String descripcion,
        List<String> telefono,
        EstadoRegistro estadoRegistro
) {
}
