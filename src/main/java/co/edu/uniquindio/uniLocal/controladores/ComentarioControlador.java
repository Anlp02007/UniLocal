package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTO;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ComentarioDTOGet;
import co.edu.uniquindio.uniLocal.dto.ComentarioDTO.ResponderComDTO;
import co.edu.uniquindio.uniLocal.dto.MensajeDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comentarios")
public class ComentarioControlador {


    private final ComentarioServicio comentarioServicio;
    @PostMapping("/crearComentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@RequestBody ComentarioDTO comentarioDTO)throws Exception{
        comentarioServicio.crearComentario(comentarioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Comentario creado con exito"));
    }
    @GetMapping("/listarPorNegocio/{codigo}")
    public ResponseEntity<MensajeDTO<List<ComentarioDTOGet>>> listarComentarios(@PathVariable String codigo)throws Exception{
        List<ComentarioDTOGet> list = comentarioServicio.listarComentariosNegocio(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,list));


    }

    @PostMapping("/responderComentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@RequestBody ResponderComDTO respuestaDTO)throws Exception{
        comentarioServicio.responderComentario(respuestaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Se ha respondido el comentario"));

    }

    @GetMapping("/calcularPromedio/{codigo}")
    public ResponseEntity<MensajeDTO<String>> calificarComentario(@PathVariable String codigo)throws Exception{
        float valor = comentarioServicio.calcularPromedioCalificaciones(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"El valor del promedio es: " + valor));

    }



}
