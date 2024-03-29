package co.edu.uniquindio.uniLocal.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroClienteDTO(

        @NotBlank @Length(max = 100) String nombre,
        String fotoPerfil,
        @NotBlank  @Length(max = 10) String nickname,
        @NotBlank @Email @Length(max = 100) String email,
        @NotBlank @Length(min = 7) String password,
        @NotBlank String ciudadResidencia
) {
}
