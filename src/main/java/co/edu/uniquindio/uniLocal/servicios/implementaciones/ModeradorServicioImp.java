package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.*;
import co.edu.uniquindio.uniLocal.modelo.documento.Moderador;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.modelo.entidades.HistoriaRevicion;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoNegocio;
import co.edu.uniquindio.uniLocal.modelo.enums.EstadoRegistro;
import co.edu.uniquindio.uniLocal.repositorios.ModeradorRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
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

    public void editarPerfil(ActualizarModeradorDTO actualizarModeradorDTODTO) throws Exception {

        Optional<Moderador> moderadorOptional = moderadorRepo.findById(actualizarModeradorDTODTO.id());

        if (moderadorOptional.isEmpty()){
            throw new Exception("Ecliente no esta registrado");

        }
        Moderador moderador = moderadorOptional.get();
        moderador.setNombre(actualizarModeradorDTODTO.nombre());
        moderador.setEmail(actualizarModeradorDTODTO.email());


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

}

