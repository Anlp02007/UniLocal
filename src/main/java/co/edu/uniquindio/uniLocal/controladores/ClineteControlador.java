package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ActualizarClienteDTO;
import co.edu.uniquindio.uniLocal.dto.DetalleClienteDTO;
import co.edu.uniquindio.uniLocal.dto.ItemClienteDTO;
import co.edu.uniquindio.uniLocal.dto.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")

public class ClineteControlador {
    private final ClienteServicio clienteServicio;
    @PostMapping("/restrar-cliente")
    public String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception{
        return null;
    }
    @PutMapping("/actualizar-cliente")
    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception{ }
    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarCuenta(String codigo)throws Exception{ }
    @GetMapping("/obtener/{codigo}")
    public DetalleClienteDTO obtenerCliente(String codigo) throws Exception{
        return null;

    }
    @GetMapping("/listar-todos")
    public List<ItemClienteDTO> listarClientes(){
        return null;

    }
}
