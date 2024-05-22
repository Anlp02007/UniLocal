package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.dto.ItemModeradorDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;

import java.util.List;

public interface ModeradorServicio extends CuentaServicio {

    HistoriaRevicion revisionNegocio(ItemNegocioDTO itemNegocioDTO, HistoriaRevicion historiaRevicion) throws Exception;

    public void registrarRevision(HistorialRevisionDTO historialDTO) throws Exception;

    void eliminarCuenta(String idCuenta) throws Exception;

    public void inactivarNegocios();

    void editarPerfil(ActualizarModeradorDTO actualizarModeradorDTO) throws Exception;

    List<ItemModeradorDTO> listarModeradoresActivos();

    List<ItemNegocioDTO> ListarNegocios();
    ActualizarModeradorDTO getModerador(String codiString)throws Exception;
}
