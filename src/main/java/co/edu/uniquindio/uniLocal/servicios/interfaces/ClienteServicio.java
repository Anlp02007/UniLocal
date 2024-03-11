package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.CambioPasswordDTO;
import co.edu.uniquindio.uniLocal.dto.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.dto.SesionDTO;

public interface ClienteServicio extends CuentaServicio{

    void registrarse(RegistroClienteDTO registroClienteDTO)throws Exception;

    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;


}
