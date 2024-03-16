package co.edu.uniquindio.uniLocal.modelo.entidades;

import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import lombok.*;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Cuenta  {

    private String nombre;
    private String password;
    private String email;
    private EstadoRegistro estadoRegistro;


}
