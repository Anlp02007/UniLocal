package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.*;
import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Moderador;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.repositorios.ModeradorRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ModeradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ModeradorServicioImp implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;

    private final NegocioRepo negocioRepo;
    private final ClienteServicio clienteServicio;
    private final EmailServicio emailServicio;
    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

        Moderador moderador = moderadorRepo.findByEmailAndPassword(sesionDTO.email(),sesionDTO.password());

        if (moderador == null){

            throw new Exception("cuenta invalida");
        }

    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {

        Optional<Moderador> moderadorOptional = moderadorRepo.findById(idCuenta);

        if (moderadorOptional.isEmpty()) {
            throw new Exception("No se encontró el moderador a eliminar");
        }

        Moderador moderador = moderadorOptional.get();
        moderador.setEstadoRegistro(EstadoRegistro.INACTIVO);

        moderadorRepo.save(moderador);

    }



    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

            Optional<Moderador> moderador = moderadorRepo.findByEmail(email);

            if (moderador == null){

                throw  new Exception("El email dado no esta asociado a ningun cliente");
            }
            enviarMensaje("Cambio de contraseña - Unilocal","Para cambiar su conttaseña acceda al link que esta acontiniacion:Link", email);

        }

    private void enviarMensaje(String s, String s1, String email) {
    }



    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }

    public void editarPerfil(ActualizarModeradorDTO actualizarModeradorDTO) throws Exception {

        Optional<Moderador> moderadorOptional = moderadorRepo.findById(actualizarModeradorDTO.id());

        if (moderadorOptional.isEmpty()){
            throw new Exception("El moderador no esta registrado");

        }
        Moderador moderador = moderadorOptional.get();
        moderador.setNombre(actualizarModeradorDTO.nombre());
        moderador.setEmail(actualizarModeradorDTO.email());


        moderadorRepo.save(moderador);
    }

    public List<ItemModeradorDTO> listarModeradoresActivos(){

        List<Moderador> moderadores = moderadorRepo.findByEstado(EstadoRegistro.ACTIVO);
        return (List<ItemModeradorDTO>) moderadores.stream().map(moderador ->
                new ItemModeradorDTO(
                        moderador.getCodigoModerador(),
                        moderador.getNombre())
        ).toList();
    }

    public List<ItemModeradorDTO> listarModeradoresInactivos(){

        List<Moderador> moderadores = moderadorRepo.findByEstado(EstadoRegistro.INACTIVO);
        return (List<ItemModeradorDTO>) moderadores.stream().map(moderador ->
                new ItemModeradorDTO(
                        moderador.getCodigoModerador(),
                        moderador.getNombre())
        ).toList();
    }


    public HistoriaRevicion revisionNegocio(ItemNegocioDTO itemNegocioDTO, HistoriaRevicion historiaRevicion) throws Exception{
        Negocio negocioOptional = negocioRepo.findByCodigoNegocio(itemNegocioDTO.codigoNegocio());

        if (negocioOptional==null){
            throw new Exception("El cliente no esta registrado");
        }
        if(negocioOptional.getHistorialRevicion()==null){
            negocioOptional.setHistorialRevicion(new ArrayList<>());
            negocioOptional.getHistorialRevicion().add(historiaRevicion);
        }else{
            negocioOptional.getHistorialRevicion().add(historiaRevicion);
        }

        negocioRepo.save(negocioOptional);
        return historiaRevicion;
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

        if(negocio.getHistorialRevicion() == null){
            negocio.setHistorialRevicion(new ArrayList<>());
        }

        negocio.setEstadoNegocio(historialDTO.estadoNegocio());
        negocio.getHistorialRevicion().add(historiaRevicion);
        negocioRepo.save(negocio);
    }



    public void inactivarNegocios() {

        List<Negocio> negocios = negocioRepo.findAll();

        for (Negocio negocio: negocios){

            if(negocio.getEstadoNegocio() == EstadoNegocio.RECHAZADO){

                List<HistoriaRevicion> historiales = negocio.getHistorialRevicion();
                HistoriaRevicion historial = historiales.get(historiales.size() - 1);
                LocalDate fecha = historial.getFecha();
                if(ChronoUnit.DAYS.between(fecha, LocalDate.now()) >= 5) {
                    Negocio negocioAModificar = negocio;
                    negocioAModificar.setEstadoRegistros(EstadoRegistro.INACTIVO);
                    negocioRepo.save(negocioAModificar);
                }
            }


        }

    }

    public List<ItemNegocioDTO> ListarNegocios(){

        List<Negocio> negocios = negocioRepo.findAll();

        return (List<ItemNegocioDTO>) negocios.stream()
                .map(negocio ->
                        new ItemNegocioDTO(
                                negocio.getCodigoNegocio(),
                                negocio.getNombre(),
                                negocio.getUbicacion(),
                                negocio.getHorario(),
                                negocio.getImagen(),
                                negocio.getDescripcion(),
                                negocio.getTipoNegocio(),
                                negocio.getTelefono(),
                                negocio.getEstadoRegistros()
                )).toList();
    }

    @Override
    public ActualizarModeradorDTO getModerador(String codiString) throws Exception {
        Optional<Moderador> cl = moderadorRepo.findById(codiString);
        return ActualizarModeradorDTO.builder()

                .nombre(cl.get().getNombre())

                .email(cl.get().getEmail())
                .build();
    }

}

