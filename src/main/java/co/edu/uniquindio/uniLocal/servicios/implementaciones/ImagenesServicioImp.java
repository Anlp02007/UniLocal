package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.servicios.interfaces.ImagenesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Transactional
@Service
@RequiredArgsConstructor
public class ImagenesServicioImp implements ImagenesServicio {
    @Override
    public Map subirImagen(MultipartFile imagen) {
        return null;
    }

    @Override
    public Map eliminarImagen(String id) {
        return null;
    }
}
