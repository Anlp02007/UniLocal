package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Moderador;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModeradorRepo extends MongoRepository<Moderador, String> {

    @Query("{'email': ?0}")
    Moderador buscarPorEmail(String email);

    Optional<Moderador> findByEmail(String email);

    @Query("{'email' : ?0, 'password' : ?1}")
    Moderador findByEmailAndPassword(String email, String password);


    @Query("{'estadoRegistro': ?0}")
    List<Moderador> findByEstado(EstadoRegistro estadoRegistro);

}
