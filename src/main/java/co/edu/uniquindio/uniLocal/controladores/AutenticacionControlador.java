package co.edu.uniquindio.uniLocal.controladores;

import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.MensajeDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;
import co.edu.uniquindio.uniLocal.servicios.interfaces.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/validar-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> validarCliente(
            @Valid @RequestBody LoginDTO loginDTO)throws Exception{

        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));

    }


}