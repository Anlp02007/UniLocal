package co.edu.uniquindio.uniLocal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
@Builder
public record ActualizarModeradorDTO(

        String id,
        @NotBlank @Length(max = 100) String nombre,

        @NotBlank @Email @Length(max = 100) String email

) {
}
