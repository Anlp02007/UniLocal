package co.edu.uniquindio.uniLocal.modelo.documento;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    private LocalDate fecha;
    private int calificacion; //esta calificacion es dada al comentario o dada al negocio al cual se le esta haciendo el comentario // como es dada esta clasificacion? en base a que jucios rangos?? cantidad de personas que lo pueden dar, etc
    private String codigoCliente;
    @Id
    private String codigoComentario;
    private String codigoNegocio;
    private String mensaje;
    private List<String> respuesta;
}
