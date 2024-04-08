package co.edu.uniquindio.uniLocal.dto;

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
        @NotBlank String ciudad) {

}
