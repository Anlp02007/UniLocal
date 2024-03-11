package co.edu.uniquindio.uniLocal.dto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
public record SesionDTO(
        @NotBlank @Email String email,
        @NotBlank  String password
) {
}
