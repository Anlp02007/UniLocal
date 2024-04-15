package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.servicios.interfaces.ImagenesServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service

public class ImagenesServicioImp implements ImagenesServicio {

    private final Cloudinary cloudinary;
    public ImagenesServicioImp(){

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "diuk93dmf");
        config.put("api_key", "733483498691887");
        config.put("api_secret", "R6xuRAx9h03n0xadg3ibunMPHpE");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {

        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "unilocal"));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }


    private File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
