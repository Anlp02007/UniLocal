package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;

import java.util.List;

public interface NegocioServicio {



    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;


    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    NegocioGetDTO buscarNegocioId(String idNegocio) throws Exception;


    String eliminarNegocio(String idNegocio) throws Exception;

    List<NegocioGetDTO>buscarNegocios(ItemNegocioDTO itemNegocioDTO) throws Exception;

     List<Negocio> filtrarPorEstado(EstadoNegocio estado) throws Exception;

    List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception;

    public void registrarRevision(HistorialRevisionDTO historialDTO) throws Exception;


}
