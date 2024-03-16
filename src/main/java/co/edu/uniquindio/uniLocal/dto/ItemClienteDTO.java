package co.edu.uniquindio.uniLocal.dto;

import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemClienteDTO(
        String codigoCliente,
        @NotBlank @Length(max = 100) String nombre,
        String fotoPerfil,
        @NotBlank  @Length(max = 10)String nickname,
        @NotBlank String ciudad) {
}
