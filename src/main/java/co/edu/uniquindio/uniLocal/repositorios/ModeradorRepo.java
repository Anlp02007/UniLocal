package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.modelo.documento.Moderador;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModeradorRepo extends MongoRepository<Moderador, String> {

}
