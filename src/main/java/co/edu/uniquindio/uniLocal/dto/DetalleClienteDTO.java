package co.edu.uniquindio.uniLocal.dto;

import co.edu.uniquindio.uniLocal.modelo.enums.Ciudad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record DetalleClienteDTO(

        String codigoCliente,
        @NotBlank @Length(max = 100)String nombre,
        String fotoPerfil,
        @NotBlank  @Length(max = 10)String nickname,
        @NotBlank @Email @Length (max = 100) String email,
        @NotBlank Ciudad ciudad) {

}
