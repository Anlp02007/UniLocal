package co.edu.uniquindio.uniLocal.modelo.documento;

import co.edu.uniquindio.uniLocal.modelo.entidades.Cuenta;
import co.edu.uniquindio.uniLocal.modelo.enums.Ciudad;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Cliente extends Cuenta {

    @Id
    private String codigoCliente;

    private String fotoPerfil;
    private String nickname;
    private Ciudad ciudad;
    private List<Negocio> favoritos;
    private List<Negocio> recomendaciones;

}
