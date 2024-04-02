package co.edu.uniquindio.uniLocal.servicios.interfaces;

import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;

public interface ModeradorServicio extends CuentaServicio {

    HistoriaRevicion revisionNegocio(ItemNegocioDTO itemNegocioDTO, HistoriaRevicion historiaRevicion) throws Exception;
}
