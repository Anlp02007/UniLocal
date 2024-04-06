package co.edu.uniquindio.uniLocal.dto;

public record MensajeDTO<T> (
        boolean error,
        T respuesta
){
}
