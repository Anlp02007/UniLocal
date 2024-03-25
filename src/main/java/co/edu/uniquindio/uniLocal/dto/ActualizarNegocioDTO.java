package co.edu.uniquindio.uniLocal.dto;

import co.edu.uniquindio.uniLocal.modelo.entidades.Horario;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarNegocioDTO(

        String id,
        @NotBlank @Length(max = 100) String nombre,
        List<String> telefono,
        List<Horario> horario,
        List<String> imagen,
        Ubicacion ubicacion



) {
}
