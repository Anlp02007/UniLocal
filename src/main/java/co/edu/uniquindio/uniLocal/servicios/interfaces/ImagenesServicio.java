package co.edu.uniquindio.uniLocal.servicios.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImagenesServicio {
    Map subirImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String id) throws  Exception;
}
