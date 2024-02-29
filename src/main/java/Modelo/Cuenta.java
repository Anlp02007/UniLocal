package Modelo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("cuenta")
@Setter
@Getter
public class Cuenta implements Serializable {

    private String nombre;
    private String email;
    private String password;
    private EstadoRegistro estado;
}
