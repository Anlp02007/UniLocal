package co.edu.uniquindio.uniLocal.servicios.implementaciones;

import co.edu.uniquindio.uniLocal.dto.LoginDTO;
import co.edu.uniquindio.uniLocal.dto.TokenDTO;
import co.edu.uniquindio.uniLocal.modelo.documento.Cliente;
import co.edu.uniquindio.uniLocal.repositorios.ClienteRepo;
import co.edu.uniquindio.uniLocal.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.uniLocal.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.uniLocal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class AutenticacionServicioImp implements AutenticacionServicio {

    private final ClienteRepo clienteRepo;
    private final JWTUtils jwtUtils;
    private final ModeradorServicio moderadorServicio;
    @Override
    public TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception  {


        moderadorServicio.inactivarNegocios();

        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(loginDTO.email());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();
        if( !passwordEncoder.matches(loginDTO.password(), cliente.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigoCliente());
        return new TokenDTO( jwtUtils.generarToken(cliente.getEmail(), map) );
    }

    public Boolean ValidateUser(String token, String ClienteDTO) throws Exception{
        Map<String,String> tokendes = jwtUtils.desencriptarToken(token);
        //tokendes.forEach((key, value) -> System.out.println("Clave: " + key + ", Valor: " + value));
        return ClienteDTO.equals(tokendes.get("id"));
    }

}

