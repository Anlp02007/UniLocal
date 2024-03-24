package co.edu.uniquindio.uniLocal;

import co.edu.uniquindio.uniLocal.dto.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                "juanito1",
                "juan1@email.com",
                "mipassword",
                "Armenia"
        );

        String codigo = clienteServicio.registrarse(registroClienteDTO);

        Assertions.assertNotNull(codigo);
    }
}
