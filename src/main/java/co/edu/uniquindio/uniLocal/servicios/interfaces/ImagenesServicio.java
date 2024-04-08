package co.edu.uniquindio.uniLocal.servicios.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImagenesServicio {
    Map subirImagen(MultipartFile imagen);

    Map eliminarImagen(String id);
}
