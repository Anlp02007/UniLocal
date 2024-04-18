package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTOGet;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ResponderComDTO;

import java.util.List;

public interface ComentarioServicio {

    void crearComentario(ComentarioDTO comentarioDTO) throws Exception;

    void responderComentario(ResponderComDTO responderComDTO) throws Exception;

    List<ComentarioDTOGet> listarComentariosNegocio(String codigoNegocio);

    float calcularPromedioCalificaciones(String codigoNegocio);
}
