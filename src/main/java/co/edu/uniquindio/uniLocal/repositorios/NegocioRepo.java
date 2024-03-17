package co.edu.uniquindio.uniLocal.repositorios;

import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NegocioRepo extends MongoRepository<Negocio,String> {

    @Query("{'codigoNegocio': ?0}")
    Negocio findByCodigoNegocio(String codigoNegocio);
    @Query("{'tipoNegocio': ?0}")
    List<Negocio>listarPorTipoNegocio(TipoNegocio tipoNegocio);
    @Query("{'codigoPropietario': ?0}")
    List<Negocio>listarPorPropietarioNegocio(String codigoPropietario);

    @Query("{'tipoNegocio': ?0, 'nombre': ?1, 'ubicacion': ?2}")
    List<Negocio>listarPorTresFiltros(TipoNegocio tipoNegocio, String nombre, Ubicacion ubicacion);

    @Query("{'nombre':?0,'tipoNegocio': ?1}")
    List<Negocio>listarPorNombreTipoNegocio(String nombre,TipoNegocio tipoNegocio);
    @Query("{'nombre':?0,'ubicacion': ?1}")
    List<Negocio>listarPorNombreUbicacion(String nombre,Ubicacion ubicacion);
    @Query("{'tipoNegocio': ?0, 'ubicacion': ?1}")
    List<Negocio>listarPorTipoNegocioUbicacion(TipoNegocio tipoNegocio, Ubicacion ubicacion);
    @Query("{'nombre': ?0}")
    List<Negocio>listarPorNombre(String nombre);
    @Query("{'ubicacion': ?0}")
    List<Negocio>listarPorUbicacion(Ubicacion ubicacion);
    @Query("{'estadoRegistros': ?0}")
    List<Negocio>listarPorEstado(EstadoRegistro estadoRegistro);



}
