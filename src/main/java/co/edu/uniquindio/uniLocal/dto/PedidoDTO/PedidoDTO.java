package co.edu.uniquindio.uniLocal.dto.PedidoDTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record PedidoDTO(

        @NotBlank String codigoPedido,
        LocalDate fecha,
        @NotBlank @Length(max = 100) String descripcion,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio

) {
}
