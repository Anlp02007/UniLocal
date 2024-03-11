package co.edu.uniquindio.uniLocal.modelo.entidades;

import lombok.*;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Ubicacion implements Serializable {

    private double latitud;
    private double longitud;



}
