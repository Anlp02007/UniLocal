package co.edu.uniquindio.uniLocal.servicios.interfaces;


import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTO;
import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTOGet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PedidoServicioTest {

    @Autowired
    private  PedidoServicio pedidoServicio;


    @Test
    void hacerPedidoTest() throws Exception{


        PedidoDTO pedidoDTO = new PedidoDTO(
                "Pedido6",
                LocalDate.now(),
                "Se hizo un pedido",
                "Cliente1",
                "Negocio1"
        );

        pedidoServicio.hacerPedido(pedidoDTO);
    }

    void listarPedidoPorCliente() throws Exception{

        String idCliente = "Cliente1";
        List<PedidoDTOGet> pedidos = pedidoServicio.obtenerPedidoPorCliente(idCliente);
        Assertions.assertFalse(pedidos.isEmpty());
    }

    void listarPedidoPorNegocio() throws Exception{

        String idNegocio = "Negocio1";
        List<PedidoDTOGet> pedidos = pedidoServicio.obtenerPedidoPorNegocio(idNegocio);
        Assertions.assertFalse(pedidos.isEmpty());
    }


}
