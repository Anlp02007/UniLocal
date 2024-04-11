package co.edu.uniquindio.uniLocal.controladores;


import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTO;
import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTOGet;
import co.edu.uniquindio.uniLocal.servicios.interfaces.PedidoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedido")
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;

    @PostMapping("/crearPedido")
    public ResponseEntity<String> crearPedido(@RequestBody PedidoDTO pedidoDTO) throws Exception {
        pedidoServicio.hacerPedido(pedidoDTO);
        return ResponseEntity.ok("Se hizo el pedido correctamente");
    }

    @GetMapping("/listarPedidosClientes/{codigoCliente}")
    public ResponseEntity<List<PedidoDTOGet>> listarPedidosPorCliente(@PathVariable String codigoCliente) throws Exception {
        List<PedidoDTOGet> listaPedidos = pedidoServicio.obtenerPedidoPorCliente(codigoCliente);
        return ResponseEntity.ok(listaPedidos);
    }

    @GetMapping("/listarPedidosNegocio/{codigoNegocio}")
    public ResponseEntity<List<PedidoDTOGet>> listarPedidosPorNegocio(@PathVariable String codigoNegocio) throws Exception {
        List<PedidoDTOGet> listaPedidos = pedidoServicio.obtenerPedidoPorNegocio(codigoNegocio);
        return ResponseEntity.ok(listaPedidos);
    }
}
