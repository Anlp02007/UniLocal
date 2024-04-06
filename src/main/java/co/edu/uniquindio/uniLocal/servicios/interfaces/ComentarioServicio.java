package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ListaComentariosDTO;
import co.edu.uniquindio.uniLocal.dto.ResponderComDTO;

public interface ComentarioServicio {

    void crearComentario(ComentarioDTO comentarioDTO) throws Exception;

    void responderComentario(ResponderComDTO responderComDTO) throws Exception;

    void listarComentarioNegocio(String codigoNegocio);

    float calcularPromedioCalificaciones(String codigoNegocio);
}
