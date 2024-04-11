package co.edu.uniquindio.uniLocal.dto.ClienteDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record ItemClienteDTO(
        String codigoCliente,
        @NotBlank @Length(max = 100) String nombre,
        String fotoPerfil,
        @NotBlank  @Length(max = 10)String nickname,
        @NotBlank String ciudad) {
}
