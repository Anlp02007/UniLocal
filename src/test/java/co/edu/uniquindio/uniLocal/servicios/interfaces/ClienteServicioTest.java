package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.FavoritosClienteDTO;
import co.edu.uniquindio.uniLocal.dto.DetalleClienteDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;



    @Test
    void editarPerfilSuccess() throws Exception{

        ActualizarClienteDTO registroClienteDTO = new ActualizarClienteDTO(
                "6605d5bd79d2d70e68caeded",
                "Juanito 2",
                "mi foto",
                "juan1@email.com",
                "Armenia"
        );
            clienteServicio.editarPerfil(registroClienteDTO);
    }

    @Test
    public void editarPerfilException() throws Exception{

        ActualizarClienteDTO registroClienteDTO = new ActualizarClienteDTO(
                "0",
                "Juanito 2",
                "mi foto",
                "juan2@email.com",
                "Armenia"
        );
        try{
            clienteServicio.editarPerfil(registroClienteDTO);
            fail("Se esperaba que lanzara una excepción, ya que el id del cliente no esta resgistrado.");
        }catch (Exception e){

        }
    }

    @Test
    public  void getClienteTest() throws  Exception{

        String codigoCliente = "Cliente3";

        DetalleClienteDTO detalleClienteDTO = clienteServicio.getCliente(codigoCliente);

        Assertions.assertNotNull(detalleClienteDTO);

    }

    @Test
    void getAllClientesTest() throws Exception{

        int cantidad =  clienteServicio.findAllClients().size();
        Assertions.assertTrue(cantidad > 0);

    }

    @Test
    void eliminarClienteTest() throws Exception{

        String id = "Cliente3";
        clienteServicio.eliminarCuenta(id);
    }

    @Test
    void enviarLinkRecuperacionTest() throws Exception{

        String id = "Cliente2";
        clienteServicio.enviarLinkRecuperacion(id);
    }

    @Test
    void agregarNegocioToFavoritosTest()throws  Exception{

        FavoritosClienteDTO favoritosClienteDTO = new FavoritosClienteDTO("Cliente2", "Negocio2");
        clienteServicio.agregarNegocioToFavoritos(favoritosClienteDTO);
    }

    @Test
    void listarFavoritos () throws Exception{

        String idCliente = "Cliente1";
        List<NegocioGetDTO> listaFavoritos = clienteServicio.listarFavoritos(idCliente);
        Assertions.assertTrue(!listaFavoritos.isEmpty());
    }

    @Test
    void eliminarFavoritos () throws Exception{

        FavoritosClienteDTO favoritosClienteDTO = new FavoritosClienteDTO("Cliente1", "Negocio2");

        String codigo = clienteServicio.eliminarNegocioFavoritos(favoritosClienteDTO);

        Assertions.assertNotNull(codigo);
    }

    @Test
    void agregarNegocioToRecomendacionesTest() throws Exception {

        String codigoCliente = "Cliente1";
        String codigoNegocio = "Negocio2";
        clienteServicio.agregarNegocioToRecomendaciones(codigoNegocio,codigoNegocio);
    }

    @Test
    void listarNegocioRecomendados() throws Exception{

        String codigoCliente = "Cliente1";

        List<NegocioGetDTO> negocioGetDTOS = clienteServicio.listarRecomendaciones(codigoCliente);

        Assertions.assertFalse(negocioGetDTOS.isEmpty());
    }
    @Test
    void eliminarNegocioRecomenacionesTest() throws Exception{

        String codigoCliente = "Cliente2";
        String codgioNegocio = "Negocio1";

        String neg = clienteServicio.eliminarNegocioRecomendaciones(codigoCliente,codgioNegocio);
        Assertions.assertNotNull(neg);

    }



}
