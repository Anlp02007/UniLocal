package co.edu.uniquindio.uniLocal.servicios.interfaces;


import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTO;
import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTOGet;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;

import java.util.List;

public interface PedidoServicio {


    void hacerPedido(PedidoDTO pedidoDTO) throws Exception;
    List<PedidoDTOGet> obtenerPedidoPorCliente(String idCLiente) throws Exception;
    List<PedidoDTOGet> obtenerPedidoPorNegocio(String idNegocio) throws Exception;

}
