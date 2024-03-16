package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepo extends MongoRepository<Cliente, String> {


    @Query("{'email': ?0}")
    Cliente buscarPorEmail(String email);

    Optional<Cliente> findByEmail(String email);

    @Query("{'email' : ?0, 'password' : ?1}")
    Cliente findByEmailAndPassword(String email, String password);

    @Query("{'nickname': ?0}")
    Cliente buscarPorNickname(String nickname);

    @Query("{'estadoRegistro': ?0}")
    List<Cliente> findByEstado(EstadoRegistro estadoRegistro);


}
