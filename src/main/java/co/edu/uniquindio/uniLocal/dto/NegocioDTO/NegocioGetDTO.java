package co.edu.uniquindio.uniLocal.dto.NegocioDTO;

import co.edu.uniquindio.uniLocal.modelo.entidades.Horario;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;

import java.util.List;

public record NegocioGetDTO(

        String codigoNegocio,
        String nombre,
        Ubicacion ubicacion,
        List<Horario> horario,
        List<String> imagen,
        String descripcion,
        TipoNegocio tipoNegocio,
        List<String> telefono,
        EstadoRegistro estadoRegistro
) {
}
