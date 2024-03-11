package co.edu.uniquindio.uniLocal.modelo.documento;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {
    private LocalDate fecha;
    private int calificacion;
    private String codigoCliente;
    private String codigoComentario;
    private String codigoNegocio;
    private String mensaje;
    private String respuesta;

}
