package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.MensajeDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;
import co.edu.uniquindio.uniLocal.modelo.enums.Ciudad;
import co.edu.uniquindio.uniLocal.servicios.interfaces.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AutenticacionControlador {
    private final AutenticacionServicio autenticacionServicio;
    @PostMapping("/login-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(
            @Valid @RequestBody LoginDTO loginDTO)throws Exception{

        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));

    }

    @PostMapping("/login-moderador")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionModerador(
            @Valid @RequestBody LoginDTO loginDTO)throws Exception{

        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionModerador(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));

    }

    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarCliente(@RequestBody RegistroClienteDTO registroClienteDTO)throws Exception{
        String cliente = autenticacionServicio.registrarse(registroClienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "El cliente se ha registrado correctamente"));
    }

    @GetMapping("/listar-ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudad>>> registrarCliente()throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, autenticacionServicio.listarCiudades()));
    }

    @PostMapping("/validar-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> validarCliente(
            @Valid @RequestBody LoginDTO loginDTO)throws Exception{

        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @GetMapping("/enviarLinkRecuperacion/{email}")
    public ResponseEntity<String> enviarLink(@PathVariable String email) throws Exception{

        autenticacionServicio.enviarLinkRecuperacion(email);
        return ResponseEntity.ok().body("Se envio el correo correctamente");
    }

}