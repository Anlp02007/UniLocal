package co.edu.uniquindio.uniLocal.modelo.documento;

import co.edu.uniquindio.uniLocal.modelo.entidades.Cuenta;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Moderador extends Cuenta {
    private String codigoModerador;
}
