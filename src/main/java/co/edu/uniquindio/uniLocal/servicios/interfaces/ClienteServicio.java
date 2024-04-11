package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.*;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.FavoritosClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;

import java.util.List;

public interface ClienteServicio extends CuentaServicio{

    String registrarse(RegistroClienteDTO registroClienteDTO)throws Exception;

    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

    DetalleClienteDTO getCliente(String codiString)throws Exception;

    List<ItemClienteDTO> findAllClients()throws Exception;

     void enviarLinkRecuperacion(String email) throws Exception;

     void agregarNegocioToFavoritos(FavoritosClienteDTO favoritoDTO) throws Exception;

     List<NegocioGetDTO> listarFavoritos(String idCliente) throws Exception;

     String eliminarNegocioFavoritos(FavoritosClienteDTO favoritosDTO) throws Exception;

     List<NegocioGetDTO> listarRecomendaciones(String idCliente) throws Exception;
     void agregarNegocioToRecomendaciones(String idCliente, Negocio negocio) throws Exception;
     String eliminarNegocioRecomendaciones(String idCliente, Negocio negocio) throws Exception;




}
