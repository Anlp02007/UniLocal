package co.edu.uniquindio.uniLocal.modelo.entidades;

import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class HistoriaRevicion implements Serializable {

    private LocalDate fecha;
    private String descripcion;
    private EstadoNegocio estado;
    private String codigoModerador;

}
