package co.edu.uniquindio.uniLocal.modelo.entidades;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Pedido implements Serializable {

    private String codigoPedido;
    private String codigoClinete;
    private LocalDate fechaPedido;
    private String descripcion;

}
