package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.RegistroClienteDTO;

public interface ClienteServicio extends CuentaServicio{

    String registrarse(RegistroClienteDTO registroClienteDTO)throws Exception;

    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;


}
