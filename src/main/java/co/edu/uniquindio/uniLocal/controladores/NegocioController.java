package co.edu.uniquindio.uniLocal.controladores;


import co.edu.uniquindio.uniLocal.dto.ItemNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.MensajeDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.CrearNegocioDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.modelo.enums.TipoNegocio;
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
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@RequestBody CrearNegocioDTO negocioDTO) throws Exception {
        String codigo = negocioServicio.crearNegocio(negocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, codigo));
    }

    @PutMapping("/actualizarNegocio")
    public ResponseEntity<MensajeDTO<String>> actualizarNegocio(@RequestBody ActualizarNegocioDTO negocioDTO) throws Exception {
        negocioServicio.actualizarNegocio(negocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "El negocio se actualizó correctamente"));
    }

    @GetMapping("/buscarNegocio/{codigo}")
    public ResponseEntity<MensajeDTO<NegocioGetDTO>> buscarNegocio(@PathVariable String codigo) throws Exception {
        NegocioGetDTO negocio = negocioServicio.buscarNegocioId(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocio));
    }

    @DeleteMapping("/eliminarNegocio/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@PathVariable String codigo) throws Exception {
        String negocio = negocioServicio.eliminarNegocio(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocio));
    }

    @PostMapping("/listarNegocios")
    public ResponseEntity<MensajeDTO<List<NegocioGetDTO>>> obtenerNegociosSinId(@RequestBody ItemNegocioDTO itemNegocioDTO) throws Exception {
        List<NegocioGetDTO> listaNegocios = negocioServicio.buscarNegocios(itemNegocioDTO,"");
        return ResponseEntity.ok().body(new MensajeDTO<>(false, listaNegocios));
    }
    @PostMapping("/listarNegocios/{idCliente}")
    public ResponseEntity<MensajeDTO<List<NegocioGetDTO>>>obtenerNegocios(@RequestBody ItemNegocioDTO itemNegocioDTO, @PathVariable String idCliente) throws Exception {
        List<NegocioGetDTO> listaNegocios = negocioServicio.buscarNegocios(itemNegocioDTO,idCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, listaNegocios));
    }

    @GetMapping("/listarNegociosPorCliente/{codigoCliente}")
    public ResponseEntity<MensajeDTO<List<NegocioGetDTO>>> listarNegociosPorCliente(@PathVariable String codigoCliente) throws Exception {
        List<NegocioGetDTO> negocio = negocioServicio.listarNegociosPorCliente(codigoCliente);

        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocio));
    }

    @GetMapping("/listar-categorias")
    public ResponseEntity<MensajeDTO<List<TipoNegocio>>> registrarCliente()throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarCategorias()));
    }


}
