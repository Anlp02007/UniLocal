package co.edu.uniquindio.uniLocal.servicios.implementaciones;


import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTO;
import co.edu.uniquindio.uniLocal.dto.PedidoDTO.PedidoDTOGet;
import co.edu.uniquindio.uniLocal.modelo.entidades.Pedido;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.repositorios.PedidoRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.PedidoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PedidoServicioImp implements PedidoServicio {

    private final PedidoRepo pedidoRepo;
    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;


    @Override
    public void hacerPedido(PedidoDTO pedidoDTO) throws Exception{

        if(negocioRepo.findByCodigoNegocio(pedidoDTO.codigoNegocio()) == null )
            throw new Exception("El negocio no existe");
        if(clienteRepo.findyById(pedidoDTO.codigoCliente()) == null)
            throw new Exception("El cliente no existe");

        Pedido pedido = converitirPedidoDtoToPedido(pedidoDTO);
        pedidoRepo.save(pedido);
    }

    @Override
    public  List<PedidoDTOGet> obtenerPedidoPorCliente(String idCLiente) throws Exception{

        List<Pedido> listaPedidos = pedidoRepo.listarPorClientes(idCLiente);
        System.out.println(listaPedidos);

        if(listaPedidos.isEmpty())
            throw new Exception("No hay pedidos por este cliente");

        return (List<PedidoDTOGet>) listaPedidos.stream().map(pedido ->
                new PedidoDTOGet(
                        pedido.getFechaPedido(),
                        pedido.getDescripcion(),
                        clienteRepo.findyById(pedido.getCodigoCliente()).getNombre(),
                        negocioRepo.findByCodigoNegocio(pedido.getCodigoNegocio()).getNombre()

        )).toList();
    }

    @Override
    public  List<PedidoDTOGet> obtenerPedidoPorNegocio(String idPedido) throws Exception{

        List<Pedido> listaPedidos = pedidoRepo.listarPorNegocio(idPedido);

        if(listaPedidos.isEmpty())
            throw new Exception("No hay pedidos por este negocio");

        return (List<PedidoDTOGet>) listaPedidos.stream().map(pedido ->
                new PedidoDTOGet(
                        pedido.getFechaPedido(),
                        pedido.getDescripcion(),
                        clienteRepo.findyById(pedido.getCodigoCliente()).getNombre(),
                        negocioRepo.findByCodigoNegocio(pedido.getCodigoNegocio()).getNombre()

                )).toList();
    }

   private Pedido converitirPedidoDtoToPedido(PedidoDTO pedidoDTO){

        Pedido pedido = new Pedido();
        pedido.setCodigoCliente(pedidoDTO.codigoCliente());
        pedido.setCodigoNegocio(pedidoDTO.codigoNegocio());
        pedido.setFechaPedido(pedidoDTO.fecha());
        pedido.setDescripcion(pedidoDTO.descripcion());

        return pedido;

    }


}
