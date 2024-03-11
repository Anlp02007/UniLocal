package co.edu.uniquindio.uniLocal.modelo.entidades;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Compartir implements Serializable {

    private LocalDate fecha;
    private String codigoCliente;
    private String codigoNegocio;
    private String mensaje;
    private String respuesta;
    private String nombrePersonaEnvio;
}
