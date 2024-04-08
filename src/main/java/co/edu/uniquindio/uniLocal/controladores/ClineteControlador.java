package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.DetalleClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ItemClienteDTO;
import co.edu.uniquindio.uniLocal.dto.RegistroClienteDTO;
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
public class ClineteControlador {
    private final ClienteServicio clienteServicio;
    private final AutenticacionServicio autenticacionServicio;

    @PostMapping("/registrar-cliente")
    public ResponseEntity<String> registrarCliente(@RequestBody RegistroClienteDTO registroClienteDTO)throws Exception{
        String cliente = clienteServicio.registrarse(registroClienteDTO);
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/actualizar-cliente")
    public ResponseEntity<Void> actualizarCliente(@RequestBody ActualizarClienteDTO actualizarClienteDTO, @RequestHeader Map<String,String>headers)throws Exception{
        String token = headers.get("authorization");
        System.out.println("El token es: "+token);
        boolean validado = autenticacionServicio.ValidateUser(token, actualizarClienteDTO.id());
        System.out.println("El usuario se valido?: "+ validado);
        //headers.forEach((key, value) -> System.out.println("Clave: " + key + ", Valor: " + value));
        if(validado){
            clienteServicio.editarPerfil(actualizarClienteDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String codigo)throws Exception{
        clienteServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<DetalleClienteDTO> obtenerCliente(@PathVariable String codigo) throws Exception{
        DetalleClienteDTO detalleClienteDTO = clienteServicio.getCliente(codigo);
        return ResponseEntity.ok().body(detalleClienteDTO);

    }
    @GetMapping("/listar-todos")
    public ResponseEntity<List<ItemClienteDTO>> listarClientes()throws Exception{
        List<ItemClienteDTO> list = clienteServicio.findAllClients();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/listar-todo")
    public ResponseEntity<String> listarCliente(){
        return ResponseEntity.ok("holis");

    }

}
