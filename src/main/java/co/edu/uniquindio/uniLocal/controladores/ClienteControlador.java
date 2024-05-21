package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ClienteDTO.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.FavoritosClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ClienteDTO.ItemClienteDTO;
import co.edu.uniquindio.uniLocal.dto.DetalleClienteDTO;
import co.edu.uniquindio.uniLocal.dto.MensajeDTO;
import co.edu.uniquindio.uniLocal.dto.NegocioDTO.NegocioGetDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteControlador {
    private final ClienteServicio clienteServicio;
    private final AutenticacionServicio autenticacionServicio;


    @PutMapping("/actualizar-cliente")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@RequestBody ActualizarClienteDTO actualizarClienteDTO, @RequestHeader Map<String,String>headers)throws Exception{
       // String token = headers.get("authorization");
       // System.out.println("El token es: "+token);
       // boolean validado = autenticacionServicio.ValidateUser(token, actualizarClienteDTO.id());
        //System.out.println("El usuario se valido?: "+ validado);
        //headers.forEach((key, value) -> System.out.println("Clave: " + key + ", Valor: " + value));
      //  if(validado){

        //}

        clienteServicio.editarPerfil(actualizarClienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se actualizo correctamente"));
      //  return ResponseEntity.status(401).build();
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo)throws Exception{
        clienteServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se elimino correctamente"));

    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String codigo) throws Exception{
        DetalleClienteDTO detalleClienteDTO = clienteServicio.getCliente(codigo);

        return ResponseEntity.ok().body(new MensajeDTO<>(false, detalleClienteDTO));

    }
    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<ItemClienteDTO>>> listarClientes()throws Exception{

        System.out.println("Eyyyyy");
        List<ItemClienteDTO> list = clienteServicio.findAllClients();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, list));

    }

    //falta
    @GetMapping("/agregarRecomendacion/{codigoCliente}/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<String>> guardarNegocioFavoritos(@PathVariable String codigoCliente, @PathVariable  String codigoNegocio )throws Exception{

        clienteServicio.agregarNegocioToRecomendaciones(codigoCliente, codigoNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se agrego el producto a recomendaciones"));
    }
    @GetMapping("/listarRecomendaciones/{codigoCliente}")
    public ResponseEntity<MensajeDTO<List<NegocioGetDTO>>> listarRecomendaciones(@PathVariable String codigoCliente) throws Exception{
        List <NegocioGetDTO> negocios = clienteServicio.listarRecomendaciones(codigoCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocios));
    }

    //Falta
    @DeleteMapping("/eliminarRecomendacion/{codigoCliente}/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarRecomendacion(@PathVariable String codigoCliente, @PathVariable String codigoNegocio) throws Exception{
        String negocio = clienteServicio.eliminarNegocioRecomendaciones(codigoCliente, codigoNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocio));
    }

    @PostMapping("/agregarFavoritos")
    public ResponseEntity<MensajeDTO<String>> guardarNegocioFavoritos(@RequestBody FavoritosClienteDTO favoritoDTO)throws Exception{
        clienteServicio.agregarNegocioToFavoritos(favoritoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se agrego el producto a favoritos"));

    }
    @GetMapping("/listarFavoritos/{codigoCliente}")
    public ResponseEntity<MensajeDTO<List<NegocioGetDTO>>> listarFavoritos(@PathVariable String codigoCliente) throws Exception{
        List <NegocioGetDTO> negocios = clienteServicio.listarFavoritos(codigoCliente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,negocios));


    }
    @DeleteMapping("/eliminarFavorito")
    public ResponseEntity<MensajeDTO<String>> eliminarFavoritos(@RequestBody FavoritosClienteDTO favoritosDTO) throws Exception{
        String negocio = clienteServicio.eliminarNegocioFavoritos(favoritosDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,negocio));
    }

}
