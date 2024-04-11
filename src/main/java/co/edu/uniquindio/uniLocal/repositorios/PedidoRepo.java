package co.edu.uniquindio.uniLocal.repositorios;


import co.edu.uniquindio.uniLocal.modelo.entidades.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PedidoRepo extends MongoRepository<Pedido,String> {

    @Query("{'codigoNegocio': ?0}")
    List<Pedido> listarPorNegocio(String codigoNegocio);
    @Query("{'codigoCliente': ?0}")
    List<Pedido> listarPorClientes(String codigoCliente);
}
