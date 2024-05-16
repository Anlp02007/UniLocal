package co.edu.uniquindio.uniLocal.dto.ClienteDTO;

import co.edu.uniquindio.uniLocal.modelo.enums.Ciudad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record ItemClienteDTO(
        String codigoCliente,
        @NotBlank @Length(max = 100) String nombre,
        String fotoPerfil,
        @NotBlank  @Length(max = 10)String nickname,
        @NotNull Ciudad ciudad) {
}
