package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.TokenDTO;

public interface AutenticacionServicio {
    TokenDTO iniciarSesionCliente(LoginDTO loginDTO);
}
