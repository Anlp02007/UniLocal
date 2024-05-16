package co.edu.uniquindio.uniLocal.dto.ClienteDTO;

import co.edu.uniquindio.uniLocal.modelo.enums.Ciudad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegistroClienteDTO(

        @NotBlank @Length(max = 100) String nombre,
        String fotoPerfil,
        @NotBlank  @Length(max = 10) String nickname,
        @NotBlank @Email @Length(max = 100) String email,
        @NotBlank @Length(min = 7) String password,
        @NotNull Ciudad ciudadResidencia
) {
}
