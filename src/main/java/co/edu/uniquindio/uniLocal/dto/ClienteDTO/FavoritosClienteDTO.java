package co.edu.uniquindio.uniLocal.dto.ClienteDTO;

import jakarta.validation.constraints.NotBlank;

public record FavoritosClienteDTO(

        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio
) {


}
