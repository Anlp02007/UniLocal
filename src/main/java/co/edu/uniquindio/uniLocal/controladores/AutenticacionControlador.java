package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.ClienteDTO.RegistroClienteDTO;
import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.MensajeDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> registrarCliente(@RequestBody RegistroClienteDTO registroClienteDTO)throws Exception{
        String cliente = autenticacionServicio.registrarse(registroClienteDTO);
        return ResponseEntity.ok(cliente);
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