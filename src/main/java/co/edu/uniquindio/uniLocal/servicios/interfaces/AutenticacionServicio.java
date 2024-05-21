package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;
import co.edu.uniquindio.uniLocal.modelo.enums.Ciudad;

import java.util.List;

public interface AutenticacionServicio {
    TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception;

    TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception;

    Boolean ValidateUser(String token, String ClienteDTO) throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;

    String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception;

    List<Ciudad> listarCiudades();
}
