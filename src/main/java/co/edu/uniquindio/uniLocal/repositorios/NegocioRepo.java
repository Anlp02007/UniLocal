package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NegocioRepo extends MongoRepository<Negocio,String> {


}
