package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTOGet;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ResponderComDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Test
    public void crearComentarioSuccessTest() throws Exception{
        ComentarioDTO comentarioDTO = new ComentarioDTO(
                "6605d5bd79d2d70e68caeded",
                LocalDate.now(),
                "1",
                "Negocio1",
                "_comentario1",
                "Excelente la cafeteria",
                5);


        comentarioServicio.crearComentario(comentarioDTO);
    }

    @Test
    public void responderComentarioSuccessTest() throws Exception{

        ResponderComDTO responderComDTO = new ResponderComDTO(LocalDate.now(),
                "6605d4e39372182a2317013c",
                "_comentario1",
                "Negocio1",
                "concuerdo");

        comentarioServicio.responderComentario(responderComDTO);
    }

    @Test
    void CalcularPromedioCalificacion() throws Exception{
        float promedio = comentarioServicio.calcularPromedioCalificaciones("_negocio1");
        System.out.println(promedio);
    }

    @Test
    void listarComentariosNegocio() throws Exception{

        String negocio = "Negocio1";
        List<ComentarioDTOGet> comentarios = comentarioServicio.listarComentariosNegocio(negocio);
        Assertions.assertFalse(comentarios.isEmpty());
    }

}
