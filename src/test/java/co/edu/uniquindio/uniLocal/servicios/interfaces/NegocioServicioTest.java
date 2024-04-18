package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.modelo.entidades.Horario;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
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
        horario.add(new Horario("Lunes", LocalTime.of(7,0),LocalTime.of(18,0)));
        List<String> imagen = new ArrayList<>();
        imagen.add("imagen1");
        imagen.add("imagen2");
        List<String> telefono = new ArrayList<>();
        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(

             "negocio 6",
             "este es el negocio 6",
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

                "negocio 4",
                "este es el negocio 4",
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
        horario.add(new Horario("", LocalTime.of(7,0),LocalTime.of(18,0)));
        List<String> imagen = new ArrayList<>();
        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(

                "Negocio4",
                "Negocio 4",
                telefono,
                horario,
                imagen,
                new Ubicacion(0,0),
                "Vendemos empanadas"
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
                "Negocio3",
                "negocio 3",
                telefono,
                horario,
                imagen,
                new Ubicacion(0,0),
                "Vendemos arepas rellenas"
        );

        try {
            negocioServicio.actualizarNegocio(actualizarNegocioDTO);
            fail("Se esperaba que lanzara una excepción, ya que el negcio a actualizar no existe.");
        }catch (Exception e){
        }
    }

    @Test
    void eliminarNegocioSuccessTest() throws Exception {

        negocioServicio.eliminarNegocio("Negocio3");
    }

    @Test
    void eliminarNegocioFailTest() throws Exception {
        try {
            negocioServicio.eliminarNegocio("Negocio10");
            fail("Se esperaba que lanzara una excepción, ya que el negcio a eliminar no existe.");
        }catch (Exception e){
        }
    }

    @Test
    void buscarNegocioUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(null,
                null,u,null,null,null,null,null,null);
        List<NegocioGetDTO> negocios = negocioServicio.buscarNegocios(itemNegocioDTO,"Cliente1");
        Assertions.assertEquals(negocios.get(0).codigoNegocio(),"Negocio1");
        Assertions.assertEquals(negocios.get(1).codigoNegocio(),"Negocio2");
        Assertions.assertEquals(negocios.size(),1);
    }

    @Test
    void buscarNegocioTipoNegocioSuccess() throws Exception{

        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(null,
                null,null,null,null,null,TipoNegocio.CAFETERIA,null,null);
        List<NegocioGetDTO> negocios = negocioServicio.buscarNegocios(itemNegocioDTO,"Cliente1");
        Assertions.assertEquals(negocios.size(),3);
    }

    @Test
    void buscarNegocioNombreSuccess() throws Exception{

        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(
                null,"negocio 3",null,null,null,null,null,null,null);
        List<NegocioGetDTO> negocios = negocioServicio.buscarNegocios(itemNegocioDTO,"Cliente1");
        Assertions.assertEquals(negocios.get(0).codigoNegocio(),"Negocio3");
        Assertions.assertEquals(negocios.size(),1);

    }
    @Test
    void buscarNegocioNombreUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(null,
                "negocio 2",u,null,null,null,
                null,null,null);
        List<NegocioGetDTO> negocios = negocioServicio.buscarNegocios(itemNegocioDTO,"Cliente1");
        Assertions.assertEquals(negocios.get(0).codigoNegocio(),"_negocio2");
        Assertions.assertEquals(negocios.size(),1);

    }
    @Test
    void buscarNegocioNombreTipoNegocioSuccess() throws Exception{

        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(null,
                "negocio1",null,null,null,null,TipoNegocio.CAFETERIA,null,null);
        List<NegocioGetDTO> negocios = negocioServicio.buscarNegocios(itemNegocioDTO,"Cliente1");
        Assertions.assertEquals(negocios.get(0).codigoNegocio(),"_negocio4");
        Assertions.assertEquals(negocios.size(),2);

    }
    @Test
    void buscarNegocioTipoNegocioUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(null,
                null,u,null,null,null,TipoNegocio.CAFETERIA,null,null);
        List<NegocioGetDTO> negocios = negocioServicio.buscarNegocios(itemNegocioDTO,"Cliente1");
        Assertions.assertEquals(negocios.get(0).codigoNegocio(),"_negocio1");
        Assertions.assertEquals(negocios.size(),2);

    }
    @Test
    void buscarNegocioTipoNegocioNombreUbicacionSuccess() throws Exception{
        Ubicacion u = new Ubicacion(0,-40);
        ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(null,
                "negocio 3",u,null,null,null,
                TipoNegocio.CAFETERIA,null,null);
        List<NegocioGetDTO> negocios = negocioServicio.buscarNegocios(itemNegocioDTO,"Cliente1");
        Assertions.assertEquals(negocios.get(0).codigoNegocio(),"Negocio3");
        Assertions.assertEquals(negocios.size(),1);

    }

    @Test
    void elimnarNegocioTest() throws Exception{

        String codigoNegocio = "Negocio3";

       String negocioEliminado =  negocioServicio.eliminarNegocio(codigoNegocio);
       Assertions.assertNotNull(negocioEliminado);
    }

    @Test
    void buscarNegocioId () throws Exception{

        String codigoNegocio = "Negocio1";
        NegocioGetDTO negocioGetDTO = negocioServicio.buscarNegocioId(codigoNegocio);
        Assertions.assertNotNull(negocioGetDTO);
    }

    void verificarSiEstaAbiertoTest() throws Exception{

        List<Horario> horarios = new ArrayList<>();
        horarios.add( new Horario("1", LocalTime.of(7, 10),LocalTime.of(18, 10) ));
        horarios.add( new Horario("2", LocalTime.of(7, 10),LocalTime.of(18, 10) ));
        horarios.add( new Horario("3", LocalTime.of(7, 10),LocalTime.of(18, 10) ));
        horarios.add( new Horario("4", LocalTime.of(7, 10),LocalTime.of(18, 10) ));
        horarios.add( new Horario("5", LocalTime.of(7, 10),LocalTime.of(18, 10) ));
        horarios.add( new Horario("6", LocalTime.of(7, 10),LocalTime.of(18, 10) ));
        horarios.add( new Horario("7", LocalTime.of(7, 10),LocalTime.of(18, 10) ));

        boolean abierto = negocioServicio.verificarSiEstaAbierto(horarios);
        Assertions.assertInstanceOf(Boolean.class, abierto);
    }

/*
    @Test
    void buscarListaNegocioPorEstado() throws Exception{
        List<Negocio> negocios = negocioServicio.filtrarPorEstado(EstadoNegocio.APROBADO);
        assertEquals(2,negocios.size());
        System.out.println(negocios.get(0).getCodigoNegocio());
        System.out.println(negocios.get(1).getCodigoNegocio());
        assertEquals("_negocio1",negocios.get(0).getCodigoNegocio());
        assertEquals("_negocio2",negocios.get(1).getCodigoNegocio());
    }
 */

}
