package co.edu.uniquindio.uniLocal.dto;

import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CrearNegocioDTO(

        @NotBlank String codigoNegocio,
        @Length(max = 100) String nombre,
        @NotBlank String descripcion,
        String codigoPropietario,
        Ubicacion ubicacion,
        List<String> horario,
        List<String> imagen,
        TipoNegocio tipoNegocios,
        List<String> telefono
) {



}
