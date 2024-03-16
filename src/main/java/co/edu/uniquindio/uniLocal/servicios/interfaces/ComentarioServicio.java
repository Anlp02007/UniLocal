package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ResponderComDTO;

public interface ComentarioServicio {

    void crearComentario(ComentarioDTO comentarioDTO) throws Exception;

    void responderComentario(ResponderComDTO responderComDTO) throws Exception;

    void listarComentariosNegocio(String codigoNegocio);

    void calcularPromedioCalificaciones();
}
