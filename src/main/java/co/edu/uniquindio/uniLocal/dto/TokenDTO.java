package co.edu.uniquindio.uniLocal.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(

        @NotBlank
        String token
) {
}
