package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;

import java.util.List;

public interface NegocioServicio {



    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;


    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    Negocio buscarNegocioId(String idNegocio) throws Exception;


    void eliminarNegocio(String idNegocio) throws Exception;

     List<Negocio>buscarNegocios(ItemNegocioDTO itemNegocioDTO) throws Exception;

     List<Negocio> filtrarPorEstado(EstadoRegistro estado) throws Exception;

    List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception;

    void cambiarEstado();

    void registrarRevision();
}
