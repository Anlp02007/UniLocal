package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.Horario;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;

import java.util.List;

public interface NegocioServicio {



    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;


    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    NegocioGetDTO buscarNegocioId(String idNegocio) throws Exception;


    String eliminarNegocio(String idNegocio) throws Exception;

    List<NegocioGetDTO> buscarNegocios(ItemNegocioDTO itemNegocioDTO,String idCliente) throws Exception;

    List<Negocio> filtrarPorEstado(EstadoNegocio estado) throws Exception;

   // public void registrarRevision(HistorialRevisionDTO historialDTO) throws Exception;
    boolean verificarSiEstaAbierto(List<Horario> horarios);

    List<NegocioGetDTO> listarNegociosPorCliente(String codigo) throws Exception;


}
