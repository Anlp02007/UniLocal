package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.EmailDTO;
import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.entidades.Ubicacion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class NegocioServivioImp implements NegocioServicio {

    private final NegocioRepo negocioRepo;
    private final EmailServicio emailServicio;
    private final ClienteServicio clienteServicio;

    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {
        
        if(existeNegocio(crearNegocioDTO.codigoNegocio())){
            throw new Exception("El email ya esta en uso por otra persona");
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
        negocio.setEstadoNegocio(EstadoNegocio.PENDIENTE);

        Negocio negocioGuardado = negocioRepo.save(negocio);

        return negocioGuardado.getCodigoNegocio();
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

        Negocio negocio = negocioRepo.findByCodigoNegocio(actualizarNegocioDTO.id());
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setTelefono(actualizarNegocioDTO.telefono());
        negocio.setUbicacion(actualizarNegocioDTO.ubicacion());
        negocio.setImagen(actualizarNegocioDTO.imagen());
        negocio.setHorario(actualizarNegocioDTO.horario());

        negocioRepo.save(negocio);


    }

    @Override
    public NegocioGetDTO buscarNegocioId(String idNegocio) throws Exception {

        Negocio negocio = null;

        if(!existeNegocio(idNegocio)){

            throw new  Exception("El negocio no está registrado");
        }

        negocio =  negocioRepo.findByCodigoNegocio(idNegocio);

        NegocioGetDTO negocioDTO = convertirNegocioToNegocioDTO(negocio);

        return negocioDTO;
    }


    @Override
    public String eliminarNegocio(String idNegocio) throws Exception {

        Negocio negocio = negocioRepo.findByCodigoNegocio(idNegocio);
        System.out.println(negocio);
        negocio.setEstadoRegistros(EstadoRegistro.INACTIVO);
        negocioRepo.save(negocio);
        return negocio.getCodigoNegocio();
    }



    @Override
    public List<NegocioGetDTO> buscarNegocios(ItemNegocioDTO itemNegocioDTO,String idCliente) throws Exception {

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

        if (negocios.isEmpty()) {

            throw new Exception("No hay negocios");
        }

        for (Negocio neg : negocios) {
            clienteServicio.agregarNegocioToRecomendaciones(idCliente,neg);
        }

        return negocios.stream().map(this::convertirNegocioToNegocioDTO).toList();


          /*  return negocios.stream().map(negocio ->
                   convertirNegocioToNegocioDTO(negocio)).toList();*/


    }

    @Override
    public List<Negocio> filtrarPorEstado(EstadoNegocio estado) throws Exception{
        List<Negocio> negocios = negocioRepo.listarPorEstado(estado);
        return negocios;
    }

    @Override
    public List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception {
        List<Negocio> negocios = negocioRepo.listarPorPropietarioNegocio(codigoPropietario);
        return negocios;

    }

    @Override
    public void registrarRevision(HistorialRevisionDTO historialDTO) throws Exception{

        Negocio negocio = negocioRepo.findByCodigoNegocio(historialDTO.codigoNegocio());
        if(negocio == null){
            throw new Exception("El negocio no existe");
        }

        HistoriaRevicion historiaRevicion = new HistoriaRevicion (
                historialDTO.fecha(),
                historialDTO.descripcion(),
                historialDTO.estadoNegocio(),
                historialDTO.codigoModerador()
                );

        String correoUsuario = clienteServicio.getCliente(negocio.getCodigoCliente()).email();

        String mensaje = "El lugar fue " + historialDTO.estadoNegocio();
        if(historialDTO.estadoNegocio() == EstadoNegocio.RECHAZADO ){
            mensaje += " por el motivo de " + historialDTO.descripcion();
        }

        emailServicio.enviarCorreo(new EmailDTO(
                "Revision de negocio",
                mensaje,
                correoUsuario
        ));

        negocio.setEstadoNegocio(historialDTO.estadoNegocio());
        negocio.getHistoriaRevicions().add(historiaRevicion);
        negocioRepo.save(negocio);
    }

    private NegocioGetDTO convertirNegocioToNegocioDTO(Negocio negocio){

       return  new NegocioGetDTO(
               negocio.getCodigoNegocio(),
                negocio.getNombre(),
               negocio.getUbicacion(),
               negocio.getHorario(),
               negocio.getImagen(),
                negocio.getDescripcion(),
                negocio.getTipoNegocio(),
                negocio.getTelefono(),
               negocio.getEstadoRegistros()

        );
    }


}
