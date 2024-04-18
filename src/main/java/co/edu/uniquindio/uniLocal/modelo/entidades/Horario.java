package co.edu.uniquindio.uniLocal.modelo.entidades;

import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Horario implements Serializable {

    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
