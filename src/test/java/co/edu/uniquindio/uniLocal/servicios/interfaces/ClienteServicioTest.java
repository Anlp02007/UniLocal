package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.RegistroClienteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Test
    public void registrarTest() throws Exception {
//Se crea un objeto de tipo RegistroClienteDTO
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Juan",
                "mi foto",
                "juanito2",
                "juan2@email.com",
                "mipassword",
                "Armenia"
        );

        String codigo = clienteServicio.registrarse(registroClienteDTO);
        System.out.println(codigo);
        Assertions.assertNotNull(codigo);
    }

    @Test
    public void editarPerfilSuccess() throws Exception{

        ActualizarClienteDTO registroClienteDTO = new ActualizarClienteDTO(
                "660090d7d150c72ed3fbaa54",
                "Juanito 2",
                "mi foto",
                "juan2@email.com",
                "Armenia"
        );
        try{
            clienteServicio.editarPerfil(registroClienteDTO);

        }catch (Exception e){
            fail("Se esperaba que no lanzara una excepción, ya que esta modificando un cliente existente.");
        }
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

}
