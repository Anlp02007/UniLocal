package co.edu.uniquindio.uniLocal.servicios.interfaces;


import co.edu.uniquindio.uniLocal.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AutenticacionServicioTest {

    @Autowired
    private AutenticacionServicio autenticacionServicio;


    @Test

    void loginClienteTest() throws Exception{

        LoginDTO loginDTO = new LoginDTO("maria@email.com", "mipassword");
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
        Assertions.assertNotNull(tokenDTO);

    }

    @Test
    void loginModeradorTest() throws Exception{

        LoginDTO loginDTO = new LoginDTO("Jul@gmail.com", "mipassword");
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
        Assertions.assertNotNull(tokenDTO);

    }

    @Test
    void registrarTest() throws Exception {
//Se crea un objeto de tipo RegistroClienteDTO
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Juan",
                "mi foto",
                "juanito1",
                "juan1@email.com",
                "mipassword",
                "Armenia"
        );

        String codigo = autenticacionServicio.registrarse(registroClienteDTO);
        System.out.println(codigo);
        Assertions.assertNotNull(codigo);
    }

}
