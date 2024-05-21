package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.dto.ItemModeradorDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.MensajeDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ModeradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/moderador")
public class ModeradorControlador {


    private final ModeradorServicio moderadorServicio;




    @PostMapping("/hacerRevision")
    public ResponseEntity<MensajeDTO<String>> hacerRevision(@RequestBody HistorialRevisionDTO historialRevisionDTO) throws Exception{
        moderadorServicio.registrarRevision(historialRevisionDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se hizo la revisión correctamente" ));

    }
    @GetMapping("/eliminarCuenta/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo) throws Exception{
        moderadorServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se eliminó la cuenta correctamente" ));
    }

    @PutMapping("/editarPerfil")
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@RequestBody ActualizarModeradorDTO actualizarModeradorDTO) throws Exception{
        moderadorServicio.editarPerfil(actualizarModeradorDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se actualizo correctamente" ));
    }

    @GetMapping("/listarModeradores")
    public ResponseEntity<MensajeDTO<List<ItemModeradorDTO>>> listarModeradores() throws Exception{
        List<ItemModeradorDTO> moreadores =  moderadorServicio.listarModeradoresActivos();
        return ResponseEntity.ok().body(new MensajeDTO<>(false,moreadores ));
    }

    @GetMapping("/listarNegocios")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegocios() throws Exception{
        List<ItemNegocioDTO> moreadores = moderadorServicio.ListarNegocios();
        return ResponseEntity.ok().body(new MensajeDTO<>(false,moreadores ));
    }



}
