package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ResponderComDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.modelo.documento.Comentario;
import co.edu.uniquindio.uniLocal.modelo.documento.Negocio;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.repositorios.ComentarioRepo;
import co.edu.uniquindio.uniLocal.repositorios.NegocioRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ComentarioServicioImp implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;

    private final ClienteRepo clienteRepo;

    private final NegocioRepo negocioRepo;
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
        comentario.setCodigoComentario(comentarioDTO.codigoComentario());
        comentario.setCodigoNegocio(comentarioDTO.codigoNegocio());
        comentario.setMensaje(comentarioDTO.mensaje());
        comentario.setCalificacion(comentarioDTO.calificaion());

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

        //comentario.setFecha(LocalDateTime.now().toLocalDate());
        //comentario.setCodigoCliente(responderComDTO.codigoCliente());
        //comentario.setCodigoComentario(responderComDTO.codigoComentario());
        //comentario.setCodigoNegocio(responderComDTO.codigoNegocio());
        //comentario.setRespuesta("Timestamp:"+LocalDateTime.now().toLocalDate()+";Cliente con id: "+responderComDTO.codigoCliente() +
        //" Responde:\n"+ responderComDTO.respuesta());

        Comentario respuestaGuardada  = comentarioRepo.save(comentario);


    }

    public boolean existeComentario(String codigoComentario){
        Comentario comentario = comentarioRepo.findByCodigoComentario(codigoComentario);
        return comentario!=null;
    }

    @Override
    public void listarComentariosNegocio(String codigoNegocio) {

      //  List<Comentario>obtenerComentario = comentarioRepo.obternerComentario(codigoNegocio);





    }

    @Override
    public float calcularPromedioCalificaciones(String codigoNegocio) {

        float comentario= comentarioRepo.calcularPromedio(codigoNegocio);


        return comentario;
    }
}
