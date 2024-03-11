package co.edu.uniquindio.uniLocal.modelo.entidades;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)

public class Horario implements Serializable {

    private String dia;
    private LocalDate horaInicio;
    private LocalDate horaFin;
}
