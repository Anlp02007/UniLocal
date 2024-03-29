package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.Horario;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
        List<Horario> horario = new ArrayList<>();
        horario.add(new Horario("Lunes", LocalDate.now(),LocalDate.now()));
        List<String> imagen = new ArrayList<>();
        imagen.add("imagen1");
        imagen.add("imagen2");
        List<String> telefono = new ArrayList<>();
        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(
             "_negocio2",
             "negocio2",
             "este es el negocio 2",
             "660090d7d150c72ed3fbaa54",
             new Ubicacion(0,-40),
                horario,
                imagen,
                TipoNegocio.CAFETERIA,
                telefono

        );
        codigo = negocioServicio.crearNegocio(crearNegocioDTO);
        System.out.println(codigo);
        Assertions.assertEquals("_negocio2",codigo);
    }

    @Test
    public void crearNegocioFailTest() throws Exception{
        // se crea un objeto de tipo CrearNegocioDTO
        String codigo = "";
        List<Horario> horario = new ArrayList<>();
        List<String> imagen = new ArrayList<>();
        List<String> telefono = new ArrayList<>();
        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(
                "_negocio4",
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
            fail("Se esperaba que lanzara una excepción, ya que el negocio ya existe.");
        }catch (Exception e){

        }

    }

    @Test
    void actualizarNegocioSuccessTest() throws Exception{
        //se crea un objeto ActualizarNegocioDTO
        List<String> telefono = new ArrayList<>();
        List<Horario> horario = new ArrayList<>();
        horario.add(new Horario("", LocalDate.now(),LocalDate.now()));
        List<String> imagen = new ArrayList<>();
        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "_negocio4",
                "negocio1",
                telefono,
                horario,
                imagen,
                new Ubicacion(0,0)
        );
            negocioServicio.actualizarNegocio(actualizarNegocioDTO);
    }

    @Test
    void actualizarNegocioFailTest() throws Exception{
        //se crea un objeto ActualizarNegocioDTO
        List<String> telefono = new ArrayList<>();
        List<Horario> horario = new ArrayList<>();
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

        negocioServicio.eliminarNegocio("_negocio4");
    }

    @Test
    void eliminarNegocioFailTest() throws Exception {
        try {
            negocioServicio.eliminarNegocio("_negocio3");
            fail("Se esperaba que lanzara una excepción, ya que el negcio a eliminar no existe.");
        }catch (Exception e){
        }
    }

    @Test
    void buscarNegocioUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(
                null,u,null,null,null,null,null,null);
        List<Negocio> newgocio = negocioServicio.buscarNegocios(itemNegocioDTO);
        Assertions.assertEquals(newgocio.get(0).getCodigoNegocio(),"_negocio1");
        Assertions.assertEquals(newgocio.get(1).getCodigoNegocio(),"_negocio2");
        Assertions.assertEquals(newgocio.size(),1);
    }

    @Test
    void buscarNegocioTipoNegocioSuccess() throws Exception{

        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(
                null,null,null,null,null,TipoNegocio.CAFETERIA,null,null);
        List<Negocio> newgocio = negocioServicio.buscarNegocios(itemNegocioDTO);
        Assertions.assertEquals(newgocio.size(),3);
    }

    @Test
    void buscarNegocioNombreSuccess() throws Exception{

        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(
                "negocio2",null,null,null,null,null,null,null);
        List<Negocio> newgocio = negocioServicio.buscarNegocios(itemNegocioDTO);
        Assertions.assertEquals(newgocio.get(0).getCodigoNegocio(),"_negocio2");
        Assertions.assertEquals(newgocio.size(),1);

    }
    @Test
    void buscarNegocioNombreUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(
                "negocio2",u,null,null,null,null,null,null);
        List<Negocio> newgocio = negocioServicio.buscarNegocios(itemNegocioDTO);
        Assertions.assertEquals(newgocio.get(0).getCodigoNegocio(),"_negocio2");
        Assertions.assertEquals(newgocio.size(),1);

    }
    @Test
    void buscarNegocioNombreTipoNegocioSuccess() throws Exception{

        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(
                "negocio1",null,null,null,null,TipoNegocio.CAFETERIA,null,null);
        List<Negocio> newgocio = negocioServicio.buscarNegocios(itemNegocioDTO);
        Assertions.assertEquals(newgocio.get(0).getCodigoNegocio(),"_negocio4");
        Assertions.assertEquals(newgocio.size(),2);

    }
    @Test
    void buscarNegocioTipoNegocioUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(
                null,u,null,null,null,TipoNegocio.CAFETERIA,null,null);
        List<Negocio> newgocio = negocioServicio.buscarNegocios(itemNegocioDTO);
        Assertions.assertEquals(newgocio.get(0).getCodigoNegocio(),"_negocio1");
        Assertions.assertEquals(newgocio.size(),2);

    }
    @Test
    void buscarNegocioTipoNegocioNombreUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO("negocio2",u,null,null,null,TipoNegocio.CAFETERIA,null,null);
        List<Negocio> newgocio = negocioServicio.buscarNegocios(itemNegocioDTO);
        Assertions.assertEquals(newgocio.get(0).getCodigoNegocio(),"_negocio2");
        Assertions.assertEquals(newgocio.size(),1);

    }



}
