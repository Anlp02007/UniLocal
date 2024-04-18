package co.edu.uniquindio.uniLocal.modelo.entidades;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Pedido implements Serializable {

    @Id
    private String codigoPedido;

    private String codigoCliente;
    private String codigoNegocio;
    private LocalDate fechaPedido;
    private String descripcion;

}
