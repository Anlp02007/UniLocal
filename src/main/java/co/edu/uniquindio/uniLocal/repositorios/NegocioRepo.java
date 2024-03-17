package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NegocioRepo extends MongoRepository<Negocio,String> {

    @Query("{'codigoNegocio': ?0}")
    Negocio findByCodigoNegocio(String codigoNegocio);
    @Query("{'tipoNegocio': ?0}")
    List<Negocio>listarPorTipoNegocio(String tipoNegocio);
    @Query("{'codigoPropietario': ?0}")
    List<Negocio>listarPorPropietarioNegocio(String codigoPropietario);




}
