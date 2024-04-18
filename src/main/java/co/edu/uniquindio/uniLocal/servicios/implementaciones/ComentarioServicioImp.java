package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTOGet;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ResponderComDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Comentario;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.repositorios.ComentarioRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ComentarioServicioImp implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;

    private final ClienteRepo clienteRepo;

    private final NegocioRepo negocioRepo;

    private final EmailServicio emailServicio;

    private LocalDate localDate;

    @Override
    public void crearComentario(ComentarioDTO comentarioDTO) throws Exception {

        Optional<Cliente> clienteOptional = clienteRepo.findById(comentarioDTO.codigoCliente());

        if (clienteOptional.isEmpty()){
            throw new Exception("El cliente no esta registrado");
        }
        Negocio negocioOptional = negocioRepo.findByCodigoNegocio(comentarioDTO.codigoNegocio());

        if (negocioOptional==null){
            throw new Exception("El cliente no esta registrado");
        }

        Comentario comentario = new Comentario();
        comentario.setFecha(LocalDateTime.now().toLocalDate());
        comentario.setCodigoCliente(comentarioDTO.codigoCliente());
        //comentario.setCodigoComentario(comentarioDTO.codigoComentario());
        comentario.setCodigoNegocio(comentarioDTO.codigoNegocio());
        comentario.setMensaje(comentarioDTO.mensaje());
        comentario.setCalificacion(comentarioDTO.calificaion());

        String email = clienteRepo.findyById(negocioOptional.getCodigoCliente()).getEmail();

       /* emailServicio.enviarCorreo(new EmailDTO(
                "Comentario",
                comentario.getMensaje(),
                email
        ));*/

        Comentario comentarioGuardado = comentarioRepo.save(comentario);

    }

    @Override
    public void responderComentario(ResponderComDTO responderComDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(responderComDTO.codigoCliente());

        if (clienteOptional.isEmpty()){
            throw new Exception("El cliente no esta registrado");
        }
        Negocio negocioOptional = negocioRepo.findByCodigoNegocio(responderComDTO.codigoNegocio());

        if (negocioOptional==null){
            throw new Exception("El cliente no esta registrado");
        }

        Comentario comentario = comentarioRepo.findByCodigoComentario(responderComDTO.codigoComentario());
        if (comentario==null){
            throw new Exception("El comentario no existe");
        }

        String email = clienteRepo.findyById(comentario.getCodigoCliente()).getEmail();

        if(comentario.getRespuesta() == null)
            comentario.setRespuesta(new ArrayList<>());

        comentario.setFecha(LocalDateTime.now().toLocalDate());
        comentario.setCodigoCliente(responderComDTO.codigoCliente());
        comentario.setCodigoComentario(responderComDTO.codigoComentario());
        comentario.setCodigoNegocio(responderComDTO.codigoNegocio());
        comentario.getRespuesta().add("Timestamp:"+LocalDateTime.now().toLocalDate()+";Cliente con id: "+responderComDTO.codigoCliente() +
                " Responde:\n"+ responderComDTO.respuesta());



   /*     emailServicio.enviarCorreo(new EmailDTO(
                "Comentario",
                ";Cliente con id: "+responderComDTO.codigoCliente() +
                        " Responde:\n"+ responderComDTO.respuesta(),
                email
        ));*/

        Comentario respuestaGuardada  = comentarioRepo.save(comentario);


    }
    public boolean existeComentario(String codigoComentario){
        Comentario comentario = comentarioRepo.findByCodigoComentario(codigoComentario);
        return comentario!=null;
    }

    public List<ComentarioDTOGet> listarComentariosNegocio(String codigoNegocio) {

    List<Comentario> listaComentarios = comentarioRepo.findAllByCodigoNegocio(codigoNegocio);
        /*List<Cliente> obtenerCliente = clienteRepo.findAllByCodigoCliente(obtenerComenario.get().getCodigoCliente());
         obtenerCliente.stream().map(cliente -> new ComentarioDTO(
                 cliente.getNombre()
                 cliente.getFotoPerfil()
         )).toList();*/

       if (!listaComentarios.isEmpty()){

          return listaComentarios.stream().map(comentario ->


               new ComentarioDTOGet(
                       clienteRepo.findyById(comentario.getCodigoCliente()).getNombre(),
                       clienteRepo.findyById(comentario.getCodigoCliente()).getFotoPerfil(),
                       comentario.getFecha(),
                       comentario.getMensaje(),
                       comentario.getCalificacion()

               ) ).toList();

       }

       return  new ArrayList<>();

    }

    @Override
    public float calcularPromedioCalificaciones(String codigoNegocio) {

        float comentario = comentarioRepo.calcularPromedio(codigoNegocio);


        return comentario;
    }
}
