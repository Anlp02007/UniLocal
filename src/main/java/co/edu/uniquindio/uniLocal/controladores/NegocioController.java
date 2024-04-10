package co.edu.uniquindio.uniLocal.controladores;


import co.edu.uniquindio.uniLocal.dto.HistorialRevisionDTO.HistorialRevisionDTO;
import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/negocio")
public class NegocioController {

    private final NegocioServicio negocioServicio;


    @PostMapping("/crearNegocio")
    public ResponseEntity<String> crearNegocio(@RequestBody CrearNegocioDTO negocioDTO) throws Exception {
        String codigo = negocioServicio.crearNegocio(negocioDTO);
        return ResponseEntity.ok(codigo);
    }

    @PutMapping("/actualizarNegocio")
    public ResponseEntity<String> actualizarNegocio(@RequestBody ActualizarNegocioDTO negocioDTO) throws Exception {
        negocioServicio.actualizarNegocio(negocioDTO);
        return ResponseEntity.ok("El negocio se actualizó correctamente");
    }

    @GetMapping("/buscarNegocio/{codigo}")
    public ResponseEntity<NegocioGetDTO> buscarNegocio(@PathVariable String codigo) throws Exception {
        NegocioGetDTO negocio = negocioServicio.buscarNegocioId(codigo);
        return ResponseEntity.ok().body(negocio);
    }

    @DeleteMapping("/eliminarNegocio/{codigo}")
    public ResponseEntity<String> eliminarNegocio(@PathVariable String codigo) throws Exception {
        String negocio = negocioServicio.eliminarNegocio(codigo);
        return ResponseEntity.ok().body(negocio);
    }

    @PostMapping("/listarNegocios")
    public ResponseEntity<List<NegocioGetDTO>> obtenerNegocios(@RequestBody ItemNegocioDTO itemNegocioDTO) throws Exception {
        List<NegocioGetDTO> listaNegocios = negocioServicio.buscarNegocios(itemNegocioDTO);
        return ResponseEntity.ok().body(listaNegocios);
    }

    @PostMapping("/hacerRevision")
    public  ResponseEntity<String> hacerRevision(@RequestBody HistorialRevisionDTO historialRevisionDTO) throws Exception{
        negocioServicio.registrarRevision(historialRevisionDTO);
        return ResponseEntity.ok().body("Se hizo la revisión correctamente");
    }


}
