package co.edu.uniquindio.uniLocal.modelo.documento;

import co.edu.uniquindio.uniLocal.modelo.entidades.Cuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Moderador extends Cuenta {

    @Id
    private String codigoModerador;
}
