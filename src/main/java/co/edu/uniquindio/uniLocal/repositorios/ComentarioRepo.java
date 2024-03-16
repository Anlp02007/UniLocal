package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Comentario;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ComentarioRepo extends MongoRepository<Comentario, String> {


    Optional<Cliente> findByCodigoCliente(String codigoCliente);


    Optional<Negocio> findByCodigoNegocio(String codigoNegocio);
}

