package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.*;

import java.util.List;

public interface ClienteServicio extends CuentaServicio{

    String registrarse(RegistroClienteDTO registroClienteDTO)throws Exception;

    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

    DetalleClienteDTO getCliente(String codiString)throws Exception;

    List<ItemClienteDTO> findAllClients()throws Exception;

     void enviarLinkRecuperacion(String email, EmailDTO emailDTO) throws Exception;


}
