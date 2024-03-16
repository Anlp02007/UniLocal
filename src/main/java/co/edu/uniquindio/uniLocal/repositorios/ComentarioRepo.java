package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Comentario;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepo extends MongoRepository<Comentario, String> {


    Optional<Cliente> findByCodigoCliente(String codigoCliente);

    @Query("{'codigoComentario': ?0}")
    Comentario findByCodigoComentario(String codigoComentario);

    @Query("{'codigoCliente': ?0}")
    List<Comentario> findListComentarioByCodigoCliente(String codigoCliente);

    @Query("{'codigoNegocio': ?0}")
    List<Comentario> findListComentarioByCodigoNegocio(String codigoNegocio);

    Optional<Negocio> findByCodigoNegocio(String codigoNegocio);
}

