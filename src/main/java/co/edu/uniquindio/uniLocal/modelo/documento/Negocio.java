package co.edu.uniquindio.uniLocal.modelo.documento;

import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.entidades.Horario;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Negocio implements Serializable {

    private Ubicacion ubicacion;
    @Id
    private String codigoNegocio;
    private String codigoCliente;
    private String nombre;
    private String descripcion;
    private List <Horario> horario;
    private List <String> imagen;
    private EstadoRegistro estadoRegistros;
    private List<HistoriaRevicion> historialRevicion;
    private TipoNegocio tipoNegocio;
    private List <String> telefono;
    private EstadoNegocio estadoNegocio;
}
