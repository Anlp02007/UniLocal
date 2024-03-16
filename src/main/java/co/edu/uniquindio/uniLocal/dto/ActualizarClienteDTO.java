package co.edu.uniquindio.uniLocal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizarClienteDTO (
        //attributos de actualizar cliente M
        //maximo de carracteres por cada atributo
        String id,
        @NotBlank @Length(max = 100) String nombre,
        String fotoPerfil,
        @NotBlank @Email @Length(max = 100) String email,
        @NotBlank String ciudadResidencia
){


}
