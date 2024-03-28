package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class NegocioServivioImp implements NegocioServicio {

    private final NegocioRepo negocioRepo;
    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {
        
        if(existeNegocio(crearNegocioDTO.codigoNegocio())){
            throw new Exception("El email ya esta en uso por otra persona");
        }

        if(existeCodigoCliente(crearNegocioDTO.codigoPropietario())){
            throw new Exception("El nickname ya ests en uso por otra persona ");
        }

       
            Negocio negocio = new Negocio();
            negocio.setCodigoNegocio(crearNegocioDTO.codigoNegocio());
            negocio.setCodigoCliente(crearNegocioDTO.codigoPropietario());
            negocio.setNombre(crearNegocioDTO.nombre());
            negocio.setDescripcion(crearNegocioDTO.descripcion());
            negocio.setHorario(crearNegocioDTO.horario());
            negocio.setTipoNegocio(crearNegocioDTO.tipoNegocios());
            negocio.setImagen(crearNegocioDTO.imagen());
            negocio.setUbicacion(crearNegocioDTO.ubicacion());
            negocio.setTelefono(crearNegocioDTO.telefono());
            negocio.setEstadoRegistros(EstadoRegistro.ACTIVO);

            Negocio negocioGuardado = negocioRepo.save(negocio);

            return negocioGuardado.getCodigoNegocio();
        }

    private boolean existeCodigoCliente(String s) {
        Negocio negocio = negocioRepo.findByCodigoCliente(s);
        return negocio!=null;
    }
     

 


    private Boolean existeNegocio(String codigo){
        Negocio negocio = negocioRepo.findByCodigoNegocio(codigo);
        return negocio!=null;
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        if (!existeNegocio(actualizarNegocioDTO.id())) {
            throw new Exception("El negocio no está registrado");
        }

        Negocio negocio = buscarNegocioId(actualizarNegocioDTO.id());
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setTelefono(actualizarNegocioDTO.telefono());
        negocio.setUbicacion(actualizarNegocioDTO.ubicacion());
        negocio.setImagen(actualizarNegocioDTO.imagen());
        negocio.setHorario(actualizarNegocioDTO.horario());

        negocioRepo.save(negocio);


    }

    @Override
    public Negocio buscarNegocioId(String idNegocio) throws Exception {
        if(existeNegocio(idNegocio)){
            return negocioRepo.findByCodigoNegocio(idNegocio);
        }

        throw new Exception("el negocio no existe");
    }


    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {

        Negocio negocioOptional = negocioRepo.findById(idNegocio);

        if (negocioOptional==null) {
            throw new Exception("No se encontró el negocio a eliminar");
        }

        Negocio negocio = negocioOptional.get();

        negocio.setEstadoRegistros(EstadoRegistro.INACTIVO);

        negocioRepo.save(negocio);
    }



    @Override
    public List<Negocio> buscarNegocios(ItemNegocioDTO itemNegocioDTO) throws Exception {

        List<Negocio> negocios = new ArrayList<>();

        String nombre = itemNegocioDTO.nombre();;
        TipoNegocio tipoNegocio = itemNegocioDTO.tipoNegocio();
        Ubicacion ubicacion= itemNegocioDTO.ubicacion();
        if (nombre!= null && tipoNegocio != null && ubicacion != null){
            negocios = negocioRepo.listarPorTresFiltros(tipoNegocio,nombre,ubicacion);
        }
        else if (nombre != null && tipoNegocio != null){
            negocios = negocioRepo.listarPorNombreTipoNegocio(nombre,tipoNegocio);
        }
        else if (nombre != null && ubicacion != null){
            negocios = negocioRepo.listarPorNombreUbicacion(nombre,ubicacion);

        }
        else if (tipoNegocio != null && ubicacion != null){
            negocios = negocioRepo.listarPorTipoNegocioUbicacion(tipoNegocio, ubicacion);
        }

        else if (nombre != null){
            negocios = negocioRepo.listarPorNombre(nombre);

        }
        else if (tipoNegocio != null){
            negocios = negocioRepo.listarPorTipoNegocio(tipoNegocio);

        }
        else if (ubicacion != null){
            negocios = negocioRepo.listarPorUbicacion(ubicacion);
        }
        else {
            negocios = negocioRepo.findAll();
        }

        return negocios;
    }



    @Override
    public List<Negocio> filtrarPorEstado(EstadoRegistro estado) throws Exception{

        List<Negocio> negocios = negocioRepo.listarPorEstado(estado);

        return negocios;

    }

    @Override
    public List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception {
        List<Negocio> negocios = negocioRepo.listarPorPropietarioNegocio(codigoPropietario);
        return negocios;

    }

    @Override
    public void cambiarEstado() {


    }

    @Override
    public void registrarRevision() {

    }


}
