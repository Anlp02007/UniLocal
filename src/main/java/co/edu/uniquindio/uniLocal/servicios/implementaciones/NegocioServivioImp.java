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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class NegocioServivioImp implements NegocioServicio {

    private NegocioRepo negocioRepo;
    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {


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

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepo.findById(actualizarNegocioDTO.id());

        if (negocioOptional.isEmpty()) {
            throw new Exception("El negocio no está registrado");
        }

        Negocio negocio = negocioOptional.get();
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setTelefono(actualizarNegocioDTO.telefono());
        negocio.setUbicacion(actualizarNegocioDTO.ubicacion());
        negocio.setImagen(actualizarNegocioDTO.imagen());
        negocio.setHorario(actualizarNegocioDTO.horario());

        negocioRepo.save(negocio);


    }

    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepo.findById(idNegocio);

        if (negocioOptional.isEmpty()) {
            throw new Exception("No se encontró el negocio a eliminar");
        }

        Negocio negocio = negocioOptional.get();

        negocio.setEstadoRegistros(EstadoRegistro.INACTIVO);

        negocioRepo.save(negocio);
    }



    @Override
    public List<ItemNegocioDTO> buscarNegocios(String nombre, TipoNegocio tipoNegocio, Ubicacion ubicacion) throws Exception {

        List<Negocio> negocios = new ArrayList<>();

        if (nombre != null && tipoNegocio != null && ubicacion != null){
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

        return (List<ItemNegocioDTO>) negocios.stream().map(negocio ->
                new ItemNegocioDTO(
                        negocio.getNombre(),
                        negocio.getUbicacion(),
                        negocio.getHorario(),
                        negocio.getImagen(),
                        negocio.getDescripcion(),
                        negocio.getTelefono(),
                        negocio.getEstadoRegistros())
                ).toList();
    }



    @Override
    public List<ItemNegocioDTO> filtrarPorEstado(String estado) throws Exception{
        List<Negocio> negocios = new  ArrayList<>();
        if (estado.equals(EstadoRegistro.ACTIVO)){

            negocios = negocioRepo.listarPorEstado(EstadoRegistro.ACTIVO);

        }else {
            negocios = negocioRepo.listarPorEstado(EstadoRegistro.INACTIVO);

        }
        return (List<ItemNegocioDTO>) negocios.stream().map(negocio ->
                new ItemNegocioDTO(
                        negocio.getNombre(),
                        negocio.getUbicacion(),
                        negocio.getHorario(),
                        negocio.getImagen(),
                        negocio.getDescripcion(),
                        negocio.getTelefono(),
                        negocio.getEstadoRegistros())
        ).toList();

    }

    @Override
    public List<ItemNegocioDTO> listarNegociosPropietario(String codigoPropietario) throws Exception {
        List<Negocio> negocios = new  ArrayList<>();
        return (List<ItemNegocioDTO>) negocios.stream().map(negocio ->
                new ItemNegocioDTO(
                        negocio.getNombre(),
                        negocio.getUbicacion(),
                        negocio.getHorario(),
                        negocio.getImagen(),
                        negocio.getDescripcion(),
                        negocio.getTelefono(),
                        negocio.getEstadoRegistros())
        ).toList();

    }

    @Override
    public void cambiarEstado() {


    }

    @Override
    public void registrarRevision() {

    }


}
