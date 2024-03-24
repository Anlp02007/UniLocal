package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class NegocioServicioTest {

    @Autowired
    private NegocioServicio negocioServicio;

    @Test
    public void crearNegocioSuccesTest() throws Exception{
        // se crea un objeto de tipo CrearNegocioDTO
        String codigo = "";
        List<String> horario = new ArrayList<>();
        List<String> imagen = new ArrayList<>();
        List<String> telefono = new ArrayList<>();
        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(
             "_negocio3",
             "negocio3",
             "este es el negocio 3",
             "660090d7d150c72ed3fbaa54",
             new Ubicacion(0,0),
                horario,
                imagen,
                TipoNegocio.CAFETERIA,
                telefono

        );
        codigo = negocioServicio.crearNegocio(crearNegocioDTO);
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void crearNegocioFailTest() throws Exception{
        // se crea un objeto de tipo CrearNegocioDTO
        String codigo = "";
        List<String> horario = new ArrayList<>();
        List<String> imagen = new ArrayList<>();
        List<String> telefono = new ArrayList<>();
        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(
                "_negocio3",
                "negocio3",
                "este es el negocio 3",
                "660090d7d150c72ed3fbaa54",
                new Ubicacion(0,0),
                horario,
                imagen,
                TipoNegocio.CAFETERIA,
                telefono

        );
        try{
            codigo = negocioServicio.crearNegocio(crearNegocioDTO);
            Assertions.assertNotNull(codigo);
            fail("Se esperaba que lanzara una excepción, ya que el negocio ya existe.");
        }catch (Exception e){

        }

    }

    @Test
    void actualizarNegocioSuccessTest() throws Exception{
        //se crea un objeto ActualizarNegocioDTO
        List<String> telefono = new ArrayList<>();
        List<String> horario = new ArrayList<>();
        List<String> imagen = new ArrayList<>();
        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "_negocio3",
                "negocio3",
                telefono,
                horario,
                imagen,
                new Ubicacion(0,0)
        );

        try {
            negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        }catch (Exception e){
            //"Se esperaba que no lanzara una excepción, ya que se actualiza el negcio.
        }
    }

    @Test
    void actualizarNegocioFailTest() throws Exception{
        //se crea un objeto ActualizarNegocioDTO
        List<String> telefono = new ArrayList<>();
        List<String> horario = new ArrayList<>();
        List<String> imagen = new ArrayList<>();
        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "_negocio3",
                "negocio3",
                telefono,
                horario,
                imagen,
                new Ubicacion(0,0)
        );

        try {
            negocioServicio.actualizarNegocio(actualizarNegocioDTO);
            fail("Se esperaba que lanzara una excepción, ya que el negcio a actualizar no existe.");
        }catch (Exception e){
        }
    }

    @Test
    void eliminarNegocioSuccessTest() throws Exception {
        try {
            negocioServicio.eliminarNegocio("_negocio3");
        }catch (Exception e){
        }
    }

    @Test
    void eliminarNegocioFailTest() throws Exception {
        try {
            negocioServicio.eliminarNegocio("_negocio3");
            fail("Se esperaba que lanzara una excepción, ya que el negcio a eliminar no existe.");
        }catch (Exception e){
        }
    }
}
