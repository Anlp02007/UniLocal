package co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO;

import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record HistorialRevisionDTO(
        LocalDate fecha,
        @NotBlank @Length(max = 100) String descripcion,
        @NotBlank EstadoNegocio estadoNegocio,
        @NotBlank String codigoModerador,
        @NotBlank String codigoNegocio

        ) {
}
