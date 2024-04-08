package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;

public interface AutenticacionServicio {
    TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception;

    Boolean ValidateUser(String token, String ClienteDTO) throws Exception;

}
