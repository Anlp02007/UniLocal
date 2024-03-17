package co.edu.uniquindio.uniLocal.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemModeradorDTO(
        String codigoModerador,
        @NotBlank @Length(max = 100) String nombre) {
}
