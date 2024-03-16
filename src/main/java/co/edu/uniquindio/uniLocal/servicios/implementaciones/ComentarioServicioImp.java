package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ResponderComDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Comentario;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.repositorios.ComentarioRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ComentarioServicio;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ComentarioServicioImp implements ComentarioServicio {

    private ComentarioRepo comentarioRepo;
    private LocalDate localDate;

    @Override
    public void crearComentario(ComentarioDTO comentarioDTO) throws Exception {

        Optional<Cliente> clienteOptional = comentarioRepo.findByCodigoCliente(comentarioDTO.codigoCliente());

        if (clienteOptional.isEmpty()){
            throw new Exception("El cliente no esta registrado");
        }
        Optional<Negocio> negocioOptional = comentarioRepo.findByCodigoNegocio(comentarioDTO.codigoNegocio());

        if (negocioOptional.isEmpty()){
            throw new Exception("El cliente no esta registrado");
        }

        Comentario comentario = new Comentario();
        comentario.setFecha(LocalDateTime.now().toLocalDate());
        comentario.setCodigoCliente(comentarioDTO.codigoCliente());
        comentario.setCodigoComentario(comentarioDTO.codigoComentario());
        comentario.setCodigoNegocio(comentarioDTO.codigoNegocio());
        comentario.setMensaje(comentarioDTO.mensaje());

        Comentario comentarioGuardado = comentarioRepo.save(comentario);

    }

    @Override
    public void responderComentario(ResponderComDTO responderComDTO) throws Exception {
        Optional<Cliente> clienteOptional = comentarioRepo.findByCodigoCliente(responderComDTO.codigoCliente());

        if (clienteOptional.isEmpty()){
            throw new Exception("El cliente no esta registrado");
        }
        Optional<Negocio> negocioOptional = comentarioRepo.findByCodigoNegocio(responderComDTO.codigoNegocio());

        if (negocioOptional.isEmpty()){
            throw new Exception("El cliente no esta registrado");
        }


        Comentario comentario = new Comentario();
        comentario.setFecha(LocalDateTime.now().toLocalDate());
        comentario.setCodigoCliente(responderComDTO.codigoCliente());
        comentario.setCodigoComentario(responderComDTO.codigoComentario());
        comentario.setCodigoNegocio(responderComDTO.codigoNegocio());
        comentario.setRespuesta(responderComDTO.respuesta());

        Comentario respuestaGuardada  = comentarioRepo.save(comentario);


    }

    public boolean existeComentario(String codigoComentario){
        Comentario comentario = comentarioRepo.findByCodigoComentario(codigoComentario);
        return comentario!=null;
    }

    @Override
    public void listarComentariosNegocio(String codigoNegocio) {





    }

    @Override
    public void calcularPromedioCalificaciones() {

    }
}
