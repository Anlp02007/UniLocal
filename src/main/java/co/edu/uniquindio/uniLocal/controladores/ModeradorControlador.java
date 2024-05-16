package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ActualizarModeradorDTO;
import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.dto.ItemModeradorDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
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
    public ResponseEntity<String> hacerRevision(@RequestBody HistorialRevisionDTO historialRevisionDTO) throws Exception{
        moderadorServicio.registrarRevision(historialRevisionDTO);
        return ResponseEntity.ok().body("Se hizo la revisión correctamente");
    }
    @GetMapping("/eliminarCuenta/{codigo}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable String codigo) throws Exception{
        moderadorServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body("Se eliminó la cuenta correctamente");
    }

    @PutMapping("/editarPerfil")
    public ResponseEntity<String> editarPerfil(@RequestBody ActualizarModeradorDTO actualizarModeradorDTO) throws Exception{
        moderadorServicio.editarPerfil(actualizarModeradorDTO);
        return ResponseEntity.ok().body("Se actualizo correctamente");
    }

    @GetMapping("/listarModeradores")
    public ResponseEntity<List<ItemModeradorDTO>> listarModeradores() throws Exception{
        List<ItemModeradorDTO> moreadores =  moderadorServicio.listarModeradoresActivos();
        return ResponseEntity.ok().body(moreadores);
    }

    @GetMapping("/listarNegocios")
    public ResponseEntity<List<ItemNegocioDTO>> listarNegocios() throws Exception{
        List<ItemNegocioDTO> moreadores = moderadorServicio.ListarNegocios();
        return ResponseEntity.ok().body(moreadores);
    }



}
