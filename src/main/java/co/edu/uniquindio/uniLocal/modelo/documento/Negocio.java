package co.edu.uniquindio.uniLocal.modelo.documento;

import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import lombok.*;
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
    private String codigoNegocio;
    private String codigoCliente;
    private String nombre;
    private String descripcion;
    private List <String> horario;
    private List <String> imagen;
    private List <EstadoRegistro>estado;
    private List <HistoriaRevicion> historiaRevicions;
    private List <TipoNegocio>tipoNegocio;
    private List <String> telefono;
}