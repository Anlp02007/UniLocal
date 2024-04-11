package co.edu.uniquindio.uniLocal.dto.PedidoDTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record PedidoDTOGet(
        LocalDate fecha,
        @NotBlank @Length(max = 100) String descripcion,
        @NotBlank String nombreCliente,
        @NotBlank String nombreNegocio
) {
}
